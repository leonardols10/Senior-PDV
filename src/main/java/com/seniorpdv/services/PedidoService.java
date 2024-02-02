package com.seniorpdv.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seniorpdv.domain.Item;
import com.seniorpdv.domain.Pedido;
import com.seniorpdv.domain.PedidoItem;
import com.seniorpdv.domain.dtos.PedidoDTO;
import com.seniorpdv.domain.enums.StatusPedido;
import com.seniorpdv.repositories.PedidoRepository;
import com.seniorpdv.services.exceptions.DataIntegrityViolationException;
import com.seniorpdv.services.exceptions.ObjectnotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repository;

	@Autowired
	private ItemService itemService;

	@Autowired
	private PedidoItemService pedidoItemService;

	public Pedido findById(UUID id) {
		Optional<Pedido> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectnotFoundException("Objeto não encontrado! Id:" + id));
	}

	public List<Pedido> findAll() {
		return repository.findAll();
	}

	public Pedido create(@Valid PedidoDTO objDTO) {
		Pedido pedido = new Pedido();
		pedido.setStatus(objDTO.getStatus());
		pedido.setTitulo(objDTO.getTitulo());
		pedido.setObservacoes(objDTO.getObservacoes());
		pedido.setDesconto(objDTO.getDesconto());

		Set<PedidoItem> pedidoItens = objDTO.getPedidoItens().stream().map(dto -> {
			Item item = itemService.findById(dto.getItem());
			if (!item.isAtivo()) {
				throw new DataIntegrityViolationException(
						"Não é possível adicionar um produto desativado em um pedido.");
			}

			PedidoItem pedidoItem = new PedidoItem();
			pedidoItem.setItem(item);
			pedidoItem.setQuantidade(dto.getQuantidade());

			if (item.isProduto() && objDTO.getDesconto() != null) {
				double desconto = objDTO.getDesconto() / 100.0;
				double precoComDesconto = item.getPreco() * (1 - desconto);
				pedidoItem.setPrecoUnitario(precoComDesconto);
			} else {
				pedidoItem.setPrecoUnitario(item.getPreco());
			}

			pedidoItem.setPedido(pedido);
			return pedidoItem;
		}).collect(Collectors.toSet());

		pedido.setItensPedido(pedidoItens);

		return repository.save(pedido);
	}

	@Transactional
	public Pedido update(UUID pedidoUuid, @Valid PedidoDTO objDTO) {
		Pedido pedido = repository.findById(pedidoUuid)
				.orElseThrow(() -> new ObjectnotFoundException("Pedido não encontrado! Id: " + pedidoUuid));

		pedido.setStatus(objDTO.getStatus());
		pedido.setTitulo(objDTO.getTitulo());
		pedido.setObservacoes(objDTO.getObservacoes());

		if (StatusPedido.FECHADO.equals(objDTO.getStatus())) {
			pedido.setDataFechamento(LocalDate.now());
		}

		if (pedido.getStatus().equals(StatusPedido.ABERTO) && objDTO.getDesconto() != null
				&& objDTO.getDesconto() >= 0) {
			pedido.setDesconto(objDTO.getDesconto());
		} else if (!pedido.getStatus().equals(StatusPedido.ABERTO) && objDTO.getDesconto() != null) {
			throw new DataIntegrityViolationException(
					"Não é possível aplicar desconto a um pedido que não está na situação 'Aberto'.");
		}

		pedido.getItensPedido().forEach(pedidoItem -> {
			Item item = pedidoItem.getItem();
			double desconto = pedido.getDesconto() != null ? pedido.getDesconto() / 100.0 : 0;
			double precoComDesconto = item.getPreco() * (1 - desconto);

			if (item.isProduto() && pedido.getDesconto() != null && pedido.getDesconto() >= 0) {
				pedidoItem.setPrecoUnitario(precoComDesconto);
			} else {
				pedidoItem.setPrecoUnitario(item.getPreco());
			}
		});

		return repository.save(pedido);
	}

}

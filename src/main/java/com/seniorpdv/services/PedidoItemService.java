package com.seniorpdv.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seniorpdv.domain.Item;
import com.seniorpdv.domain.Pedido;
import com.seniorpdv.domain.PedidoItem;
import com.seniorpdv.domain.dtos.PedidoItemDTO;
import com.seniorpdv.repositories.PedidoItemRepository;
import com.seniorpdv.services.exceptions.DataIntegrityViolationException;
import com.seniorpdv.services.exceptions.ObjectnotFoundException;

@Service
public class PedidoItemService {

	@Autowired
	private PedidoItemRepository repository;

	@Autowired
	private ItemService itemService;

	@Autowired
	private PedidoService pedidoService;

	public PedidoItem findById(UUID id) {
		Optional<PedidoItem> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectnotFoundException("Objeto não encontrado! Id:" + id));
	}

	public List<PedidoItem> findAll() {
		return repository.findAll();
	}

	public PedidoItem create(@Valid PedidoItemDTO objDTO) {
		return repository.save(newPedidoItem(objDTO));
	}

	private PedidoItem newPedidoItem(PedidoItemDTO obj) {
		Pedido pedido = pedidoService.findById(obj.getPedido());
		Item item = itemService.findById(obj.getItem());

		if (!item.isAtivo()) {
			throw new DataIntegrityViolationException("Não é possível adicionar um produto desativado em um pedido.");
		}

		PedidoItem pedidoItem = new PedidoItem();

		if (obj.getId() != null) {
			pedidoItem.setId(obj.getId());
		}

		pedidoItem.setPedido(pedido);
		pedidoItem.setItem(item);
		pedidoItem.setQuantidade(obj.getQuantidade());
		pedidoItem.setPrecoUnitario(obj.getPrecoUnitario());
		return pedidoItem;
	}

	@Transactional
	public PedidoItem update(UUID pedidoItemId, @Valid PedidoItemDTO objDTO) {
		PedidoItem pedidoItemExistente = repository.findById(pedidoItemId)
				.orElseThrow(() -> new ObjectnotFoundException("PedidoItem não encontrado! Id: " + pedidoItemId));

		pedidoItemExistente.setQuantidade(objDTO.getQuantidade());
		pedidoItemExistente.setPrecoUnitario(objDTO.getPrecoUnitario());

		Item item = itemService.findById(objDTO.getItem());
		if (!item.isAtivo()) {
			throw new DataIntegrityViolationException("Não é possível adicionar um produto desativado em um pedido.");
		}
		pedidoItemExistente.setItem(item);

		return repository.save(pedidoItemExistente);
	}

}

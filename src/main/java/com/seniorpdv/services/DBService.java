package com.seniorpdv.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seniorpdv.domain.Item;
import com.seniorpdv.domain.Pedido;
import com.seniorpdv.domain.PedidoItem;
import com.seniorpdv.domain.enums.StatusPedido;
import com.seniorpdv.repositories.PedidoRepository;
import com.seniorpdv.repositories.itemRepository;

@Service
public class DBService {

	@Autowired
	private itemRepository itemRepository;

	@Autowired
	private PedidoRepository pedidoRepository;

	public void instanciaDB() {
		Item prod1 = new Item();
		prod1.setNome("Teste");
		prod1.setPreco((double) 100);
		prod1.setProduto(true);
		prod1.setAtivo(true);

		Pedido p1 = new Pedido();
		p1.setStatus(StatusPedido.FECHADO);
		p1.setTitulo("Pedido de Teste");
		p1.setObservacoes("Nenhuma observação");

		PedidoItem item1 = new PedidoItem();
		item1.setQuantidade(2);
		item1.setPrecoUnitario(50);
		item1.setProdutoServico(prod1);

		p1.adicionarItem(item1);

		itemRepository.save(prod1);
		pedidoRepository.save(p1);
	}
}

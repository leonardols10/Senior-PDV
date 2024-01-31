package com.seniorpdv;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.seniorpdv.domain.Pedido;
import com.seniorpdv.domain.ProdutoServico;
import com.seniorpdv.domain.enums.StatusPedido;
import com.seniorpdv.domain.enums.TipoItem;
import com.seniorpdv.repositories.PedidoRepository;
import com.seniorpdv.repositories.ProdutoServicoRepository;

@SpringBootApplication
public class SeniorpdvApplication  implements CommandLineRunner{

    @Autowired
    private ProdutoServicoRepository produtoServicoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;
	public static void main(String[] args) {
		SpringApplication.run(SeniorpdvApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		ProdutoServico prod1 = new ProdutoServico(null, "Teste", 1, 2);
		prod1.addItenspedido(TipoItem.SERVICO);
		
		Pedido p1 = new Pedido(null, StatusPedido.FECHADO, "teste", "teste", prod1);
		
		produtoServicoRepository.saveAll(Arrays.asList(prod1));
		pedidoRepository.saveAll(Arrays.asList(p1));
	}

}

package com.seniorpdv.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seniorpdv.domain.ItemPedido;
import com.seniorpdv.domain.ProdutoServico;

public interface ProdutoServicoRepository extends JpaRepository<ProdutoServico, Integer> {

}

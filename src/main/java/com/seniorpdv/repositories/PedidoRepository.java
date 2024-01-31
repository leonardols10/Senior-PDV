package com.seniorpdv.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seniorpdv.domain.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

}

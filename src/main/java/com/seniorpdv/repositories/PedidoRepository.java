package com.seniorpdv.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seniorpdv.domain.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, UUID> {

}

package com.seniorpdv.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seniorpdv.domain.PedidoItem;

public interface PedidoItemRepository extends JpaRepository<PedidoItem, UUID> {

}

package com.seniorpdv.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.seniorpdv.domain.Item;

public interface itemRepository extends JpaRepository<Item, UUID>, JpaSpecificationExecutor<Item> {


}

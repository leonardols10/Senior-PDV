package com.seniorpdv.services;

import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.seniorpdv.domain.Item;
import com.seniorpdv.domain.ItemSpecification;
import com.seniorpdv.domain.dtos.ItemDTO;
import com.seniorpdv.repositories.itemRepository;
import com.seniorpdv.services.exceptions.DataIntegrityViolationException;
import com.seniorpdv.services.exceptions.ObjectnotFoundException;

@Service
public class ItemService {
	@Autowired
	private itemRepository itemRepository;

	public Item findById(UUID id) {
		Optional<Item> obj = itemRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectnotFoundException("Objeto não encontrado! Id:" + id));
	}

	public Page<Item> findAll(Pageable pageable, String nome) {
		return itemRepository.findAll(ItemSpecification.hasNome(nome), pageable);
	}

	public Item create(@Valid @RequestBody ItemDTO objDTO) {
		objDTO.setId(null);
		Item newObj = new Item(objDTO);
		return itemRepository.save(newObj);
	}

	public Item update(UUID id, @Valid ItemDTO objDTO) {
		objDTO.setId(id);
		Item oldObj = findById(id);

		oldObj = new Item(objDTO);
		return itemRepository.save(oldObj);
	}

	public void delete(UUID id) {
		Item obj = findById(id);

		if (obj.getPedidoItens() != null && !obj.getPedidoItens().isEmpty()) {
			throw new DataIntegrityViolationException(
					"O Item possui pedido e não pode ser deletado");
		}

		itemRepository.deleteById(id);

	}

}

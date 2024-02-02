package com.seniorpdv.resources;

import java.net.URI;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.seniorpdv.domain.Item;
import com.seniorpdv.domain.dtos.ItemDTO;
import com.seniorpdv.services.ItemService;

@RestController
@RequestMapping(value = "/item")
public class ItemResource {

	@Autowired
	private ItemService itemService;

	@GetMapping(value = "/{id}")
	public ResponseEntity<ItemDTO> findById(@PathVariable UUID id) {
		Item obj = itemService.findById(id);
		return ResponseEntity.ok().body(new ItemDTO(obj));
	}

	@GetMapping
	public ResponseEntity<Page<ItemDTO>> findAll(@PageableDefault(page = 0, size = 10) Pageable pageable,
			@RequestParam(value = "nome", required = false) String nome) {
		Page<Item> page = itemService.findAll(pageable, nome);
		Page<ItemDTO> pageDTO = page.map(obj -> new ItemDTO(obj));
		return ResponseEntity.ok().body(pageDTO);
	}

	@PostMapping
	public ResponseEntity<ItemDTO> create(@Valid @RequestBody ItemDTO objDTO) {
		Item newObj = itemService.create(objDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<ItemDTO> update(@PathVariable UUID id, @Valid @RequestBody ItemDTO objDTO) {
		Item obj = itemService.update(id, objDTO);
		return ResponseEntity.ok().body(new ItemDTO(obj));
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<ItemDTO> delete(@PathVariable UUID id) {
		itemService.delete(id);

		return ResponseEntity.noContent().build();

	}

}

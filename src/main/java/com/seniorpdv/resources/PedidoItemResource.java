package com.seniorpdv.resources;

import java.net.URI;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.seniorpdv.domain.PedidoItem;
import com.seniorpdv.domain.dtos.PedidoItemDTO;
import com.seniorpdv.services.PedidoItemService;

@RestController
@RequestMapping(value = "/pedido-itens")
public class PedidoItemResource {

	@Autowired
	private PedidoItemService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<PedidoItemDTO> findById(@PathVariable UUID id) {
		PedidoItem obj = service.findById(id);
		return ResponseEntity.ok().body(new PedidoItemDTO(obj));
	}

	@GetMapping
	public ResponseEntity<List<PedidoItemDTO>> findAll() {
		List<PedidoItem> list = service.findAll();
		List<PedidoItemDTO> listDTO = list.stream().map(obj -> new PedidoItemDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}

	@PostMapping
	public ResponseEntity<PedidoItemDTO> create(@Valid @RequestBody PedidoItemDTO objDTO) {
		PedidoItem obj = service.create(objDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<PedidoItemDTO> update(@PathVariable UUID id, @Valid @RequestBody PedidoItemDTO objDTO) {
		PedidoItem newObj = service.update(id, objDTO);
		return ResponseEntity.ok().body(new PedidoItemDTO(newObj));

	}

}

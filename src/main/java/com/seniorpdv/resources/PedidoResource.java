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

import com.seniorpdv.domain.Pedido;
import com.seniorpdv.domain.dtos.PedidoDTO;
import com.seniorpdv.services.PedidoService;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoResource {

	@Autowired
	private PedidoService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<PedidoDTO> findById(@PathVariable UUID id) {
		Pedido obj = service.findById(id);
		return ResponseEntity.ok().body(new PedidoDTO(obj));
	}

	@GetMapping
	public ResponseEntity<List<PedidoDTO>> findAll() {
		List<Pedido> list = service.findAll();
		List<PedidoDTO> listDTO = list.stream().map(obj -> new PedidoDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}

	@PostMapping
	public ResponseEntity<PedidoDTO> create(@Valid @RequestBody PedidoDTO objDTO) {
		Pedido obj = service.create(objDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<PedidoDTO> update(@PathVariable UUID id, @Valid @RequestBody PedidoDTO objDTO) {
		Pedido newObj = service.update(id, objDTO);
		return ResponseEntity.ok().body(new PedidoDTO(newObj));

	}

}

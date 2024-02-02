package com.seniorpdv.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.seniorpdv.domain.Pedido;
import com.seniorpdv.repositories.PedidoRepository;
import com.seniorpdv.services.exceptions.ObjectnotFoundException;

class PedidoServiceTest {

	@Mock
	private PedidoRepository repository;

	@InjectMocks
	private PedidoService service;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void testFindByIdExists() {
		UUID id = UUID.randomUUID();
		Pedido expectedPedido = new Pedido();
		expectedPedido.setId(id);

		when(repository.findById(id)).thenReturn(Optional.of(expectedPedido));

		Pedido result = service.findById(id);

		assertNotNull(result);
		assertEquals(expectedPedido.getId(), result.getId());
	}

	@Test
	void testFindByIdNotExists() {
		UUID id = UUID.randomUUID();

		when(repository.findById(id)).thenReturn(Optional.empty());

		assertThrows(ObjectnotFoundException.class, () -> {
			service.findById(id);
		});
	}

	@Test
	void testFindAll() {
		Pedido pedido1 = new Pedido();
		Pedido pedido2 = new Pedido();
		List<Pedido> expectedList = Arrays.asList(pedido1, pedido2);

		when(repository.findAll()).thenReturn(expectedList);

		List<Pedido> resultList = service.findAll();

		assertNotNull(resultList);
		assertFalse(resultList.isEmpty());
		assertEquals(2, resultList.size());
	}

}

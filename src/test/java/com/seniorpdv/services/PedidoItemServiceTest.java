package com.seniorpdv.services;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.seniorpdv.domain.PedidoItem;
import com.seniorpdv.repositories.PedidoItemRepository;

class PedidoItemServiceTest {

	@Mock
	private PedidoItemRepository pedidoItemRepository;

	@InjectMocks
	private PedidoItemService pedidoItemService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void testFindAll() {
		PedidoItem pedidoItem1 = new PedidoItem();
		PedidoItem pedidoItem2 = new PedidoItem();
		List<PedidoItem> expectedPedidoItens = Arrays.asList(pedidoItem1, pedidoItem2);

		when(pedidoItemRepository.findAll()).thenReturn(expectedPedidoItens);

		List<PedidoItem> foundPedidoItens = pedidoItemService.findAll();

		assertFalse(foundPedidoItens.isEmpty(), "Deveria retornar uma lista de PedidoItens.");
		assertEquals(2, foundPedidoItens.size(), "A lista de PedidoItens deve conter dois elementos.");
	}
}

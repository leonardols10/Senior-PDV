package com.seniorpdv.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.seniorpdv.domain.Item;
import com.seniorpdv.services.exceptions.ObjectnotFoundException;

@ExtendWith(MockitoExtension.class)
class ItemServiceTest {

	@InjectMocks
	private ItemService itemService;

	@Mock
	private com.seniorpdv.repositories.itemRepository itemRepository;

	private final UUID itemId = UUID.randomUUID();
	private final Item item = new Item();

	@BeforeEach
	void setUp() {
		item.setId(itemId);
		item.setNome("Test Item");
		item.setPreco(100.0);
		item.setProduto(true);
		item.setAtivo(true);
	}

	@Test
	void testFindByIdSuccess() {
		when(itemRepository.findById(any(UUID.class))).thenReturn(Optional.of(item));
		Item foundItem = itemService.findById(itemId);
		assertNotNull(foundItem, "O item encontrado não deve ser nulo");
		assertEquals(itemId, foundItem.getId(), "O ID do item encontrado deve corresponder ao ID solicitado");
	}

	@Test
	void testFindByIdNotFound() {
		when(itemRepository.findById(any(UUID.class))).thenReturn(Optional.empty());
		assertThrows(ObjectnotFoundException.class, () -> itemService.findById(itemId),
				"Deve lançar uma ObjectnotFoundException quando o item não for encontrado");
	}

}

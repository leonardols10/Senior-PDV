package com.seniorpdv.resources;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.seniorpdv.domain.Pedido;
import com.seniorpdv.services.PedidoService;

@WebMvcTest(PedidoResource.class)
class PedidoResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PedidoService pedidoService;

  
    
    @Test
    void testFindAll() throws Exception {
        List<Pedido> pedidos = new ArrayList<>();
        pedidos.add(new Pedido()); 
        when(pedidoService.findAll()).thenReturn(pedidos);

        mockMvc.perform(get("/pedidos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(pedidos.size())));
    }



}

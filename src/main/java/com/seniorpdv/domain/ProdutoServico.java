package com.seniorpdv.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.seniorpdv.domain.enums.StatusProduto;
import com.seniorpdv.domain.enums.TipoItem;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Entity
public class ProdutoServico extends ItemPedido {
	
	private static final long serialVersionUID = 1L;
	
	@JsonIgnore
	@OneToMany(mappedBy = "produtoservico")
    private List<Pedido> pedidos = new ArrayList<>();

	public ProdutoServico() {
		super();
	}

	public ProdutoServico(Integer id, String produtoServico, Integer quantidade, Integer precoUnitario) {
		super(id, produtoServico, quantidade, precoUnitario);
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}
	
	
    

}

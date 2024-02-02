package com.seniorpdv.domain;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;

import com.seniorpdv.domain.dtos.ItemDTO;

@Entity
public class Item implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(updatable = false, nullable = false)
	private UUID id;

	private String nome;
	private Double preco;
	private boolean isProduto;

	private boolean isAtivo;

	@OneToMany(mappedBy = "item")
	private Set<PedidoItem> pedidoItens;

	public Item() {
	}

	public Item(ItemDTO obj) {
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.preco = obj.getPreco();
		this.isProduto = obj.isProduto();
		this.isAtivo = obj.isAtivo();
	}

	public Set<PedidoItem> getPedidoItens() {
		return pedidoItens;
	}

	public void setPedidoItens(Set<PedidoItem> pedidoItens) {
		this.pedidoItens = pedidoItens;
	}

	public UUID getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public Double getPreco() {
		return preco;
	}

	public boolean isProduto() {
		return isProduto;
	}

	public boolean isAtivo() {
		return isAtivo;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public void setProduto(boolean isProduto) {
		this.isProduto = isProduto;
	}

	public void setAtivo(boolean isAtivo) {
		this.isAtivo = isAtivo;
	}

}

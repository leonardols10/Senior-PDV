package com.seniorpdv.domain.dtos;

import java.io.Serializable;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import com.seniorpdv.domain.Item;

public class ItemDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private UUID id;
	@NotNull(message = "O campo nome é obrigátorio")
	private String nome;
	@NotNull(message = "O campo preco é obrigátorio")
	private Double preco;
	private boolean isProduto;
	private boolean isAtivo;

	public ItemDTO() {
		super();
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public boolean isProduto() {
		return isProduto;
	}

	public void setProduto(boolean isProduto) {
		this.isProduto = isProduto;
	}

	public boolean isAtivo() {
		return isAtivo;
	}

	public void setAtivo(boolean isAtivo) {
		this.isAtivo = isAtivo;
	}

	public ItemDTO(Item obj) {
		super();
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.preco = obj.getPreco();
		this.isProduto = obj.isProduto();
		this.isAtivo = obj.isAtivo();
	}

}

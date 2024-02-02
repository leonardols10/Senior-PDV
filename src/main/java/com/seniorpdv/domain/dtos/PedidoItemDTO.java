package com.seniorpdv.domain.dtos;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.seniorpdv.domain.PedidoItem;

public class PedidoItemDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(updatable = false, nullable = false)
	private UUID id;
	@NotNull(message = "O campo quantidade é obrigatorio")
	@Column(name = "quantidade")
	private Integer quantidade;
	@NotNull(message = "O campo preco é obrigatorio")
	@Column(name = "preco_unitario")
	private Double precoUnitario;
	@Column(name = "data_criacao")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataCriacao = LocalDate.now();
	private UUID pedido;
	private UUID item;
	private String nomeItem;

	public PedidoItemDTO() {
		super();
	}

	public PedidoItemDTO(PedidoItem obj) {
		super();
		this.id = obj.getId();
		this.quantidade = obj.getQuantidade();
		this.precoUnitario = obj.getPrecoUnitario();
		this.dataCriacao = obj.getDataCriacao();
		this.pedido = obj.getPedido().getId();
		this.item = obj.getItem().getId();
		this.nomeItem = obj.getItem().getNome();
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Double getPrecoUnitario() {
		return precoUnitario;
	}

	public void setPrecoUnitario(Double precoUnitario) {
		this.precoUnitario = precoUnitario;
	}

	public LocalDate getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDate dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public UUID getPedido() {
		return pedido;
	}

	public void setPedido(UUID pedido) {
		this.pedido = pedido;
	}

	public UUID getItem() {
		return item;
	}

	public void setItem(UUID item) {
		this.item = item;
	}

	public String getNomeItem() {
		return nomeItem;
	}

	public void setNomeItem(String nomeItem) {
		this.nomeItem = nomeItem;
	}

}

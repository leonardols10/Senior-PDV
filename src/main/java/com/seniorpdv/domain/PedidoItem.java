package com.seniorpdv.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "pedido_item")
public class PedidoItem implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(updatable = false, nullable = false)
	private UUID id;

	@Column(name = "quantidade")
	private Integer quantidade;

	@Column(name = "preco_unitario")
	private Double precoUnitario;

	@Column(name = "data_criacao")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataCriacao = LocalDate.now();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pedido_id")
	private Pedido pedido;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "item_id")
	private Item item;

	public PedidoItem() {
	}

	public UUID getId() {
		return id;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public Double getPrecoUnitario() {
		return precoUnitario;
	}

	public LocalDate getDataCriacao() {
		return dataCriacao;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public Item getProdutoServico() {
		return item;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public void setPrecoUnitario(double precoComDesconto) {
		this.precoUnitario = (Double) precoComDesconto;
	}

	public void setDataCriacao(LocalDate dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public void setProdutoServico(Item produtoServico) {
		this.item = produtoServico;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dataCriacao, id, item, pedido, precoUnitario, quantidade);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PedidoItem other = (PedidoItem) obj;
		return Objects.equals(dataCriacao, other.dataCriacao) && Objects.equals(id, other.id)
				&& Objects.equals(item, other.item) && Objects.equals(pedido, other.pedido)
				&& Objects.equals(precoUnitario, other.precoUnitario) && Objects.equals(quantidade, other.quantidade);
	}
}

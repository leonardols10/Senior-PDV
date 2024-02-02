package com.seniorpdv.domain.dtos;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.seniorpdv.domain.Pedido;
import com.seniorpdv.domain.PedidoItem;
import com.seniorpdv.domain.enums.StatusPedido;

public class PedidoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(updatable = false, nullable = false)
	private UUID id;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataAbertura = LocalDate.now();
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataFechamento;
	@Enumerated(EnumType.STRING)
	private StatusPedido status;
	@NotNull(message = "O campo titulo é obrigatorio")
	private String titulo;
	private String observacoes;
	@NotNull(message = "O campo item é obrigatorio")
	private Set<UUID> pedidoItemIds = new HashSet<>();
	private Double desconto;
	private Set<PedidoItemDTO> pedidoItens;

	public PedidoDTO() {
		super();
	}

	public PedidoDTO(Pedido obj) {
		super();
		this.id = obj.getId();
		this.dataAbertura = obj.getDataAbertura();
		this.dataFechamento = obj.getDataFechamento();
		this.status = obj.getStatus();
		this.titulo = obj.getTitulo();
		this.observacoes = obj.getObservacoes();
		this.pedidoItemIds = obj.getItensPedido().stream().map(PedidoItem::getId).collect(Collectors.toSet());
		this.desconto = obj.getDesconto();
	}

	public Set<PedidoItemDTO> getPedidoItens() {
		return pedidoItens;
	}

	public void setPedidoItens(Set<PedidoItemDTO> pedidoItens) {
		this.pedidoItens = pedidoItens;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public LocalDate getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(LocalDate dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public LocalDate getDataFechamento() {
		return dataFechamento;
	}

	public void setDataFechamento(LocalDate dataFechamento) {
		this.dataFechamento = dataFechamento;
	}

	public StatusPedido getStatus() {
		return status;
	}

	public void setStatus(StatusPedido status) {
		this.status = status;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public Set<UUID> getPedidoItemIds() {
		return pedidoItemIds;
	}

	public void setPedidoItemIds(Set<UUID> pedidoItemIds) {
		this.pedidoItemIds = pedidoItemIds;
	}

	public Double getDesconto() {
		return desconto;
	}

	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}

}

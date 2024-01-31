package com.seniorpdv.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.seniorpdv.domain.enums.TipoItem;

@Entity
public class ItemPedido  implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Integer id;
	protected String produtoServico;
	protected Integer quantidade;
	protected Integer precoUnitario;
	
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "TIPOITEM")
	protected Set<Integer> itenspedido = new HashSet<>();
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	protected LocalDate datacriacao = LocalDate.now();
	
	public ItemPedido() {
		super();
	}

	public ItemPedido(Integer id, String produtoServico, Integer quantidade, Integer precoUnitario) {
		super();
		this.id = id;
		this.produtoServico = produtoServico;
		this.quantidade = quantidade;
		this.precoUnitario = precoUnitario;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProdutoServico() {
		return produtoServico;
	}

	public void setProdutoServico(String produtoServico) {
		this.produtoServico = produtoServico;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Integer getPrecoUnitario() {
		return precoUnitario;
	}

	public void setPrecoUnitario(Integer precoUnitario) {
		this.precoUnitario = precoUnitario;
	}

	public Set<TipoItem> getItenspedido() {
		return itenspedido.stream().map( x -> TipoItem.toEnum(x)).collect(Collectors.toSet());

	}

	public void addItenspedido(TipoItem itempedido) {
		this.itenspedido.add(itempedido.getCodigo());	
	}

	public LocalDate getDatacriacao() {
		return datacriacao;
	}

	public void setDatacriacao(LocalDate datacriacao) {
		this.datacriacao = datacriacao;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemPedido other = (ItemPedido) obj;
		return Objects.equals(id, other.id);
	}
	
	

}

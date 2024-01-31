package com.seniorpdv.domain.enums;

public enum StatusPedido {
	
    ABERTO(0, "ABERTO"), 
    FECHADO(1, "FECHADO");

	private Integer codigo;
	private String descricao;
	
	private StatusPedido(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}
	
	
	public static StatusPedido toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		
		for(StatusPedido x : StatusPedido.values()) {
			if(cod.equals(x.getCodigo())) {
				return x; 
			}
		}
		
		throw new IllegalArgumentException("Status inválido");
	}
	
	
	
}
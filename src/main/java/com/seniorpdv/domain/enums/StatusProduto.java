package com.seniorpdv.domain.enums;

public enum StatusProduto {

    ATIVO(0, "ATIVO"),
    DESATIVADO(1, "DESATIVADO");

    private Integer codigo;
    private String descricao;

    private StatusProduto(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static StatusProduto toEnum(Integer cod) {
        if (cod == null) {
            return null;
        }

        for (StatusProduto x : StatusProduto.values()) {
            if (cod.equals(x.getCodigo())) {
                return x;
            }
        }

        throw new IllegalArgumentException("Status do produto inválido");
    }
}

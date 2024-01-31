package com.seniorpdv.domain.enums;

public enum TipoItem {

    PRODUTO(0, "PRODUTO"),
    SERVICO(1, "SERVICO");

    private Integer codigo;
    private String descricao;

    private TipoItem(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static TipoItem toEnum(Integer cod) {
        if (cod == null) {
            return null;
        }

        for (TipoItem x : TipoItem.values()) {
            if (cod.equals(x.getCodigo())) {
                return x;
            }
        }

        throw new IllegalArgumentException("Tipo de item inválido");
    }
}

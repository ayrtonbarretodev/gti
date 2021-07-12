package com.br.gti.sistemagti.domain.enums;

public enum Status {
    EMUSO("Em uso"),
    MANUTENCAO("Em manutenção"),
    DESCARTADO("Descartado");

    private String descricao;

    Status(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}

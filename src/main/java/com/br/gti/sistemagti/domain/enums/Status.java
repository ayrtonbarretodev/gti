package com.br.gti.sistemagti.domain.enums;

public enum Status {
    EMUSO("Em uso"),
    MANUTENCAO("Em manutenção"),
    DESCARTADO("Descartado");

    //private final int valor;
    private String descricao;

//    Status(int valor, String descricao) {
//        this.valor = valor;
//        this.descricao = descricao;
//    }

//    Status(int valor) {
//        this.valor = valor;
//    }
//
//    public int getValor() {
//        return valor;
//    }


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

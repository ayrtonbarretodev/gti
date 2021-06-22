package com.br.gti.sistemagti.domain.enums;

public enum Status {
    EMUSO(1),MANUTENCAO(2),DESCARTADO(3);

    private final int valor;

    Status(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }
}

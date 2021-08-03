package com.br.gti.sistemagti.domain;

import lombok.Getter;

@Getter
public enum PerfilTipo {
    ADMIN(1, "ADMIN"), ESTAGIARIO(2, "ESTAGIARIO");

    private long cod;
    private String desc;

    private PerfilTipo(long cod, String desc) {
        this.cod = cod;
        this.desc = desc;
    }

    public long getCod() {
        return cod;
    }

    public String getDesc() {
        return desc;
    }

}

package com.br.gti.sistemagti.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Getter
@Setter
@SuppressWarnings("serial")
@Entity
@Table(name = "estagiarios")
public class Estagiario extends AbstractEntity<Long> {

    @Column(name = "nome", unique = true, nullable = false)
    private String nome;

    @Column(name = "matricula", unique = true, nullable = false)
    private Long matricula;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    public Estagiario() {
        super();
    }

    public Estagiario(Long id) {
        super.setId(id);
    }

    public Estagiario(Usuario usuario) {
        this.usuario = usuario;
    }
}

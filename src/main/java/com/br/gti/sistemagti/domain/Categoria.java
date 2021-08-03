package com.br.gti.sistemagti.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "CATEGORIAS")
public class Categoria extends AbstractEntity<Long> {

    @NotBlank(message = "O Campo Categoria é Obrigatório.")
    @Column(nullable = false, unique = true, length = 60)
    private String nome;

    @OneToMany(mappedBy = "categoria")
    private List<Equipamento> equipamentos = new ArrayList<>();
}

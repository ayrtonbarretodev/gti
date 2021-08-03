package com.br.gti.sistemagti.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "DEPARTAMENTOS")
public class Departamento extends AbstractEntity<Long> {

    @NotBlank(message = "O Campo Departamento é Obrigatório.")
    @Column(nullable = false, length = 60)
    @Getter
    @Setter
    private String nome;

    @Getter
    @Setter
    private String ambiente;

    @Column(length = 9)
    @Size(min = 9, max = 9, message = "{Size.departamento.telefone}")
    @Getter
    @Setter
    private String telefone;

    @OneToMany(mappedBy = "departamento")
    @Getter
    @Setter
    private List<Equipamento> equipamentos = new ArrayList<>();

}

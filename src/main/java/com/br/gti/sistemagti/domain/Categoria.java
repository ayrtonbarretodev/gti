package com.br.gti.sistemagti.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    @OneToMany(mappedBy = "categoria")
    private List<Equipamento> equipamentos = new ArrayList<>();

    public List<Equipamento> getEquipamentos() {
        return equipamentos;
    }

    public void setEquipamentos(List<Equipamento> equipamentos) {
        this.equipamentos = equipamentos;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}

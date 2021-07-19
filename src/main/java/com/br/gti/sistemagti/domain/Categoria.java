package com.br.gti.sistemagti.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "CATEGORIAS")
public class Categoria extends AbstractEntity<Long> {

    @NotBlank(message = "O nome da Categoria é obrigatório")
    @Column(nullable = false, unique = true, length = 60)
    private String nome;

    @OneToMany(mappedBy = "categoria")
    private List<Equipamento> equipamentos = new ArrayList<>();

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome.trim();
    }

    public List<Equipamento> getEquipamentos() {
        return equipamentos;
    }

    public void setEquipamentos(List<Equipamento> equipamentos) {
        this.equipamentos = equipamentos;
    }

}

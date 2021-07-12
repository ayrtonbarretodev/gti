package com.br.gti.sistemagti.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "DEPARTAMENTOS")
public class Departamento extends AbstractEntity<Long>{

    @Column(nullable = false,unique = true,length = 60)
    private String nome;

    @Column(length = 9)
    private String telefone;

    @OneToMany(mappedBy = "departamento")
    private List<Equipamento> equipamentos = new ArrayList<>();

    @OneToMany(mappedBy = "departamento")
    private List<Funcionario>funcionarios = new ArrayList<>();

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public List<Equipamento> getEquipamentos() {
        return equipamentos;
    }

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setEquipamentos(List<Equipamento> equipamentos) {
        this.equipamentos = equipamentos;
    }

    public void setFuncionarios(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

//    @Override
//    public String toString() {
//        return nome;
//    }
}

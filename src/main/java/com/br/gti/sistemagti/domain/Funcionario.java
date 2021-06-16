package com.br.gti.sistemagti.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "FUNCIONARIOS")
public class Funcionario extends AbstractEntity<Long>{

    @Column(nullable = false,unique = true)
    private String nome;

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "endereco_id_fk")
//    private Endereco endereco;

    //private Departamento departamento;
}

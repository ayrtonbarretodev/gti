package com.br.gti.sistemagti.domain;

import com.br.gti.sistemagti.domain.enums.UF;

import javax.persistence.*;

@Entity
@Table(name= "ENDERECOS")
public class Endereco extends AbstractEntity<Long>{

    @Column(nullable = false)
    private String logradouro;

    @Column(nullable = false)
    private String bairro;

    @Column(nullable = false)
    private String cidade;

    @Column(nullable = false, length = 2)
    @Enumerated(EnumType.STRING)
    private UF uf;

    @Column(nullable = false, length = 9)
    private String cep;

    @Column(nullable = false, length = 5)
    private Integer numero;

    //@OneToOne
    //private Funcionario funcionario;

    private String complemento;


}

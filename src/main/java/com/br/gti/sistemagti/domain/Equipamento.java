package com.br.gti.sistemagti.domain;

import com.br.gti.sistemagti.domain.enums.Status;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "EQUIPAMENTOS")
public class Equipamento extends AbstractEntity<Long>{

    @Column(nullable = false, length = 60)
    private String nome;

    private String modelo;

    private String fabricante;

    @Column(nullable = false, columnDefinition = "DATE")
    private LocalDate dataEntrada;

    @Column(columnDefinition = "DATE")
    private LocalDate dataSaida;

    private String observacao;

    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private Status status;

    @Column(nullable = false, unique = true, length = 60)
    private String numeroSerie;

    @ManyToOne
    @JoinColumn(name = "id_categoria_fk")
    private Categoria categoria;

}

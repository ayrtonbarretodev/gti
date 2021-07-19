package com.br.gti.sistemagti.domain;

import com.br.gti.sistemagti.domain.enums.Status;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Audited
@Entity
@Table(name = "EQUIPAMENTOS")
public class Equipamento extends AbstractEntity<Long> {

    @Column(nullable = false, length = 60)
    private String nome;

    private String modelo;

    private String fabricante;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(nullable = false, columnDefinition = "DATE")
    private LocalDate dataEntrada;

//    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
//    @Column(columnDefinition = "DATE")
//    private LocalDate dataSaida;

    private String observacao;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(nullable = false, unique = true, length = 60)
    private String numeroSerie;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @ManyToOne
    @JoinColumn(name = "id_categoria_fk", nullable = false)
    private Categoria categoria;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @ManyToOne
    @JoinColumn(name = "id_departamento_fk", nullable = false)
    private Departamento departamento;

    @Column(nullable = false, unique = true, length = 17)
    private String enderecoMac;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public LocalDate getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(LocalDate dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

//    public LocalDate getDataSaida() {
//        return dataSaida;
//    }
//
//    public void setDataSaida(LocalDate dataSaida) {
//        this.dataSaida = dataSaida;
//    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public String getEnderecoMac() {
        return enderecoMac;
    }

    public void setEnderecoMac(String enderecoMac) {
        this.enderecoMac = enderecoMac;
    }
}

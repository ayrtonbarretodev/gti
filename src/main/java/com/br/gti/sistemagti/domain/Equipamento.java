package com.br.gti.sistemagti.domain;

import com.br.gti.sistemagti.domain.enums.Status;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Audited
@Entity
@Table(name = "EQUIPAMENTOS")
public class Equipamento extends AbstractEntity<Long> {

    @NotBlank (message = "O Campo Nome do Equipamento é Obrigatório")
    @Column(nullable = false, length = 60)
    private String nome;

    private String modelo;

    private String fabricante;

    @NotNull
    @PastOrPresent(message = "{PastOrPresent.equipamento.dataEntrada}")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(nullable = false, columnDefinition = "DATE")
    private LocalDate dataEntrada = LocalDate.now();

    @Column(unique = true)
    private Integer tomboPatrimonial;

    @Size (max = 255)
    private String observacao;

    @NotNull(message = "{NotNull.equipamento.status}")
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(unique = true, length = 60)
    private String numeroSerie;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @NotNull(message = "{NotNull.equipamento.categoria}")
    @ManyToOne
    @JoinColumn(name = "id_categoria_fk", nullable = false)
    private Categoria categoria;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @NotNull(message = "{NotNull.equipamento.departamento}")
    @ManyToOne
    @JoinColumn(name = "id_departamento_fk", nullable = false)
    private Departamento departamento;

    @Size(min = 17, max = 17, message = "{Size.equipamento.enderecoMac}")
    @Column(nullable = false, unique = true, length = 17)
    private String enderecoMac;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome.trim();
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo.trim();
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante.trim();
    }

    public LocalDate getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(LocalDate dataEntrada) {
        this.dataEntrada = dataEntrada;
    }


    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao.trim();
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
        this.numeroSerie = numeroSerie.toUpperCase().trim();
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
        this.enderecoMac = enderecoMac.trim();
    }

    public Integer getTomboPatrimonial() {
        return tomboPatrimonial;
    }

    public void setTomboPatrimonial(Integer tomboPatrimonial) {
        this.tomboPatrimonial = tomboPatrimonial;
    }
}

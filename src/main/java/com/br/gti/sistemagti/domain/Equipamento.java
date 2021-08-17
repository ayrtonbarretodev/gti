package com.br.gti.sistemagti.domain;

import com.br.gti.sistemagti.domain.enums.Status;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Audited
@Entity
@Table(name = "EQUIPAMENTOS")
public class Equipamento extends AbstractEntity<Long> {

    @NotBlank(message = "O Campo Nome do Equipamento é Obrigatório.")
    @Column(nullable = false, length = 60)
    @Getter
    @Setter
    private String nome;

    @Getter
    @Setter
    private String modelo;

    @Getter
    @Setter
    private String fabricante;

    @NotNull
    @PastOrPresent(message = "{PastOrPresent.equipamento.dataEntrada}")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(nullable = false, columnDefinition = "DATE")
    @Getter
    @Setter
    private LocalDate dataEntrada = LocalDate.now();

    @Column(unique = true,length = 10)
    //@Size(min = 10, max = 10, message = "{Size.equipamento.tombo}")
    @Getter
    @Setter
    private Integer tomboPatrimonial;

//    @Size(max = 255)
//    @Getter
//    @Setter
//    private String observacao;

    @NotNull(message = "{NotNull.equipamento.status}")
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @Getter
    @Setter
    private Status status;

    @Column(unique = true, length = 60)
    @Getter
    @Setter
    private String numeroSerie;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @NotNull(message = "{NotNull.equipamento.categoria}")
    @ManyToOne
    @JoinColumn(name = "id_categoria_fk", nullable = false)
    @Getter
    @Setter
    private Categoria categoria;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @NotNull(message = "{NotNull.equipamento.departamento}")
    @ManyToOne
    @JoinColumn(name = "id_departamento_fk", nullable = false)
    @Getter
    @Setter
    private Departamento departamento;

    @Size(max = 17, message = "{Size.equipamento.enderecoMac}")
    @Column(unique = true, length = 17)
    @Getter
    @Setter
    private String enderecoMac;
}

package com.br.gti.sistemagti.domain;

import com.br.gti.sistemagti.listener.EntityRevisionListener;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@RequiredArgsConstructor
@Entity
@RevisionEntity(value = EntityRevisionListener.class)
public class Revisao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "revisao_Sequence")
    @SequenceGenerator(name = "revisao_Sequence", sequenceName = "REVISAO_SEQ")
    @RevisionNumber
    private Long revisaoId;

    @RevisionTimestamp
    private Date revisaoData;

    @Column
    private String ip;

    @Column
    private String usuario;
}

package com.br.gti.sistemagti.repository;

import com.br.gti.sistemagti.domain.Equipamento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.Tuple;
import java.util.List;

public interface EquipamentoRepository extends JpaRepository<Equipamento, Long> {

    @Query("select " +
            "distinct e " +
            "from Equipamento e "
            + "where e.deleted=false and "
            + " e.categoria.nome like :search% and e.deleted = false OR"
            + " e.departamento.nome like :search% and e.deleted = false OR"
            + " e.enderecoMac like :search% and e.deleted = false OR"
            + " e.modelo like :search% and e.deleted = false OR"
            + " e.nome like :search% and e.deleted = false OR"
            + " e.fabricante like :search% and e.deleted = false")
    Page<Equipamento> findByName(String search, Pageable pageable);

    @Query("select e from Equipamento e where  e.status = 'EMUSO' and e.deleted=false")
    List<Equipamento> getEquipamentosByStatusEmuso();

    @Query("select e from Equipamento e where  e.status = 'DEPOSITO' and e.deleted=false")
    List<Equipamento> getEquipamentosByStatusDeposito();

    @Query("select e from Equipamento e where  e.status = 'MANUTENCAO'and e.deleted=false")
    List<Equipamento> getEquipamentosByStatusManutencao();

    //lista de todos os equipamentos com deleted=false
    List<Equipamento> getAllByDeletedFalse();

    @Modifying
    @Query("UPDATE Equipamento e set e.deleted = true WHERE e.id = ?1")
    int deleteEquipamento(Long id);

    Page<Equipamento> getEquipamentosByDeletedFalse(Pageable pageable);

    @Query("select distinct e.id as id, e.tomboPatrimonial as tombo, e.nome as nome, e.enderecoMac as mac from Equipamento e where e.enderecoMac like %:mac% or e.tomboPatrimonial like %:mac% or e.nome like %:mac%")
    List<Tuple> findEquipamentoByEnderecoMacOrTomboPatrimonialOrNome(String mac);

}

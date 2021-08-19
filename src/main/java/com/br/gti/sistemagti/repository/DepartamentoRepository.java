package com.br.gti.sistemagti.repository;

import com.br.gti.sistemagti.domain.Departamento;
import com.br.gti.sistemagti.domain.Equipamento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {

    @Query("select distinct d from Departamento d "
            + "where d.deleted = false and d.nome like %:search% OR d.ambiente like %:search%")
    Page<Departamento> findByNameOrAmbiente(String search, Pageable pageable);

    @Query("select distinct d.nome from Departamento d order by d.nome asc")
    List<String> findDepartamentos();

    //lista de todos os departamentos com deleted=false
    List<Departamento> getAllByDeletedFalse();

    @Modifying
    @Query("UPDATE Departamento d set d.deleted = true WHERE d.id = ?1")
    int deleteDepartamento(Long id);

    Page<Departamento> getDepartamentosByDeletedFalse(Pageable pageable);
}

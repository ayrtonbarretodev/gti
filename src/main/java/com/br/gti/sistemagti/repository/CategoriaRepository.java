package com.br.gti.sistemagti.repository;

import com.br.gti.sistemagti.domain.Categoria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    @Query("select distinct c from Categoria c "
            + "where c.nome like %:search%")
    Page<Categoria> findByName(String search, Pageable pageable);

    @Query("select c.nome from Categoria c order by c.nome asc")
    List<String> findCategorias();
}

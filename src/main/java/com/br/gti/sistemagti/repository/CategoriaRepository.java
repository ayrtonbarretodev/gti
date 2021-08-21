package com.br.gti.sistemagti.repository;

import com.br.gti.sistemagti.domain.Categoria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    @Query("select distinct c from Categoria c "
            + "where c.deleted = false and c.nome like %:search%")
    Page<Categoria> findByName(String search, Pageable pageable);

    @Query("select c.nome from Categoria c where c.deleted=false order by c.nome asc")
    List<String> findCategorias();

    //lista de todas as categorias com deleted=false
    List<Categoria> getAllByDeletedFalse();

    @Modifying
    @Query("UPDATE Categoria c set c.deleted = true WHERE c.id = ?1")
    int deleteCategoria(Long id);

    @Modifying
    @Query("UPDATE Categoria c set c.deleted = false WHERE c.nome = ?1")
    void reativarCategoria(String nome);

    Page<Categoria> getCategoriaByDeletedFalse(Pageable pageable);

    Categoria findByNome(String nome);
}

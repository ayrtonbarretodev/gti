package com.br.gti.sistemagti.dao;

import com.br.gti.sistemagti.domain.Categoria;
import com.br.gti.sistemagti.util.PaginacaoUtil;

import java.util.List;
import java.util.Optional;

public interface CategoriaDao {
    void save(Categoria categoria);

    void update(Categoria categoria);

    void delete(Long id);

    Categoria findById(Long id);

    List<Categoria> findAll();

    List<Categoria> findByNome(String nome);

    PaginacaoUtil<Categoria> buscaPaginada (int pagina, String direcao);

    PaginacaoUtil<Categoria> buscaPorNome (int pagina, String direcao, String nome);
}

package com.br.gti.sistemagti.service;

import com.br.gti.sistemagti.domain.Categoria;

import java.util.List;

public interface CategoriaService {

    void salvar (Categoria categoria);

    void editar (Categoria categoria);

    void excluir (Long id);

    Categoria buscarPorId(Long id);

    List<Categoria> buscarTodos();

    boolean categoriaTemEquipamentos(Long id);
}

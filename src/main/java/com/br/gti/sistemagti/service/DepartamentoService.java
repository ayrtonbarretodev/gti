package com.br.gti.sistemagti.service;

import com.br.gti.sistemagti.domain.Departamento;

import java.util.List;

public interface DepartamentoService {
    void salvar (Departamento departamento);

    void editar (Departamento departamento);

    void excluir (Long id);

    Departamento buscarPorId(Long id);

    List<Departamento> buscarTodos();
}

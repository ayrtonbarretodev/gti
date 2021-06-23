package com.br.gti.sistemagti.service;

import com.br.gti.sistemagti.domain.Equipamento;

import java.util.List;

public interface EquipamentoService {
    void salvar (Equipamento equipamento);

    void editar (Equipamento equipamento);

    void excluir (Long id);

    Equipamento buscarPorId(Long id);

    List<Equipamento> buscarTodos();
}

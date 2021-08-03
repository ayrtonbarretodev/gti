package com.br.gti.sistemagti.service;

import com.br.gti.sistemagti.domain.Equipamento;
import com.br.gti.sistemagti.util.PaginacaoUtil;

import java.util.List;

public interface EquipamentoService {
    void salvar(Equipamento equipamento);

    void editar(Equipamento equipamento);

    void excluir(Long id);

    Equipamento buscarPorId(Long id);

    List<Equipamento> buscarTodos();

    PaginacaoUtil<Equipamento> buscaPorPagina(int pagina, String direcao);
}

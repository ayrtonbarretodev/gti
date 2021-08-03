package com.br.gti.sistemagti.dao;

import com.br.gti.sistemagti.domain.Equipamento;
import com.br.gti.sistemagti.util.PaginacaoUtil;

import java.util.List;

public interface EquipamentoDao {

    void save(Equipamento equipamento);

    void update(Equipamento equipamento);

    void delete(Long id);

    Equipamento findById(Long id);

    List<Equipamento> findAll();

    PaginacaoUtil<Equipamento> buscaPaginada(int pagina, String direcao);
}

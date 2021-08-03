package com.br.gti.sistemagti.dao;


import com.br.gti.sistemagti.domain.Departamento;
import com.br.gti.sistemagti.util.PaginacaoUtil;

import java.util.List;

public interface DepartamentoDao {

    void save(Departamento departamento);

    void update(Departamento departamento);

    void delete(Long id);

    Departamento findById(Long id);

    List<Departamento> findAll();

    List<Departamento> findByNome(String nome);

    List<Departamento> findByAmbiente(String ambiente);

    PaginacaoUtil<Departamento> buscaPaginada(int pagina, String direcao);

    PaginacaoUtil<Departamento> buscarPorNome(int pagina, String direcao, String nome);
}

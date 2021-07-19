package com.br.gti.sistemagti.dao;

import com.br.gti.sistemagti.domain.Departamento;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DepartamentoDaoImpl extends AbstractDao<Departamento, Long> implements DepartamentoDao {
    @Override
    public List<Departamento> findByNome(String nome) {
        return createQuery("select d from Departamento d where d.nome like concat('%',?1,'%')",nome);
    }

    @Override
    public List<Departamento> findByAmbiente(String ambiente) {
        return createQuery("select d from Departamento d where d.ambiente like concat('%',?1,'%')",ambiente);
    }
}

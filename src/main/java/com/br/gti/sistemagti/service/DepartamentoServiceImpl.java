package com.br.gti.sistemagti.service;

import com.br.gti.sistemagti.dao.DepartamentoDao;
import com.br.gti.sistemagti.domain.Departamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service @Transactional
public class DepartamentoServiceImpl implements DepartamentoService{

    @Autowired
    private DepartamentoDao dao;

    @Override
    public void salvar(Departamento departamento) {
        dao.save(departamento);
    }

    @Override
    public void editar(Departamento departamento) {
        dao.update(departamento);
    }

    @Override
    public void excluir(Long id) {
        dao.delete(id);
    }

    @Override  @Transactional (readOnly = true)
    public Departamento buscarPorId(Long id) {
        return null;
    }

    @Override @Transactional (readOnly = true)
    public List<Departamento> buscarTodos() {
        return null;
    }
}

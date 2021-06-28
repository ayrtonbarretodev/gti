package com.br.gti.sistemagti.service;

import com.br.gti.sistemagti.dao.CategoriaDao;
import com.br.gti.sistemagti.domain.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service @Transactional
public class CategoriaServiceImpl implements CategoriaService{

    @Autowired
    private CategoriaDao dao;

    @Override
    public void salvar(Categoria categoria) {
        dao.save(categoria);
    }

    @Override
    public void editar(Categoria categoria) {
        dao.update(categoria);
    }

    @Override
    public void excluir(Long id) {
        dao.delete(id);
    }

    @Override
    public Categoria buscarPorId(Long id) {
        return dao.findById(id);
    }

    @Override
    public List<Categoria> buscarTodos() {
        return dao.findAll();
    }
}
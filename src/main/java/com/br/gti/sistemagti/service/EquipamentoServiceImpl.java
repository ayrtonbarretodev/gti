package com.br.gti.sistemagti.service;

import com.br.gti.sistemagti.dao.EquipamentoDao;
import com.br.gti.sistemagti.domain.Equipamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EquipamentoServiceImpl implements EquipamentoService {

    @Autowired
    private EquipamentoDao dao;

    @Override
    public void salvar(Equipamento equipamento) {
        dao.save(equipamento);
    }

    @Override
    public void editar(Equipamento equipamento) {
        dao.update(equipamento);
    }

    @Override
    public void excluir(Long id) {
        dao.delete(id);
    }

    @Override
    public Equipamento buscarPorId(Long id) {
        return dao.findById(id);
    }

    @Override
    public List<Equipamento> buscarTodos() {
        return dao.findAll();
    }
}

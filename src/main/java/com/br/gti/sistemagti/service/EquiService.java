package com.br.gti.sistemagti.service;

import com.br.gti.sistemagti.datatables.Datatables;
import com.br.gti.sistemagti.datatables.DatatablesColunas;
import com.br.gti.sistemagti.domain.Departamento;
import com.br.gti.sistemagti.domain.Equipamento;
import com.br.gti.sistemagti.repository.EquipamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Service
public class EquiService {

    @Autowired
    private EquipamentoRepository repository;

    @Autowired
    private Datatables datatables;

    @Transactional(readOnly = true)
    public Map<String,Object> buscarTodos(HttpServletRequest request) {
        datatables.setRequest(request);
        datatables.setColunas(DatatablesColunas.EQUIPAMENTOS);
        Page<Equipamento> page = datatables.getSearch().isEmpty()
                ? repository.getEquipamentosByDeletedFalse(datatables.getPageable())
                : repository.findByName(datatables.getSearch(), datatables.getPageable());
        return datatables.getResponse(page);
    }

    @Transactional(readOnly = false)
    public void salvarEquipamento(Equipamento equipamento) {
        repository.save(equipamento);
    }

    @Transactional(readOnly = true)
    public Equipamento buscarPorId(Long id) {
        return repository.findById(id).get();
    }

    @Transactional(readOnly = true)
    public List<Equipamento> buscarTodosEquipamentos() {
        return repository.getAllByDeletedFalse();
    }

    @Transactional(readOnly = true)
    public List<Equipamento> buscarTodosEquipamentosEmUso() {
        return repository.getEquipamentosByStatusEmuso();
    }

    @Transactional(readOnly = true)
    public List<Equipamento> buscarTodosEquipamentosDeposito() {
        return repository.getEquipamentosByStatusDeposito();
    }

    @Transactional(readOnly = true)
    public List<Equipamento> buscarTodosEquipamentosManutencao() {
        return repository.getEquipamentosByStatusManutencao();
    }

    @Transactional(readOnly = false)
    public void deletarEquipamento(Long id) {
        repository.deleteEquipamento(id);
    }

    @Transactional(readOnly = false)
    public void editarEquipamento(Equipamento equipamento) {
        Equipamento e = repository.findById(equipamento.getId()).get();
        e.setNome(equipamento.getNome());
        e.setModelo(equipamento.getModelo());
        e.setFabricante(equipamento.getFabricante());
        e.setTomboPatrimonial(equipamento.getTomboPatrimonial());
        e.setCategoria(equipamento.getCategoria());
        e.setDepartamento(equipamento.getDepartamento());
        e.setDataEntrada(equipamento.getDataEntrada());
        e.setEnderecoMac(equipamento.getEnderecoMac());
        e.setNumeroSerie(equipamento.getNumeroSerie());
        e.setStatus(equipamento.getStatus());
    }
}

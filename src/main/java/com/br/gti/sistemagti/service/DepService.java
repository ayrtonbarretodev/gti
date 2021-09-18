package com.br.gti.sistemagti.service;

import com.br.gti.sistemagti.datatables.Datatables;
import com.br.gti.sistemagti.datatables.DatatablesColunas;
import com.br.gti.sistemagti.domain.Departamento;
import com.br.gti.sistemagti.repository.DepartamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Service
public class DepService {

    @Autowired
    private DepartamentoRepository repository;


    @Autowired
    private Datatables datatables;


    @Transactional(readOnly = true)
    public Map<String,Object> buscarTodos(HttpServletRequest request) {
        datatables.setRequest(request);
        datatables.setColunas(DatatablesColunas.DEPARTAMENTOS);
        Page<Departamento> page = datatables.getSearch().isEmpty()
                ? repository.getDepartamentosByDeletedFalse(datatables.getPageable())
                : repository.findByNameOrAmbiente(datatables.getSearch(), datatables.getPageable());
        return datatables.getResponse(page);
    }

    @Transactional(readOnly = false)
    public void salvarDepartamento(Departamento departamento) {
        repository.save(departamento);
    }

    @Transactional(readOnly = true)
    public Departamento buscarPorId(Long id) {
        return repository.findById(id).get();
    }

    @Transactional(readOnly = true)
    public List<Departamento> buscarTodosDepartamentos() {
        return repository.getAllByDeletedFalse();
    }

    @Transactional(readOnly = false)
    public void deletarDepartamento(Long id) {
        repository.deleteDepartamento(id);
    }


    public boolean departamentoTemEquipamentos(Long id) {
        if (buscarPorId(id).getEquipamentos().isEmpty()) {
            return false;
        }
        return true;

    }

    @Transactional(readOnly = false)
    public void editarDepartamento(Departamento departamento) {
        Departamento d = repository.findById(departamento.getId()).get();
        d.setNome(departamento.getNome());
        d.setAmbiente(departamento.getAmbiente());
        d.setTelefone(departamento.getTelefone());
    }

}

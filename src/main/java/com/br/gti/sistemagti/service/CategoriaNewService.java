package com.br.gti.sistemagti.service;

import com.br.gti.sistemagti.datatables.Datatables;
import com.br.gti.sistemagti.datatables.DatatablesColunas;
import com.br.gti.sistemagti.domain.Categoria;
import com.br.gti.sistemagti.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Service
public class CategoriaNewService {

    @Autowired
    private CategoriaRepository repository;

    @Autowired
    private Datatables datatables;

    @Transactional(readOnly = true)
    public Map<String,Object> buscarTodos(HttpServletRequest request) {
        datatables.setRequest(request);
        datatables.setColunas(DatatablesColunas.CATEGORIAS);
        Page<Categoria> page = datatables.getSearch().isEmpty()
                ? repository.findAll(datatables.getPageable())
                : repository.findByName(datatables.getSearch(), datatables.getPageable());
        return datatables.getResponse(page);
    }

    @Transactional(readOnly = false)
    public void salvarCategoria(Categoria categoria) {
        repository.save(categoria);
    }


    @Transactional(readOnly = false)
    public void editarCategoria(Categoria categoria) {
        Categoria c = repository.findById(categoria.getId()).get();
        c.setNome(categoria.getNome());
    }

    @Transactional(readOnly = false)
    public void deletarCategoria(Long id) {
        repository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Categoria buscarPorId(Long id) {
        return repository.findById(id).get();
    }

    public boolean categoriaTemEquipamentos(Long id) {
        if (buscarPorId(id).getEquipamentos().isEmpty()) {
            return false;
        }
        return true;
    }

    @Transactional(readOnly = true)
    public List<Categoria> buscarTodasCategorias() {
        return repository.findAll();
    }

}

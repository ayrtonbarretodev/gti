package com.br.gti.sistemagti.service;

import com.br.gti.sistemagti.dao.CategoriaDao;
import com.br.gti.sistemagti.domain.Categoria;
import com.br.gti.sistemagti.util.PaginacaoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CategoriaServiceImpl implements CategoriaService {

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
    @Transactional(readOnly = true)
    public Categoria buscarPorId(Long id) {
        return dao.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Categoria> buscarTodos() {
        return dao.findAll();
    }

    @Override
    public boolean categoriaTemEquipamentos(Long id) {
        if (buscarPorId(id).getEquipamentos().isEmpty()) {
            return false;
        }
        return true;
    }

    @Override
    public List<Categoria> buscarPorNome(String nome) {
        return dao.findByNome(nome);
    }

    @Override
    public PaginacaoUtil<Categoria> buscaPorPagina(int pagina, String direcao) {
        return dao.buscaPaginada(pagina, direcao);
    }

    @Override
    public PaginacaoUtil<Categoria> buscaPorNome(int pagina, String direcao, String nome) {
        return dao.buscaPorNome(pagina, direcao, nome);
    }


}

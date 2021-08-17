package com.br.gti.sistemagti.service;

//import com.br.gti.sistemagti.dao.DepartamentoDao;
//import com.br.gti.sistemagti.domain.Departamento;
//import com.br.gti.sistemagti.util.PaginacaoUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;

//import java.util.List;
//
//@Service
//@Transactional
//public class DepartamentoServiceImpl implements DepartamentoService {
//
//    @Autowired
//    private DepartamentoDao dao;
//
//    @Override
//    public void salvar(Departamento departamento) {
//        dao.save(departamento);
//    }
//
//    @Override
//    public void editar(Departamento departamento) {
//        dao.update(departamento);
//    }
//
//    @Override
//    public void excluir(Long id) {
//        dao.delete(id);
//    }
//
//    @Override
//    @Transactional(readOnly = true)
//    public Departamento buscarPorId(Long id) {
//        return dao.findById(id);
//    }
//
//    @Override
//    @Transactional(readOnly = true)
//    public List<Departamento> buscarTodos() {
//        return dao.findAll();
//    }
//
//    @Override
//    public boolean departamentoTemEquipamento(Long id) {
//        if (buscarPorId(id).getEquipamentos().isEmpty()) {
//            return false;
//        }
//        return true;
//    }
//
//    @Override
//    public List<Departamento> buscarPorNome(String nome) {
//        return dao.findByNome(nome);
//    }
//
//    @Override
//    public List<Departamento> buscarPorAmbiente(String ambiente) {
//        return dao.findByAmbiente(ambiente);
//    }
//
//    @Override
//    public PaginacaoUtil<Departamento> buscaPorPagina(int pagina, String direcao) {
//        return dao.buscaPaginada(pagina, direcao);
//    }
//
//    @Override
//    public PaginacaoUtil<Departamento> buscarPorNome(int pagina, String direcao, String nome) {
//        return dao.buscarPorNome(pagina, direcao, nome);
//    }
//}

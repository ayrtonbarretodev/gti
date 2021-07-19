package com.br.gti.sistemagti.dao;

import com.br.gti.sistemagti.domain.Categoria;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoriaDaoImpl extends AbstractDao<Categoria, Long> implements CategoriaDao {
    @Override
    public List<Categoria> findByNome(String nome) {
        return createQuery("select c from Categoria c where c.nome like concat('%',?1,'%')", nome);
    }
}
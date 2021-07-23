package com.br.gti.sistemagti.dao;

import com.br.gti.sistemagti.domain.Categoria;
import com.br.gti.sistemagti.util.PaginacaoUtil;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CategoriaDaoImpl extends AbstractDao<Categoria, Long> implements CategoriaDao {

    @Override
    public List<Categoria> findByNome(String nome) {
        return createQuery("select c from Categoria c where c.nome like concat('%',?1,'%')", nome);
    }


    public PaginacaoUtil<Categoria> buscaPaginada (int pagina, String direcao){
        int tamanho = 10;
        int inicio = (pagina-1) * tamanho;
        List<Categoria> categorias = getEntityManager()
                .createQuery("select c from Categoria c order by c.nome " + direcao,Categoria.class)
                .setFirstResult(inicio)
                .setMaxResults(tamanho)
                .getResultList();

        long totalRegistros = count();
        long totalDePaginas = (totalRegistros + (tamanho-1))/tamanho;

        return new PaginacaoUtil<>(tamanho,pagina,totalDePaginas,direcao,categorias);
    }

    public PaginacaoUtil<Categoria> buscaPorNome (int pagina, String direcao, String nome){
        int tamanho = 5;
        int inicio = (pagina-1) * tamanho;
        List<Categoria> categorias = getEntityManager()
                .createQuery("select c from Categoria c where c.nome like concat('%',?1,'%') order by c.nome " + direcao,Categoria.class)
                .setFirstResult(inicio)
                .setMaxResults(tamanho)
                .setParameter(1,nome)
                .getResultList();

        long totalRegistros = count();
        long totalDePaginas = (totalRegistros + (tamanho-1))/tamanho;

        return new PaginacaoUtil<>(tamanho,pagina,totalDePaginas,direcao,categorias);
    }

    public long count(){ //retorna total de registros na tabela
        return getEntityManager()
                .createQuery("select count(nome) from Categoria", Long.class)
                .getSingleResult();
    }
}
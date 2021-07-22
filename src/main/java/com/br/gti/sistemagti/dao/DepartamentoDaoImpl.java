package com.br.gti.sistemagti.dao;

import com.br.gti.sistemagti.domain.Categoria;
import com.br.gti.sistemagti.domain.Departamento;
import com.br.gti.sistemagti.util.PaginacaoUtil;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DepartamentoDaoImpl extends AbstractDao<Departamento, Long> implements DepartamentoDao {
    @Override
    public List<Departamento> findByNome(String nome) {
        return createQuery("select d from Departamento d where d.nome like concat('%',?1,'%')",nome);
    }

    @Override
    public List<Departamento> findByAmbiente(String ambiente) {
        return createQuery("select d from Departamento d where d.ambiente like concat('%',?1,'%')",ambiente);
    }

    @Override
    public PaginacaoUtil<Departamento> buscaPaginada(int pagina, String direcao) {
        int tamanho = 5;
        int inicio = (pagina-1) * tamanho;
        List<Departamento> departamentos = getEntityManager()
                .createQuery("select d from Departamento d order by d.nome " + direcao,Departamento.class)
                .setFirstResult(inicio)
                .setMaxResults(tamanho)
                .getResultList();

        long totalRegistros = count();
        long totalDePaginas = (totalRegistros + (tamanho-1))/tamanho;

        return new PaginacaoUtil<>(tamanho,pagina,totalDePaginas,direcao,departamentos);
    }

    @Override
    public PaginacaoUtil<Departamento> buscarPorNome(int pagina, String direcao, String nome) {
        int tamanho = 5;
        int inicio = (pagina-1) * tamanho;
        List<Departamento> departamentos = getEntityManager()
                .createQuery("select d from Departamento d where d.nome like concat('%',?1,'%') order by d.nome " + direcao,Departamento.class)
                .setFirstResult(inicio)
                .setMaxResults(tamanho)
                .setParameter(1,nome)
                .getResultList();

        long totalRegistros = count();
        long totalDePaginas = (totalRegistros + (tamanho-1))/tamanho;

        return new PaginacaoUtil<>(tamanho,pagina,totalDePaginas,direcao,departamentos);
    }

    public long count(){ //retorna total de registros na tabela
        return getEntityManager()
                .createQuery("select count(nome) from Departamento ", Long.class)
                .getSingleResult();
    }
}

package com.br.gti.sistemagti.dao;


import com.br.gti.sistemagti.domain.Equipamento;
import com.br.gti.sistemagti.util.PaginacaoUtil;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EquipamentoDaoImpl extends AbstractDao<Equipamento, Long> implements EquipamentoDao {


    public PaginacaoUtil<Equipamento> buscaPaginada(int pagina, String direcao) {
        int tamanho = 5;
        int inicio = (pagina - 1) * tamanho;
        List<Equipamento> equipamentos = getEntityManager()
                .createQuery("select e from Equipamento e order by e.nome " + direcao, Equipamento.class)
                .setFirstResult(inicio)
                .setMaxResults(tamanho)
                .getResultList();

        long totalRegistros = count();
        long totalDePaginas = (totalRegistros + (tamanho - 1)) / tamanho;

        return new PaginacaoUtil<>(tamanho, pagina, totalDePaginas, direcao, equipamentos);
    }

    public long count() { //retorna total de registros na tabela
        return getEntityManager()
                .createQuery("select count(nome) from Equipamento", Long.class)
                .getSingleResult();
    }
}

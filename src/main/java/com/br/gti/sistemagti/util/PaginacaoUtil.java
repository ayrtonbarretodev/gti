package com.br.gti.sistemagti.util;

import java.util.List;

public class PaginacaoUtil<T> {
    private int tamanho; //tamanho de linhas que a página terá
    private int pagina; //verifica a página atual selecionada
    private long totalDePaginas; //armazena o total de páginas contidos no sistema de paginação
    private String direcao;
    private List<T> registros; //recebe o resultado da consulta realizada no BD

    public PaginacaoUtil(int tamanho, int pagina, long totalDePaginas, String direcao, List<T> registros) {
        this.tamanho = tamanho;
        this.pagina = pagina;
        this.totalDePaginas = totalDePaginas;
        this.direcao = direcao;
        this.registros = registros;
    }

    public String getDirecao() {
        return direcao;
    }

    public int getTamanho() {
        return tamanho;
    }

    public int getPagina() {
        return pagina;
    }

    public long getTotalDePaginas() {
        return totalDePaginas;
    }

    public List<T> getRegistros() {
        return registros;
    }
}

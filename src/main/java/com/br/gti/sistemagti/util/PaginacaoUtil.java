package com.br.gti.sistemagti.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;


@Getter
@AllArgsConstructor
public class PaginacaoUtil<T> {
    private int tamanho; //tamanho de linhas que a página terá
    private int pagina; //verifica a página atual selecionada
    private long totalDePaginas; //armazena o total de páginas contidos no sistema de paginação
    private String direcao;
    private List<T> registros; //recebe o resultado da consulta realizada no BD
}

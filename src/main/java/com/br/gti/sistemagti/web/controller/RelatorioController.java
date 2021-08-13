package com.br.gti.sistemagti.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.Connection;

@Controller
public class RelatorioController {

    @Autowired
    private Connection connection;

    @GetMapping("/relatorios")
    public String PaginaRelatorios(){
        return "relatorio/relatorios";
    }
}

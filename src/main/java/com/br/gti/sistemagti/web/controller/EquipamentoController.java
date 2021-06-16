package com.br.gti.sistemagti.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/equipamentos")
public class EquipamentoController {

    @GetMapping("/cadastrar")
    public String cadastrar(){
        return "/equipamento/cadastro";
    }

    @GetMapping("/listar")
    public String listar(){
        return "/equipamento/lista";
    }
}

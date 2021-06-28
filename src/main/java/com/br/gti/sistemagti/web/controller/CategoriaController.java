package com.br.gti.sistemagti.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/categorias")
public class CategoriaController {

    @GetMapping("/cadastrar")
    public String cadastrar(){
        return "/categoria/cadastro";
    }

    @GetMapping("/listar")
    public String listar(){
        return "/categoria/lista";
    }
}
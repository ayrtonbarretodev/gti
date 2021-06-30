package com.br.gti.sistemagti.web.controller;

import com.br.gti.sistemagti.domain.Categoria;
import com.br.gti.sistemagti.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService service;

    @GetMapping("/cadastrar")
    public String cadastrar(Categoria categoria){
        return "/categoria/cadastro";
    }

    @GetMapping("/listar")
    public String listar(){
        return "/categoria/lista";
    }

    @PostMapping("/salvar")
    public String salvar(Categoria categoria){
        service.salvar(categoria);
        return "redirect:/categorias/cadastrar";
    }
}

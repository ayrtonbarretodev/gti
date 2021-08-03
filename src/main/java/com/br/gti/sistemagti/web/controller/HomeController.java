package com.br.gti.sistemagti.web.controller;

import com.br.gti.sistemagti.service.CategoriaService;
import com.br.gti.sistemagti.service.DepartamentoService;
import com.br.gti.sistemagti.service.EquipamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class HomeController {

    @Autowired
    private DepartamentoService departamentoService;

    @Autowired
    private EquipamentoService equipamentoService;

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping({"/home"})
    public String home() {
        return "home";
    }


    @ModelAttribute("qtdDepartamentos")
    public int totalDepartamentos() {
        return departamentoService.buscarTodos().size();
    }

    @ModelAttribute("qtdEquipamentos")
    public int totalEquipamentos() {
        return equipamentoService.buscarTodos().size();
    }

    @ModelAttribute("qtdCategorias")
    public int totalCategorias() {
        return categoriaService.buscarTodos().size();
    }
}

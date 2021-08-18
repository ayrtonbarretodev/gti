package com.br.gti.sistemagti.web.controller;

import com.br.gti.sistemagti.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class HomeController {

    @Autowired
    private EquiService equiService;

    @Autowired
    private DepService depService;

    @Autowired
    private CategoriaNewService categoriaNewService;

    @GetMapping({"/home"})
    public String home() {
        return "home";
    }

    @ModelAttribute("qtdDepartamentos")
    public int totalDepartamentos() {
        return depService.buscarTodosDepartamentos().size();
    }

    @ModelAttribute("qtdEquipamentos")
    public int totalEquipamentos() {
        return equiService.buscarTodosEquipamentos().size();
    }

    @ModelAttribute("qtdCategorias")
    public int totalCategorias() {
        return categoriaNewService.buscarTodasCategorias().size();
    }

    @ModelAttribute("qtdEquipamentosEmUso")
    public int totalEquipamentosEmUso() {
        return equiService.buscarTodosEquipamentosEmUso().size();
    }

    @ModelAttribute("qtdEquipamentosDeposito")
    public int totalEquipamentosDeposito() {
        return equiService.buscarTodosEquipamentosDeposito().size();
    }

    @ModelAttribute("qtdEquipamentosManutencao")
    public int totalEquipamentosManutencao() {
        return equiService.buscarTodosEquipamentosManutencao().size();
    }
}

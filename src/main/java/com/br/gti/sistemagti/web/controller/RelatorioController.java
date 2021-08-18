package com.br.gti.sistemagti.web.controller;

import com.br.gti.sistemagti.domain.enums.Status;
import com.br.gti.sistemagti.repository.CategoriaRepository;
import com.br.gti.sistemagti.repository.DepartamentoRepository;
import com.br.gti.sistemagti.repository.EquipamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

@Controller
public class RelatorioController {

    @Autowired
    private Connection connection;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private DepartamentoRepository departamentoRepository;

    @GetMapping("/relatorios")
    public String PaginaRelatorios(){
        return "relatorio/relatorios";
    }

    @ModelAttribute("categorias")
    public List<String> getCategorias(){
        return categoriaRepository.findCategorias();
    }

    @ModelAttribute("departamentos")
    public List<String> getDepartamentos(){
        return departamentoRepository.findDepartamentos();
    }

    @ModelAttribute("status")
    public Status[] getStatus() {
        return Status.values();
    }

}

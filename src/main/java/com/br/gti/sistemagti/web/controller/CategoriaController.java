package com.br.gti.sistemagti.web.controller;

import com.br.gti.sistemagti.domain.Categoria;
import com.br.gti.sistemagti.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public String listar(ModelMap model){
        model.addAttribute("categorias",service.buscarTodos());
        return "/categoria/lista";
    }

    @PostMapping("/salvar")
    public String salvar(Categoria categoria){
        service.salvar(categoria);
        return "redirect:/categorias/cadastrar";
    }

    @GetMapping("/editar/{id}")
    public String preEditar(@PathVariable("id") Long id, ModelMap model){
        model.addAttribute("categoria",service.buscarPorId(id));
        return "/categoria/cadastro";
    }

    @PostMapping("/editar")
    public String editar(Categoria categoria){
        service.editar(categoria);
        return "redirect:/categorias/cadastrar";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") Long id, ModelMap model){
        if(!service.categoriaTemEquipamentos(id)){
            service.excluir(id);
        }
        return listar(model);
    }

}

package com.br.gti.sistemagti.web.controller;

import com.br.gti.sistemagti.domain.Categoria;
import com.br.gti.sistemagti.domain.Departamento;
import com.br.gti.sistemagti.domain.Equipamento;
import com.br.gti.sistemagti.domain.enums.Status;
import com.br.gti.sistemagti.service.CategoriaService;
import com.br.gti.sistemagti.service.DepartamentoService;
import com.br.gti.sistemagti.service.EquipamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/equipamentos")
public class EquipamentoController {

    @Autowired
    private EquipamentoService equipamentoService;

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private DepartamentoService departamentoService;

    @GetMapping("/cadastrar")
    public String cadastrar(Equipamento equipamento){
        return "equipamento/cadastro";
    }

    @GetMapping("/listar")
    public String listar(ModelMap model) {
        model.addAttribute("equipamentos",equipamentoService.buscarTodos());
        return "/equipamento/lista";
    }

    @PostMapping("/salvar")
    public String salvar (Equipamento equipamento, RedirectAttributes attr){
        equipamentoService.salvar(equipamento);
        System.out.println(equipamento.getDepartamento().getNome());
        System.out.println(equipamento.getCategoria().getNome());
        attr.addFlashAttribute("success", "Equipamento inserido com sucesso");
        return "redirect:/equipamentos/cadastrar";
    }

    @ModelAttribute("categorias")
    public List<Categoria> listaDeCategorias(){
        return categoriaService.buscarTodos();
    }

    @ModelAttribute("departamentos")
    public List<Departamento> listaDeDepartamentos(){
        return departamentoService.buscarTodos();
    }

    @ModelAttribute("status")
    public Status[] getStatus(){
        return Status.values();
    }


}

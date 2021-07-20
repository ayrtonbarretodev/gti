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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
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
    public String cadastrar(Equipamento equipamento) {
        return "equipamento/cadastro";
    }

    @GetMapping("/listar")
    public String listar(ModelMap model) {
        model.addAttribute("equipamentos", equipamentoService.buscarTodos());
        return "equipamento/lista";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid Equipamento equipamento, BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()){
            return "equipamento/cadastro";
        }
        equipamentoService.salvar(equipamento);
        attr.addFlashAttribute("success", "Equipamento inserido com sucesso");
        return "redirect:/equipamentos/cadastrar";
    }

    @GetMapping("/editar/{id}")
    public String preEditar(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("equipamento", equipamentoService.buscarPorId(id));
        return "equipamento/cadastro";
    }

    @PostMapping("/editar")
    public String editar(@Valid Equipamento equipamento, BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()){
            return "equipamento/cadastro";
        }
        equipamentoService.editar(equipamento);
        attr.addFlashAttribute("success", "Equipamento editado com sucesso");
        return "redirect:/equipamentos/cadastrar";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") Long id, RedirectAttributes attr) {
        equipamentoService.excluir(id);
        attr.addFlashAttribute("success", "Equipamento removido com sucesso.");
        return "redirect:/equipamentos/listar";
    }

    @ModelAttribute("categorias")
    public List<Categoria> listaDeCategorias() {
        return categoriaService.buscarTodos();
    }

    @ModelAttribute("departamentos")
    public List<Departamento> listaDeDepartamentos() {
        return departamentoService.buscarTodos();
    }

    @ModelAttribute("status")
    public Status[] getStatus() {
        return Status.values();
    }

}

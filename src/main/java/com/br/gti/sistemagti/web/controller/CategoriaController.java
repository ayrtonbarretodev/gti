package com.br.gti.sistemagti.web.controller;

import com.br.gti.sistemagti.domain.Categoria;
import com.br.gti.sistemagti.service.CategoriaService;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService service;

    @GetMapping("/cadastrar")
    public String cadastrar(Categoria categoria) {
        return "/categoria/cadastro";
    }

    @GetMapping("/listar")
    public String listar(ModelMap model) {
        model.addAttribute("categorias", service.buscarTodos());
        return "/categoria/lista";
    }

    @PostMapping("/salvar")
    public String salvar(Categoria categoria, RedirectAttributes attr) {
        service.salvar(categoria);
        attr.addFlashAttribute("success", "Categoria inserida com sucesso");
        return "redirect:/categorias/cadastrar";
    }

    @GetMapping("/editar/{id}")
    public String preEditar(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("categoria", service.buscarPorId(id));
        return "/categoria/cadastro";
    }

    @PostMapping("/editar")
    public String editar(Categoria categoria, RedirectAttributes attr) {
        service.editar(categoria);
        attr.addFlashAttribute("success", "Categoria editada com sucesso");
        return "redirect:/categorias/cadastrar";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") Long id, RedirectAttributes attr) {
        if (service.categoriaTemEquipamentos(id)) {
            attr.addFlashAttribute("fail", "Categoria não removida, possui equipamento(s) vinculado(s).");
        } else {
            service.excluir(id);
            attr.addFlashAttribute("success", "Categoria excluída com sucesso.");
        }
        return "redirect:/categorias/listar";
    }

    @GetMapping("buscar/nome")
    public String getPorNome(@RequestParam("nome") String nome, ModelMap model) {
        model.addAttribute("categorias", service.buscarPorNome(nome));
        return "/categoria/lista";
    }



}

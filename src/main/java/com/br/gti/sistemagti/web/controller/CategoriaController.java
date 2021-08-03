package com.br.gti.sistemagti.web.controller;

import com.br.gti.sistemagti.domain.Categoria;
import com.br.gti.sistemagti.service.CategoriaService;
import com.br.gti.sistemagti.util.PaginacaoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Optional;


@Controller
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService service;

    @GetMapping("/cadastrar")
    public String cadastrar(Categoria categoria) {
        return "categoria/cadastro";
    }

    @GetMapping("/listar")
    public String listar(ModelMap model,
                         @RequestParam("page") Optional<Integer> page,
                         @RequestParam("dir") Optional<String> dir) {

        int paginaAtual = page.orElse(1);
        String ordem = dir.orElse("asc");

        PaginacaoUtil<Categoria> pageCategoria = service.buscaPorPagina(paginaAtual, ordem);

        model.addAttribute("pageCategoria", pageCategoria);
        return "categoria/lista";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid Categoria categoria, BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) {
            return "categoria/cadastro";
        }

        service.salvar(categoria);
        attr.addFlashAttribute("success", "Categoria inserida com sucesso");
        return "redirect:/categorias/cadastrar";
    }

    @GetMapping("/editar/{id}")
    public String preEditar(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("categoria", service.buscarPorId(id));
        return "categoria/cadastro";
    }

    @PostMapping("/editar")
    public String editar(@Valid Categoria categoria, BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) {
            return "categoria/cadastro";
        }
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
    public String getPorNome(ModelMap model,
                             @RequestParam("page") Optional<Integer> page,
                             @RequestParam("dir") Optional<String> dir,
                             @RequestParam("nome") Optional<String> nome) {
        int paginaAtual = page.orElse(1);
        String ordem = dir.orElse("asc");
        String name = nome.orElse("");

        PaginacaoUtil<Categoria> pageNome = service.buscaPorNome(paginaAtual, ordem, name);

        model.addAttribute("pageCategoria", pageNome);
        return "categoria/lista";
    }

}

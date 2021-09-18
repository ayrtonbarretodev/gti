package com.br.gti.sistemagti.web.controller;

import com.br.gti.sistemagti.domain.Categoria;
import com.br.gti.sistemagti.service.CategoriaNewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.DateFormat;
import java.util.Date;


@Controller
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaNewService categoriaNewService;

    @GetMapping("/cadastrar")
    public String cadastrar(Categoria categoria) {
        return "categoria/cadastro";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid Categoria categoria, BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) {
            return "categoria/cadastro";
        }


        Categoria categoriaExiste = categoriaNewService.buscarPorNome(categoria.getNome());

        if (categoriaExiste == null){
            categoriaNewService.salvarCategoria(categoria);
            attr.addFlashAttribute("success", "Categoria inserida com sucesso");
        }else if (categoria.getNome().equals(categoriaExiste.getNome()) && categoriaExiste.getDeleted().equals(false)){
            attr.addFlashAttribute("fail", "Categoria já existe e está ativa");
        }else{
            categoriaNewService.reativarCategoria(categoria.getNome());
            attr.addFlashAttribute("success", "Categoria reativada com sucesso");
        }

        return "redirect:/categorias/cadastrar";
    }

    @GetMapping("/editar/{id}")
    public String preEditar(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("categoria", categoriaNewService.buscarPorId(id));
        return "categoria/cadastro";
    }

    @PostMapping("/editar")
    public String editar(@Valid Categoria categoria, BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) {
            return "categoria/cadastro";
        }
        categoriaNewService.editarCategoria(categoria);
        attr.addFlashAttribute("success", "Categoria editada com sucesso");
        return "redirect:/categorias/cadastrar";
    }

    @GetMapping("/excluir/{id}")
    public String excluirCategoria(@PathVariable("id") Long id, RedirectAttributes attr) {
        if (categoriaNewService.categoriaTemEquipamentos(id)) {
            attr.addFlashAttribute("fail", "Categoria não removida, possui equipamento(s) vinculado(s).");
        } else {
            categoriaNewService.deletarCategoria(id);
            attr.addFlashAttribute("success", "Categoria excluída com sucesso.");
        }
        return "redirect:/categorias/listar";
    }

    //listar categorias na datatables
    @GetMapping("/datatables/server/categorias")
    public ResponseEntity<?> listarCategoriasDatatables(HttpServletRequest request){
        return ResponseEntity.ok(categoriaNewService.buscarTodos(request));
    }

    @GetMapping("/listar")
    public String listar(){
        return "categoria/lista";
    }

}

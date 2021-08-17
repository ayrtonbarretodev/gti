package com.br.gti.sistemagti.web.controller;

import com.br.gti.sistemagti.domain.Categoria;
import com.br.gti.sistemagti.domain.Departamento;
import com.br.gti.sistemagti.domain.Equipamento;
import com.br.gti.sistemagti.domain.enums.Status;
import com.br.gti.sistemagti.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/equipamentos")
public class EquipamentoController {

    @Autowired
    private EquiService equiService;

    @Autowired
    private DepService depService;

    @Autowired
    private CategoriaNewService categoriaNewService;


    @GetMapping("/cadastrar")
    public String cadastrar(Equipamento equipamento) {
        return "equipamento/cadastro";
    }


    @PostMapping("/salvar")
    public String salvar(@Valid Equipamento equipamento, BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) {
            return "equipamento/cadastro";
        }
        equiService.salvarEquipamento(equipamento);
        attr.addFlashAttribute("success", "Equipamento inserido com sucesso");
        return "redirect:/equipamentos/cadastrar";
    }

    @GetMapping("/editar/{id}")
    public String preEditar(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("equipamento", equiService.buscarPorId(id));
        return "equipamento/cadastro";
    }

    @PostMapping("/editar")
    public String editar(@Valid Equipamento equipamento, BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) {
            return "equipamento/cadastro";
        }
        equiService.editarEquipamento(equipamento);
        attr.addFlashAttribute("success", "Equipamento editado com sucesso");
        return "redirect:/equipamentos/cadastrar";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") Long id, RedirectAttributes attr) {
        equiService.deletarEquipamento(id);
        attr.addFlashAttribute("success", "Equipamento removido com sucesso.");
        return "redirect:/equipamentos/listar";
    }

    //listar equipamentos na datatables
    @GetMapping("/datatables/server/equipamentos")
    public ResponseEntity<?> listarEquipamentosDatatables(HttpServletRequest request){
        return ResponseEntity.ok(equiService.buscarTodos(request));
    }

    @GetMapping("/listar")
    public String listar(){
        return "equipamento/listaTeste";
    }

    @ModelAttribute("categorias")
    public List<Categoria> listaDeCategorias() {
        return categoriaNewService.buscarTodasCategorias();
    }

    @ModelAttribute("departamentos")
    public List<Departamento> listaDeDepartamentos() {
        return depService.buscarTodosDepartamentos();
    }

    @ModelAttribute("qtdDepartamentos")
    public int listaDeDepartamentosTotal() {
        return depService.buscarTodosDepartamentos().size();
    }

    @ModelAttribute("status")
    public Status[] getStatus() {
        return Status.values();
    }

}

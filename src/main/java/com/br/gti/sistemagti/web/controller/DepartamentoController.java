package com.br.gti.sistemagti.web.controller;

import com.br.gti.sistemagti.domain.Departamento;
import com.br.gti.sistemagti.service.DepService;
//import com.br.gti.sistemagti.service.DepartamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("/departamentos")
public class DepartamentoController {

//    @Autowired
//    private DepartamentoService service;

    @Autowired
    private DepService depService;

    @GetMapping("/cadastrar")
    public String cadastrar(Departamento departamento) {
        return "departamento/cadastro";
    }

//    @GetMapping("/listar")
//    public String listar(ModelMap model,
//                         @RequestParam("page") Optional<Integer> page,
//                         @RequestParam("dir") Optional<String> dir) {
//
//        int paginaAtual = page.orElse(1);
//        String ordem = dir.orElse("asc");
//
//        PaginacaoUtil<Departamento> pageDepartamento = service.buscaPorPagina(paginaAtual, ordem);
//
//        model.addAttribute("pageDepartamento", pageDepartamento);
//        return "departamento/lista";
//    }

    @PostMapping("/salvar")
    public String salvar(@Valid Departamento departamento, BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) {
            return "departamento/cadastro";
        }

        depService.salvarDepartamento(departamento);
        attr.addFlashAttribute("success", "Departamento inserido com sucesso");
        return "redirect:/departamentos/cadastrar";
    }

    @GetMapping("/editar/{id}")
    public String preEditar(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("departamento", depService.buscarPorId(id));
        return "departamento/cadastro";
    }

    @PostMapping("/editar")
    public String editar(@Valid Departamento departamento, BindingResult result, RedirectAttributes attr) {

        if (result.hasErrors()) { //se retornar true significa que algum campo não passou no sistema de validação
            return "departamento/cadastro";
        }

        depService.editarDepartamento(departamento);
        attr.addFlashAttribute("success", "Departamento editado com sucesso");
        return "redirect:/departamentos/cadastrar";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") Long id, RedirectAttributes attr) {
        if (depService.departamentoTemEquipamentos(id)) {
            attr.addFlashAttribute("fail", "Departamento não removido. Possui equipamento(s) vinculado(s).");
        } else {
            depService.deletarDepartamento(id);
            attr.addFlashAttribute("success", "Departamento excluído com sucesso.");
        }
        return "redirect:/departamentos/listar";
    }

//    @GetMapping("buscar/nome")
//    public String getPorNome(ModelMap model,
//                             @RequestParam("page") Optional<Integer> page,
//                             @RequestParam("dir") Optional<String> dir,
//                             @RequestParam("nome") Optional<String> nome) {
//        int paginaAtual = page.orElse(1);
//        String ordem = dir.orElse("asc");
//        String name = nome.orElse("");
//
//        PaginacaoUtil<Departamento> pageNome = service.buscarPorNome(paginaAtual, ordem, name);
//
//        model.addAttribute("pageDepartamento", pageNome);
//        return "departamento/lista";
//    }

//    @GetMapping("buscar/ambiente")
//    public String getPorAmbiente(@RequestParam("ambiente") String ambiente, ModelMap model) {
//        model.addAttribute("departamentos", service.buscarPorAmbiente(ambiente));
//        return "departamento/lista";
//    }

    //listar departamentos na datatables
    @GetMapping("/datatables/server/departamentos")
    public ResponseEntity<?> listarDepartamentosDatatables(HttpServletRequest request){
        return ResponseEntity.ok(depService.buscarTodos(request));
    }

    @GetMapping("/listar")
    public String listar(){
        return "departamento/lista";
    }
}

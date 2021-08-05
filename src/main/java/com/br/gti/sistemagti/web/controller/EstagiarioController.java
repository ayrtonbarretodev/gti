package com.br.gti.sistemagti.web.controller;

import com.br.gti.sistemagti.domain.Estagiario;
import com.br.gti.sistemagti.domain.Usuario;
import com.br.gti.sistemagti.service.EstagiarioService;
import com.br.gti.sistemagti.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("estagiarios")
public class EstagiarioController {

    @Autowired
    private EstagiarioService service;

    @Autowired
    private UsuarioService usuarioService;

    // abrir página de dados pessoais do estagiário
    @GetMapping({"/dados"})
    public String abrirPorEstagiario(Estagiario estagiario, ModelMap model, @AuthenticationPrincipal User user) {
        if(estagiario.hasNotId()){
            estagiario = service.buscarPorEmail(user.getUsername());
            model.addAttribute("estagiario", estagiario);
        }
        return "estagiario/cadastro";
    }

    //salvar estagiário
    @PostMapping("/salvar")
    public String salvar(Estagiario estagiario, RedirectAttributes attr, @AuthenticationPrincipal User user){
        //System.out.println("Testando antes");
        if(estagiario.hasNotId() && estagiario.getUsuario().hasNotId()){
            Usuario usuario = usuarioService.buscarPorEmail(user.getUsername());
            estagiario.setUsuario(usuario);
        }
        service.salvar(estagiario);
        //System.out.println("Testando depois");
        attr.addFlashAttribute("sucesso", "Operação realizada com sucesso");
        attr.addFlashAttribute("estagiario", estagiario);
        return "redirect:/estagiarios/dados";
    }

    //editar estagiário
    @PostMapping({"/editar"})
    public String editar(Estagiario estagiario, RedirectAttributes attr){
        //System.out.println("Testando antes");
        service.editar(estagiario);
        //System.out.println("Testando depois");
        attr.addFlashAttribute("sucesso", "Operação realizada com sucesso");
        attr.addFlashAttribute("estagiario", estagiario);
        return "redirect:/estagiarios/dados";
    }
}

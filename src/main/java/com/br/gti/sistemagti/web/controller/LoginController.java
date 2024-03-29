package com.br.gti.sistemagti.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/login-error")
    public String loginError(ModelMap model) {
        model.addAttribute("alerta", "erro");
        model.addAttribute("titulo", "Credenciais inválidas");
        model.addAttribute("texto", "Login ou senha incorretos, tente novamente.");
        model.addAttribute("subtexto", "Acesso permitido apenas para cadastros já ativados.");
        return "login";
    }

    @GetMapping("/acesso-negado")
    public String acessoNegado(ModelMap model, HttpServletResponse resp) {
        model.addAttribute("status", resp.getStatus());
        model.addAttribute("error", "Acesso negado");
        model.addAttribute("message", "Você não tem permissão para acesso a esta área ou ação.");
        return "error";
    }

    @GetMapping("/expired")
    public String sessaoExpirada(ModelMap model) {
        model.addAttribute("alerta", "erro");
        model.addAttribute("titulo", "Acesso recusado!");
        model.addAttribute("texto", "Sua sessão expirou");
        model.addAttribute("subtexto", "Usuário logado em outro dispositivo");
        return "login";
    }



}

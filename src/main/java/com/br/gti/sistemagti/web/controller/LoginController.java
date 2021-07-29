package com.br.gti.sistemagti.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/login-error")
    public String loginError(ModelMap model){
        model.addAttribute("alerta", "erro");
        model.addAttribute("titulo", "Credenciais inválidas");
        model.addAttribute("texto", "Login ou senha incorretos, tente novamente,");
        model.addAttribute("subtexto", "Acesso permitido apenas para cadastros já ativados");
        return "login";
    }

}

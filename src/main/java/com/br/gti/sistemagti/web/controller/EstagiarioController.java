package com.br.gti.sistemagti.web.controller;

import com.br.gti.sistemagti.domain.Estagiario;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("estagiarios")
public class EstagiarioController {

    // abrir página de dados pessoais do estagiário
    @GetMapping({"/dados"})
    public String abrirPorEstagiario(Estagiario estagiario, ModelMap model) {

        return "estagiario/cadastro";
    }
}

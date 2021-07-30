package com.br.gti.sistemagti.web.controller;

import com.br.gti.sistemagti.domain.Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("u")
public class UsuarioController {
    // abrir cadastro de usuários (admin/estagiário)
    @GetMapping("/novo/cadastro/usuario")
    public String cadastroPorAdminParaEstagirario(Usuario usuario){

        return "usuario/cadastro";
    }
}

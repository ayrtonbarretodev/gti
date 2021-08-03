package com.br.gti.sistemagti.web.controller;

import com.br.gti.sistemagti.domain.Usuario;
import com.br.gti.sistemagti.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("u")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    // abrir cadastro de usuários (admin/estagiário)
    @GetMapping("/novo/cadastro/usuario")
    public String cadastroPorAdminParaEstagiario(Usuario usuario) {

        return "usuario/cadastro";
    }

    //abrir lista de usuários
    @GetMapping("lista")
    public String listarUsuarios() {
        return "usuario/lista";
    }

    //listar usuarios na datatables
    @GetMapping("/datatables/server/usuarios")
    public ResponseEntity<?> listarUsuariosDatatables(HttpServletRequest request){

        return ResponseEntity.ok(service.buscarTodos(request));
    }
}

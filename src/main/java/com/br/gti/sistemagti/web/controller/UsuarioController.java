package com.br.gti.sistemagti.web.controller;

import com.br.gti.sistemagti.domain.Perfil;
import com.br.gti.sistemagti.domain.Usuario;
import com.br.gti.sistemagti.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

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

    //salvar cadastro de usuários por administrador
    @PostMapping("/cadastro/salvar")
    public String salvarUsuarios(Usuario usuario, RedirectAttributes attr){
        List<Perfil> perfis = usuario.getPerfis();
        if (perfis.size() > 1 ||
                perfis.contains(Arrays.asList(new Perfil(1L), new Perfil(2L)))) {
            attr.addFlashAttribute("falha", "Estagiário não pode ser Admin ou Admin não pode ser Estagiário");
            attr.addFlashAttribute("usuario", usuario);
        }else{
            service.salvarUsuario(usuario);
            attr.addFlashAttribute("sucesso", "Operação realizada com sucesso!");
        }
        return "redirect:/u/novo/cadastro/usuario";
    }

}

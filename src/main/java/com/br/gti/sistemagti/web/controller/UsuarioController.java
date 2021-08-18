package com.br.gti.sistemagti.web.controller;

import com.br.gti.sistemagti.domain.Estagiario;
import com.br.gti.sistemagti.domain.Perfil;
import com.br.gti.sistemagti.domain.PerfilTipo;
import com.br.gti.sistemagti.domain.Usuario;
import com.br.gti.sistemagti.service.EstagiarioService;
import com.br.gti.sistemagti.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("u")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @Autowired
    private EstagiarioService estagiarioService;

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
                perfis.containsAll(Arrays.asList(new Perfil(PerfilTipo.ADMIN.getCod()), new Perfil(PerfilTipo.ESTAGIARIO.getCod())))) {
            attr.addFlashAttribute("falha", "Selecione somente um perfil");
            attr.addFlashAttribute("usuario", usuario);
        }else{
            try {
                service.salvarUsuario(usuario);
                attr.addFlashAttribute("sucesso", "Operação realizada com sucesso!");
            }catch (DataIntegrityViolationException ex){
                attr.addFlashAttribute("falha", "Cadastro não realizado, email já existente.");
            }
        }
        return "redirect:/u/novo/cadastro/usuario";
    }

    // pre edicao de credenciais de usuarios
    @GetMapping("/editar/credenciais/usuario/{id}")
    public ModelAndView preEditarCredenciais(@PathVariable("id") Long id){
        return new ModelAndView("usuario/cadastro", "usuario", service.buscarPorId(id));
    }

    // pre edição de cadastro de usuarios
    @GetMapping("/editar/dados/usuario/{id}/perfis/{perfis}")
    public ModelAndView preEditarCadastroDadosPessoais(@PathVariable("id") Long usuarioId, @PathVariable("perfis") Long[] perfisId){

        Usuario us = service.buscarPorIdEPerfis(usuarioId,perfisId);

        if (us.getPerfis().contains(new Perfil(PerfilTipo.ADMIN.getCod())) &&
                !us.getPerfis().contains(new Perfil(PerfilTipo.ESTAGIARIO.getCod())) ){
            return new ModelAndView("usuario/cadastro", "usuario", us);
        }else if (us.getPerfis().contains(new Perfil(PerfilTipo.ESTAGIARIO.getCod()))){

            Estagiario estagiario = estagiarioService.buscarPorUsuarioId(usuarioId);
            return estagiario.hasNotId()
                    ? new ModelAndView("estagiario/cadastro", "estagiario", new Estagiario(new Usuario(usuarioId)))
                    : new ModelAndView("estagiario/cadastro", "estagiario", estagiario);
        }
        return new ModelAndView("redirect:/u/lista");
    }

    @GetMapping("/editar/senha")
    public String abrirEditarSenha(){

        return "usuario/editar-senha";
    }

    @PostMapping("/confirmar/senha")
    public String editarSenha(@RequestParam("senha1") String s1, @RequestParam("senha2") String s2,
                              @RequestParam ("senha3") String s3, @AuthenticationPrincipal User user,
                              RedirectAttributes attr){
        if(!s1.equals(s2)){
            attr.addFlashAttribute("falha", "Senhas não conferem, tente novamente");
            return "redirect:/u/editar/senha";
        }

        Usuario u = service.buscarPorEmail(user.getUsername());
        if (!UsuarioService.isSenhaCorreta(s3,u.getSenha())){
            attr.addFlashAttribute("falha", "Senha atual não confere, tente novamente");
            return "redirect:/u/editar/senha";
        }

        service.alterarSenha(u, s1);
        attr.addFlashAttribute("sucesso", "Senha alterada com sucesso");
        return "redirect:/u/editar/senha";
    }

    @GetMapping("/e/redefinir/senha")
    public String pedidoRedefinirSenha(){
        return "usuario/pedido-recuperar-senha";
    }

    @GetMapping("/e/recuperar/senha")
    public String redefinirSenha(String email, ModelMap model) throws MessagingException {
        service.pedidoRedefinicaoDeSenha(email);
        model.addAttribute("sucesso", "Em instantes você receberá um e-mail para " +
                "prosseguir com a redefinição de sua senha");
        model.addAttribute("usuario", new Usuario(email));
        return "usuario/recuperar-senha";
    }

    @PostMapping("/e/nova/senha")
    public String confirmacaoDeRedefinicaoDeSenha(Usuario usuario, ModelMap model){
        Usuario u = service.buscarPorEmail(usuario.getEmail());
        if (!usuario.getCodigoVerificador().equals(u.getCodigoVerificador())){
            model.addAttribute("falha", "Código verificador não confere");
            return "usuario/recuperar-senha";
        }

        u.setCodigoVerificador(null);
        service.alterarSenha(u, usuario.getSenha());
        model.addAttribute("alerta", "sucesso");
        model.addAttribute("titulo", "Senha redefinida");
        model.addAttribute("texto", "Você já pode logar no sistema");
        return "login";
    }

}

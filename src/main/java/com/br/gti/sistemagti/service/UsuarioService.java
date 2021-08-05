package com.br.gti.sistemagti.service;

import com.br.gti.sistemagti.datatables.Datatables;
import com.br.gti.sistemagti.datatables.DatatablesColunas;
import com.br.gti.sistemagti.domain.Perfil;
import com.br.gti.sistemagti.domain.Usuario;
import com.br.gti.sistemagti.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private Datatables datatables;

    public static boolean isSenhaCorreta(String senhaDigitada, String senhaArmazenada) {
        return new BCryptPasswordEncoder().matches(senhaDigitada,senhaArmazenada);
    }

    @Transactional(readOnly = true)
    public Usuario buscarPorEmail(String email) {

        return repository.findByEmail(email);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = buscarPorEmail(username);

        return new User(
                usuario.getEmail(),
                usuario.getSenha(),
                AuthorityUtils.createAuthorityList(getAuthorities(usuario.getPerfis()))
        );
    }

    private String[] getAuthorities(List<Perfil> perfis) {
        String[] authorities = new String[perfis.size()];
        for (int i = 0; i < perfis.size(); i++) {
            authorities[i] = perfis.get(i).getDesc();
        }
        return authorities;
    }

    @Transactional(readOnly = true)
    public Map<String,Object> buscarTodos(HttpServletRequest request) {
        datatables.setRequest(request);
        datatables.setColunas(DatatablesColunas.USUARIOS);
        Page<Usuario> page = datatables.getSearch().isEmpty()
                ? repository.findAll(datatables.getPageable())
                : repository.findByEmailOrPerfil(datatables.getSearch(), datatables.getPageable());
        return datatables.getResponse(page);
    }

    @Transactional(readOnly = false)
    public void salvarUsuario(Usuario usuario) {
        String crypt = new BCryptPasswordEncoder().encode(usuario.getSenha());
        usuario.setSenha(crypt);
        repository.save(usuario);
    }

    @Transactional(readOnly = true)
    public Usuario buscarPorId(Long id) {
        return repository.findById(id).get();
    }

    @Transactional(readOnly = true)
    public Usuario buscarPorIdEPerfis(Long usuarioId, Long[] perfisId) {
        return repository.findByIdAndPerfis(usuarioId,perfisId)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário inexistente!"));
    }

    @Transactional(readOnly = false)
    public void alterarSenha(Usuario usuario, String senha) {
        usuario.setSenha(new BCryptPasswordEncoder().encode(senha));
        repository.save(usuario);
    }
}

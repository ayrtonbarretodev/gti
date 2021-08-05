package com.br.gti.sistemagti.service;

import com.br.gti.sistemagti.domain.Estagiario;
import com.br.gti.sistemagti.repository.EstagiarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EstagiarioService {

    @Autowired
    private EstagiarioRepository repository;

    @Transactional(readOnly = true)
    public Estagiario buscarPorUsuarioId(Long id){
        return repository.findByUsuarioId(id).orElse(new Estagiario());
    }

    @Transactional(readOnly = false)
    public void salvar(Estagiario estagiario) {
        repository.save(estagiario);
    }

    @Transactional(readOnly = false)
    public void editar(Estagiario estagiario) {
        Estagiario e2 = repository.findById(estagiario.getId()).get();
        e2.setNome(estagiario.getNome());
        e2.setMatricula(estagiario.getMatricula());
    }

    @Transactional(readOnly = true)
    public Estagiario buscarPorEmail(String email) {
        return repository.findByUsuarioEmail(email).orElse(new Estagiario());
    }

}

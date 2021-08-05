package com.br.gti.sistemagti.repository;

import com.br.gti.sistemagti.domain.Estagiario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface EstagiarioRepository extends JpaRepository<Estagiario, Long> {

    @Query("select e from Estagiario e where e.usuario.id = :id")
    Optional<Estagiario> findByUsuarioId(Long id);

    @Query("select e from Estagiario e where e.usuario.email like :email")
    Optional<Estagiario> findByUsuarioEmail(String email);
}

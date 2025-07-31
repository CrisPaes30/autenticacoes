package com.lecosBurguer.keycloak.Autenticacoes.Lecos.repository;

import com.lecosBurguer.keycloak.Autenticacoes.Lecos.entidade.LcCadastro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LcCadastroRepository extends JpaRepository<LcCadastro, Long> {

    Optional<LcCadastro> findByClient(String client);

    Optional<LcCadastro> findByEmail(String email);

}

package com.lecosBurguer.keycloak.Autenticacoes.Lecos.autenticadorCadastro.repository;

import com.lecosBurguer.keycloak.Autenticacoes.Lecos.autenticadorCadastro.entities.LcKeycloakErrorCadastro;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface LcKeycloakErrorCadastroRepository extends JpaRepository<LcKeycloakErrorCadastro, String> {

    @Modifying
    @Transactional
    @Query("INSERT INTO LcKeycloakErrorCadastro (username, email, lastName, firstName, roles, password, status) VALUES (?1, ?2, ?3, ?4, ?5, ?6, ?7)")
    void insereUsuario(String username, String email, String lastName, String firstName, String roles, String password, String status);

}

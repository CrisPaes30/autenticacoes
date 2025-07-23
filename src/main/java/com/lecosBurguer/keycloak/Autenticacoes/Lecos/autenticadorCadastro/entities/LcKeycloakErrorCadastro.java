package com.lecosBurguer.keycloak.Autenticacoes.Lecos.autenticadorCadastro.entities;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "LC_KEYCLOAK_ERROR_CADASTRO")
@Entity
public class LcKeycloakErrorCadastro {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_generator")
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "roles")
    private String roles;

    @Column(name = "password")
    private String password;

    @Column(name = "status")
    private String status;
}

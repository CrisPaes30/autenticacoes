package com.lecosBurguer.keycloak.Autenticacoes.Lecos.autenticadorUsuario.controller.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthRequest {

    @NotBlank(message = "Username não pode estar vazio")
    private String username;

    @NotBlank(message = "password não pode estar vazio")
    private String password;
}

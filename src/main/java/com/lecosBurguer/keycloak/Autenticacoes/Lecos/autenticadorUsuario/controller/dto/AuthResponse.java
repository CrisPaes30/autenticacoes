package com.lecosBurguer.keycloak.Autenticacoes.Lecos.autenticadorUsuario.controller.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthResponse {

    private String access_token;
    private String refresh_token;
    private String token_type;
    private Long expires_in;
}

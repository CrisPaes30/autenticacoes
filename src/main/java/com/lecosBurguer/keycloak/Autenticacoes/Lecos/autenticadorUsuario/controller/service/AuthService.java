package com.lecosBurguer.keycloak.Autenticacoes.Lecos.autenticadorUsuario.controller.service;

import com.lecosBurguer.keycloak.Autenticacoes.Lecos.autenticadorUsuario.controller.dto.AuthResponse;

public interface AuthService {

    public AuthResponse login(String username, String password);
}

package com.lecosBurguer.keycloak.Autenticacoes.Lecos.autenticadorUsuario.controller;

import com.lecosBurguer.keycloak.Autenticacoes.Lecos.autenticadorUsuario.controller.dto.AuthRequest;
import com.lecosBurguer.keycloak.Autenticacoes.Lecos.autenticadorUsuario.controller.dto.AuthResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface UsuarioAuthLoginCotroller {

    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request);
}

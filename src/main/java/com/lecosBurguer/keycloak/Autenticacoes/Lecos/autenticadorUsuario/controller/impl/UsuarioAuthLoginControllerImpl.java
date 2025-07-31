package com.lecosBurguer.keycloak.Autenticacoes.Lecos.autenticadorUsuario.controller.impl;

import com.lecosBurguer.keycloak.Autenticacoes.Lecos.autenticadorUsuario.controller.UsuarioAuthLoginCotroller;
import com.lecosBurguer.keycloak.Autenticacoes.Lecos.autenticadorUsuario.controller.dto.AuthRequest;
import com.lecosBurguer.keycloak.Autenticacoes.Lecos.autenticadorUsuario.controller.dto.AuthResponse;
import com.lecosBurguer.keycloak.Autenticacoes.Lecos.autenticadorUsuario.controller.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;


@RestController
@RequestMapping("/v1/usuario/auth")
public class UsuarioAuthLoginControllerImpl implements UsuarioAuthLoginCotroller {

    @Autowired
    private AuthService authService;

    @PostMapping
    @Override
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthRequest request) {
        try {
            AuthResponse token = authService.login(request.getUsername(), request.getPassword());
            return ResponseEntity.ok(token);
        } catch (HttpClientErrorException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}

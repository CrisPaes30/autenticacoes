package com.lecosBurguer.keycloak.Autenticacoes.Lecos.autenticadorUsuario.controller.service.impl;

import com.lecosBurguer.keycloak.Autenticacoes.Lecos.autenticadorCadastro.exceptions.BusinessException;
import com.lecosBurguer.keycloak.Autenticacoes.Lecos.autenticadorUsuario.controller.dto.AuthResponse;
import com.lecosBurguer.keycloak.Autenticacoes.Lecos.autenticadorUsuario.controller.service.AuthService;
import com.lecosBurguer.keycloak.Autenticacoes.Lecos.config.KeycloakConfig;
import com.lecosBurguer.keycloak.Autenticacoes.Lecos.entidade.LcCadastro;
import com.lecosBurguer.keycloak.Autenticacoes.Lecos.repository.LcCadastroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.atomic.AtomicReference;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    @Autowired
    private RestTemplate restTemplate;
    private final KeycloakConfig keycloakConfig;
    private final LcCadastroRepository lcCadastroRepository;

    @Override
    public AuthResponse login(String username, String password) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        String client =validaUsuarioPorEmailOuUserName(username);

        var params = new LinkedMultiValueMap<String, String>();
        params.add("grant_type", "password");
        params.add("client_id", keycloakConfig.getClientId());
        params.add("username", client);
        params.add("password", password);
        params.add("client_secret", keycloakConfig.getSecret());

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);

        String tokenUrl = keycloakConfig.getRealmUrl() + "/realms/" + keycloakConfig.getRealm() + "/protocol/openid-connect/token";

        ResponseEntity<AuthResponse> response;
        try {
            response = restTemplate.postForEntity(tokenUrl, request, AuthResponse.class);
        } catch (BusinessException e) {
            throw new RuntimeException("Falha na chamadada do token", e);
        }

        return response.getBody();
    }

    private String validaUsuarioPorEmailOuUserName(String userName) {
        return userName.contains("@")
                ? lcCadastroRepository.findByEmail(userName)
                .filter(c -> "A".equals(c.getAtivo()))
                .map(LcCadastro::getClient)
                .orElseThrow(() -> new BusinessException("Usuário não encontrado ou inativo"))
                : lcCadastroRepository.findByClient(userName)
                .filter(c -> "A".equals(c.getAtivo()))
                .map(LcCadastro::getClient)
                .orElseThrow(() -> new BusinessException("Usuário não encontrado ou inativo"));
    }
}

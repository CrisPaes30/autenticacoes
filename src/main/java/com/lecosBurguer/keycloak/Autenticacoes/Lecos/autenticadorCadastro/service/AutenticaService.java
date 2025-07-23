package com.lecosBurguer.keycloak.Autenticacoes.Lecos.autenticadorCadastro.service;

import com.lecosBurguer.keycloak.Autenticacoes.Lecos.autenticadorCadastro.requests.AutenticaRequest;
import com.lecosBurguer.keycloak.Autenticacoes.Lecos.autenticadorCadastro.requests.dto.AutenticaRequestDTO;

public interface AutenticaService {

    public void cadastroUsuarioKeyCloak(AutenticaRequestDTO autenticaRequest);
}

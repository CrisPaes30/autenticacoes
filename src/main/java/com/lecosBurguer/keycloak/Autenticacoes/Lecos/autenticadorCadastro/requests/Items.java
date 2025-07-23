package com.lecosBurguer.keycloak.Autenticacoes.Lecos.autenticadorCadastro.requests;

import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Items {

    @Valid
    private AutenticaRequest autenticaRequest;
}

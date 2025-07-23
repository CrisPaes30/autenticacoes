package com.lecosBurguer.keycloak.Autenticacoes.Lecos.autenticadorCadastro.requests.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lecosBurguer.keycloak.Autenticacoes.Lecos.autenticadorCadastro.requests.Items;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class AutenticaRequestDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("items")
    @Valid
    private List<Items> item;
}

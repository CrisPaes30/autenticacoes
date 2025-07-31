package com.lecosBurguer.keycloak.Autenticacoes.Lecos.entidade;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "LC_CADASTRO")
@Entity
public class LcCadastro {

    @Id
    private Long id;
    private String client;
    private String email;
    private String ativo;

}

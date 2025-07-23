package com.lecosBurguer.keycloak.Autenticacoes.Lecos.utils;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.util.Locale;

import static com.lecosBurguer.keycloak.Autenticacoes.Lecos.autenticadorCadastro.config.CadastroConfig.MESSAGE_SOURCE_BEAN_IDENTIFIER;

@Configuration
public class MensagemResolver {

    private final ReloadableResourceBundleMessageSource messageSource;

    public MensagemResolver(@Qualifier(MESSAGE_SOURCE_BEAN_IDENTIFIER) ReloadableResourceBundleMessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String getMensagem(String codigo, Object... args) {
        Locale locale = Locale.getDefault();
        return messageSource.getMessage(codigo, args, locale);
    }
}

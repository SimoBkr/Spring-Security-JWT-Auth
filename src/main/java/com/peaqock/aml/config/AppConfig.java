package com.peaqock.aml.config;

import com.peaqock.aml.service.auth.AuthService;
import com.peaqock.aml.shared.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InjectionPoint;
import org.springframework.context.annotation.*;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;
import java.util.Objects;

@Configuration
public class AppConfig {

    @Bean("authenticatedUser")
    @Description("Load Authenticated User")
    @Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public UserInfo authenticatedUser(AuthService service) {
        return service.loadAuthenticatedUser()
                .orElse(null);
    }

    @Bean
    @Scope("prototype")
    public Logger logger(final InjectionPoint injectionPoint) {
        if (Objects.nonNull(injectionPoint.getMethodParameter())) {
            return LoggerFactory.getLogger(injectionPoint.getMethodParameter().getContainingClass());
        }

        if (Objects.nonNull(injectionPoint.getField())) {
            return LoggerFactory.getLogger(injectionPoint.getField().getDeclaringClass());
        }

        throw new IllegalArgumentException();
    }

    @Bean
    public KeyPair keyPair(AuthProps authProps) throws IOException, UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException, CertificateException {
        final var props = authProps.getJwt();
        final var ksFile = props.getKeyStore();

        final var keystore = KeyStore.getInstance(ksFile.getFile(), props.getKeyPassword().toCharArray());
        final var certificate = keystore.getCertificate(props.getKeyAlias());

        final var publicKey = certificate.getPublicKey();
        final var privateKey = (PrivateKey) keystore.getKey(props.getKeyAlias(), props.getKeyPassword().toCharArray());

        return new KeyPair(publicKey, privateKey);
    }
}

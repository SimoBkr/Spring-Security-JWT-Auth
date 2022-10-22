package com.peaqock.aml.config;

import com.peaqock.aml.dto.UserPrincipal;
import com.peaqock.aml.utils.DateTimeUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.time.temporal.TemporalAccessor;
import java.util.Objects;
import java.util.Optional;

@EnableJpaAuditing(dateTimeProviderRef = "auditingDateTimeProvider")
@Component("auditingDateTimeProvider")
public class JpaConfiguration implements DateTimeProvider {

    @Bean
    public AuditorAware<String> auditorProvider() {
        return new SpringSecurityAuditAwareImpl();
    }

    @Override
    public Optional<TemporalAccessor> getNow() {
        return Optional.of(DateTimeUtil.now());
    }
}

class SpringSecurityAuditAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        final var authentication = SecurityContextHolder.getContext().getAuthentication();

        if (Objects.isNull(authentication) ||
                !authentication.isAuthenticated() ||
                authentication instanceof AnonymousAuthenticationToken) {
            return Optional.empty();
        }

        final var principal = (UserPrincipal) authentication.getPrincipal();
        return Optional.ofNullable(String.valueOf(principal.getUserId()));
    }
}

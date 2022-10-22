package com.peaqock.aml.service.auth;

import com.peaqock.aml.dao.UserRepository;
import com.peaqock.aml.dto.UserPrincipal;
import com.peaqock.aml.dto.payload.AccessToken;
import com.peaqock.aml.dto.payload.AuthRequest;
import com.peaqock.aml.exception.ApiErrorMessage;
import com.peaqock.aml.exception.errors.AuthenticationFailedException;
import com.peaqock.aml.mapper.IUserMapper;
import com.peaqock.aml.security.jwt.JwtTokenProvider;
import com.peaqock.aml.shared.UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository repository;
    private final IUserMapper mapper;

    public AccessToken accessToken(AuthRequest request) {
        try {
            var authenticate = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

            var user = (UserPrincipal) authenticate.getPrincipal();

            return jwtTokenProvider.generateAccessToken(user);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new AuthenticationFailedException(ApiErrorMessage.BAD_CREDENTIALS.getErrorMessage());
        }
    }


    public UserInfo loadUserinfo() {
        return loadAuthenticatedUser()
                .orElseThrow(() -> new UsernameNotFoundException(ApiErrorMessage.NO_USER_FOUND.getErrorMessage()));
    }

    public Optional<UserInfo> loadAuthenticatedUser() {
        final var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (Objects.nonNull(authentication)) {
            return repository.findByUsernameIgnoreCase(authentication.getName())
                    .map(mapper::toDto);
        }
        return Optional.empty();
    }
}

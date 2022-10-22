package com.peaqock.aml.service.user;

import com.peaqock.aml.dao.UserRepository;
import com.peaqock.aml.domain.UserEntity;
import com.peaqock.aml.dto.payload.UserGroups;
import com.peaqock.aml.dto.payload.UserPayload;
import com.peaqock.aml.dto.payload.UserResponse;
import com.peaqock.aml.exception.ApiErrorMessage;
import com.peaqock.aml.exception.errors.AlreadyExistException;
import com.peaqock.aml.mapper.IUserMapper;
import com.peaqock.aml.service.email.EmailSenderService;
import com.peaqock.aml.shared.UserInfo;
import com.peaqock.aml.shared.enums.GroupEnum;
import com.peaqock.aml.shared.enums.RoleEnum;
import com.peaqock.aml.utils.PasswordGeneratorUtils;
import com.peaqock.aml.utils.constants.PageableConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class DefaultAuthServiceImpl implements UserService {

    private static final String STATIC_PASSWORD = "admin";

    private final IUserMapper mapper;
    private final UserRepository repository;
    private final PasswordEncoder encoder;
    private final EmailSenderService senderService;

    @Override
    public UserResponse listAllUsers(RoleEnum role, String search, int pageNo, int pageSize, String sortBy, String sortDir) {
        final var pageable = PageableConstants.builder()
                .pageNo(pageNo)
                .pageSize(pageSize)
                .sortDir(sortDir)
                .sortBy(sortBy)
                .build();

        final var result = repository.findAllBySearch(role, search, pageable);

        return UserResponse.builder()
                .pageNo(pageNo)
                .pageSize(pageSize)
                .last(result.isLast())
                .totalPages(result.getTotalPages())
                .totalElements(result.getTotalElements())
                .content(mapper.toDTOList(result.getContent()))
                .build();
    }

    @Override
    public List<UserInfo> listAllUsersByGroup(GroupEnum group) {
        return mapper.toDTOList(repository.findAllByGroups(group));
    }

    @Override
    public String changeStatus(UUID id) {
        repository.updateStatusById(id);
        return "User Saved.";
    }

    @Override
    public String asigneUserGroups(UserGroups groups) {
        var users = groups.getIds().stream()
                .map(this::fetchUser)
                .peek(user -> user.addGroups(groups.getGroups()))
                .collect(Collectors.toSet());
        repository.saveAll(users);
        return "Users Saved.";
    }


    @Override
    public Map<String, Object> createUser(UserPayload payload, RoleEnum role) {
        if (repository.existsByUsernameIgnoreCase(payload.getEmail()))
            throw new AlreadyExistException(ApiErrorMessage.USER_ALREADY_EXISTS.getErrorMessage());

        var user = mapper.payloadToEntity(payload);
        var password = PasswordGeneratorUtils.generatePassword();

        user.addRole(role);
        user.setActive(true);
        user.setEncryptedPassword(encoder.encode(password));
        user.setUsername(payload.getEmail().trim().toLowerCase());

        user = repository.save(user);
        senderService.createUser(payload.getEmail(), password);
        return Map.of("message", "User Created Successfully.", "data", user);
    }

    @Override
    public Map<String, Object> shareForm(UUID userId, UUID idFeedback) {
        final var user = fetchUser(userId);
        senderService.shareForm(user.getCompleteName(), user.getEmail(), idFeedback);
        return Map.of("message", "form shared Successfully.", "data", idFeedback);
    }

    private UserEntity fetchUser(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new AlreadyExistException(ApiErrorMessage.NO_RECORD_FOUND.getErrorMessage()));
    }
}

package com.peaqock.aml.service.user;

import com.peaqock.aml.dto.payload.UserGroups;
import com.peaqock.aml.dto.payload.UserPayload;
import com.peaqock.aml.dto.payload.UserResponse;
import com.peaqock.aml.shared.UserInfo;
import com.peaqock.aml.shared.enums.GroupEnum;
import com.peaqock.aml.shared.enums.RoleEnum;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface UserService {

    UserResponse listAllUsers(RoleEnum role, String search,
                              int pageNo, int pageSize,
                              String sortBy, String sortDir);

    List<UserInfo> listAllUsersByGroup(GroupEnum group);

    String changeStatus(UUID id);

    String asigneUserGroups(UserGroups groups);

    Map<String, Object> createUser(UserPayload payload, RoleEnum group);

    Map<String, Object> shareForm(UUID userId, UUID idFeedback);

}

package com.peaqock.aml.api;

import com.peaqock.aml.dto.payload.ShareDTO;
import com.peaqock.aml.dto.payload.UserGroups;
import com.peaqock.aml.dto.payload.UserPayload;
import com.peaqock.aml.dto.payload.UserResponse;
import com.peaqock.aml.service.user.UserService;
import com.peaqock.aml.shared.enums.RoleEnum;
import com.peaqock.aml.utils.constants.PageableConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@PreAuthorize("hasAnyAuthority('AML_ADMIN')")
public class UserController {

    private final UserService service;

    @GetMapping("/all/{role}")
    public UserResponse getAllUsers(
            @PathVariable(name = "role") RoleEnum role,
            @RequestParam(name = "search", required = false) String search,
            @RequestParam(value = "pageNo", defaultValue = PageableConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = PageableConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = PageableConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = PageableConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ) {
        return service.listAllUsers(role, search, pageNo, pageSize, sortBy, sortDir);
    }

    @PostMapping("/status/{id}")
    public String changeStatus(@PathVariable UUID id) {
        return service.changeStatus(id);
    }

    @PutMapping("/groups")
    public String asigneUserGroups(@RequestBody UserGroups groups) {
        return service.asigneUserGroups(groups);
    }

    @PostMapping("/create/{role}")
    public Map<String, Object> createUser(@RequestBody UserPayload payload, @PathVariable RoleEnum role) {
        return service.createUser(payload, role);
    }

    @PostMapping("/share")
    public Map<String, Object> shareForm(@RequestBody ShareDTO payload) {
        return service.shareForm(payload.getUserId(), payload.getIdFeedback());
    }
}

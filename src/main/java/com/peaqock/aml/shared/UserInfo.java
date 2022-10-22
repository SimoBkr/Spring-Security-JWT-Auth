package com.peaqock.aml.shared;

import com.peaqock.aml.shared.enums.GroupEnum;
import com.peaqock.aml.shared.enums.RoleEnum;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {

    private UUID id;

    private String username;

    private String firstName;

    private String lastName;

    private String email;

    private String organization;

    private String telephone;

    private String jobTitle;

    private String profile;

    private boolean active;

    @Builder.Default
    private Set<RoleEnum> roles = new HashSet<>();

    @Builder.Default
    private Set<GroupEnum> groups = new HashSet<>();

    private OffsetDateTime creationDate;

    public String getCompleteName() {
        return String.format("%s %s", this.getFirstName(), this.getLastName());
    }
}

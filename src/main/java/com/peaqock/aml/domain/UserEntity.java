package com.peaqock.aml.domain;

import com.peaqock.aml.domain.common.AbstractEntity;
import com.peaqock.aml.shared.enums.GroupEnum;
import com.peaqock.aml.shared.enums.RoleEnum;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity extends AbstractEntity {

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "organization")
    private String organization;

    @Column(name = "telephone")
    private String telephone;

    @Column(name = "job_title")
    private String jobTitle;

    @Column(name = "profile")
    private String profile;

    @Column(name = "encryptedPassword", nullable = false)
    private String encryptedPassword;

    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = RoleEnum.class)
    @Builder.Default
    private Set<RoleEnum> roles = new HashSet<>();

    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = GroupEnum.class)
    @Builder.Default
    private Set<GroupEnum> groups = new HashSet<>();

    @Column(name = "user_status")
    @Builder.Default
    private Boolean active = false;

    public Collection<GrantedAuthority> getGrantedAuthorities() {
        return this.roles.stream()
                .map(RoleEnum::name)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    public void addGroup(GroupEnum group) {
        this.groups.add(group);
    }

    public void addGroups(Set<GroupEnum> groups) {
        this.groups.clear();
        if (Objects.nonNull(groups)) {
            this.groups = groups;
        }
    }

    public void addRole(RoleEnum role) {
        this.roles.add(role);
    }

    public void addRoles(Set<RoleEnum> roles) {
        this.roles.clear();
        if (Objects.nonNull(roles)) {
            this.roles = roles;
        }
    }

    public String getCompleteName() {
        return String.format("%s %s", this.getFirstName(), this.getLastName());
    }
}

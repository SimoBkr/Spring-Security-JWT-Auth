package com.peaqock.aml.dto.payload;

import com.peaqock.aml.shared.enums.GroupEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserGroups {

    @NotNull
    @Builder.Default
    private Set<UUID> ids = new HashSet<>();

    @NotNull
    @Builder.Default
    private Set<GroupEnum> groups = new HashSet<>();
}

package com.peaqock.aml.mapper;

import com.peaqock.aml.domain.UserEntity;
import com.peaqock.aml.dto.payload.UserPayload;
import com.peaqock.aml.shared.UserInfo;
import org.mapstruct.Mapper;
import org.springframework.context.annotation.Lazy;

@Lazy
@Mapper(
        config = SpringMappingConfig.class,
        uses = {SharedMapper.class}
)
public interface IUserMapper extends IAbstractMapper<UserEntity, UserInfo> {

    UserEntity payloadToEntity(UserPayload dto);
}

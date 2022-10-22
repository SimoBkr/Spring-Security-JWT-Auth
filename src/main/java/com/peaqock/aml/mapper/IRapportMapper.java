package com.peaqock.aml.mapper;


import com.peaqock.aml.domain.RapportEnity;
import com.peaqock.aml.dto.RapportDto;
import org.mapstruct.Mapper;
import org.springframework.context.annotation.Lazy;

@Lazy
@Mapper(
        config = SpringMappingConfig.class,
        uses = {SharedMapper.class}
)
public interface IRapportMapper extends IAbstractMapper<RapportEnity, RapportDto> {

}

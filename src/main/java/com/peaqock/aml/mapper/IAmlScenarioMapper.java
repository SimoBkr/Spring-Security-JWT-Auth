package com.peaqock.aml.mapper;

import com.peaqock.aml.domain.AmlScenarioEntity;
import com.peaqock.aml.domain.AmlSummaryEntity;
import com.peaqock.aml.dto.AmlScenarioDTO;
import com.peaqock.aml.dto.AmlSummaryDTO;
import org.mapstruct.Mapper;
import org.springframework.context.annotation.Lazy;

import java.util.List;

@Lazy
@Mapper(
        config = SpringMappingConfig.class,
        uses = {SharedMapper.class}
)
public interface IAmlScenarioMapper extends IAbstractMapper<AmlScenarioEntity, AmlScenarioDTO> {

    List<AmlSummaryDTO> toSummaryDTOList(List<AmlSummaryEntity> entities);
}

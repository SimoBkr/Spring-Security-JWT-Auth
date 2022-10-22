package com.peaqock.aml.mapper;

import com.peaqock.aml.domain.AmlScenarioEntity;
import com.peaqock.aml.domain.AmlSummaryEntity;
import com.peaqock.aml.dto.AmlScenarioDTO;
import com.peaqock.aml.dto.AmlSummaryDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-10-21T20:18:55+0100",
    comments = "version: 1.5.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.1.jar, environment: Java 11.0.16.1 (Amazon.com Inc.)"
)
@Component
public class IAmlScenarioMapperImpl implements IAmlScenarioMapper {

    @Override
    public AmlScenarioEntity toEntity(AmlScenarioDTO dto) {
        if ( dto == null ) {
            return null;
        }

        AmlScenarioEntity.AmlScenarioEntityBuilder amlScenarioEntity = AmlScenarioEntity.builder();

        amlScenarioEntity.id( dto.getId() );
        amlScenarioEntity.subjectId( dto.getSubjectId() );
        amlScenarioEntity.scenarioName( dto.getScenarioName() );
        amlScenarioEntity.scenarioCode( dto.getScenarioCode() );
        amlScenarioEntity.scenarioDescription( dto.getScenarioDescription() );
        amlScenarioEntity.raisonSocial( dto.getRaisonSocial() );
        amlScenarioEntity.policyKey( dto.getPolicyKey() );
        amlScenarioEntity.alertKey( dto.getAlertKey() );
        amlScenarioEntity.complexity( dto.getComplexity() );
        amlScenarioEntity.hasComment( dto.getHasComment() );
        amlScenarioEntity.comment( dto.getComment() );
        amlScenarioEntity.score( dto.getScore() );

        return amlScenarioEntity.build();
    }

    @Override
    public AmlScenarioDTO toDto(AmlScenarioEntity entity) {
        if ( entity == null ) {
            return null;
        }

        AmlScenarioDTO.AmlScenarioDTOBuilder amlScenarioDTO = AmlScenarioDTO.builder();

        amlScenarioDTO.id( entity.getId() );
        amlScenarioDTO.subjectId( entity.getSubjectId() );
        amlScenarioDTO.scenarioName( entity.getScenarioName() );
        amlScenarioDTO.scenarioCode( entity.getScenarioCode() );
        amlScenarioDTO.scenarioDescription( entity.getScenarioDescription() );
        amlScenarioDTO.raisonSocial( entity.getRaisonSocial() );
        amlScenarioDTO.complexity( entity.getComplexity() );
        amlScenarioDTO.policyKey( entity.getPolicyKey() );
        amlScenarioDTO.alertKey( entity.getAlertKey() );
        amlScenarioDTO.hasComment( entity.getHasComment() );
        amlScenarioDTO.comment( entity.getComment() );
        amlScenarioDTO.score( entity.getScore() );

        return amlScenarioDTO.build();
    }

    @Override
    public List<AmlScenarioEntity> toEntityList(List<AmlScenarioDTO> dtos) {
        if ( dtos == null ) {
            return null;
        }

        List<AmlScenarioEntity> list = new ArrayList<AmlScenarioEntity>( dtos.size() );
        for ( AmlScenarioDTO amlScenarioDTO : dtos ) {
            list.add( toEntity( amlScenarioDTO ) );
        }

        return list;
    }

    @Override
    public List<AmlScenarioDTO> toDTOList(List<AmlScenarioEntity> entities) {
        if ( entities == null ) {
            return null;
        }

        List<AmlScenarioDTO> list = new ArrayList<AmlScenarioDTO>( entities.size() );
        for ( AmlScenarioEntity amlScenarioEntity : entities ) {
            list.add( toDto( amlScenarioEntity ) );
        }

        return list;
    }

    @Override
    public Iterable<AmlScenarioEntity> toEntityIterable(Iterable<AmlScenarioDTO> dtos) {
        if ( dtos == null ) {
            return null;
        }

        ArrayList<AmlScenarioEntity> iterable = new ArrayList<AmlScenarioEntity>();
        for ( AmlScenarioDTO amlScenarioDTO : dtos ) {
            iterable.add( toEntity( amlScenarioDTO ) );
        }

        return iterable;
    }

    @Override
    public Iterable<AmlScenarioDTO> toDTOIterable(Iterable<AmlScenarioEntity> entities) {
        if ( entities == null ) {
            return null;
        }

        ArrayList<AmlScenarioDTO> iterable = new ArrayList<AmlScenarioDTO>();
        for ( AmlScenarioEntity amlScenarioEntity : entities ) {
            iterable.add( toDto( amlScenarioEntity ) );
        }

        return iterable;
    }

    @Override
    public List<AmlSummaryDTO> toSummaryDTOList(List<AmlSummaryEntity> entities) {
        if ( entities == null ) {
            return null;
        }

        List<AmlSummaryDTO> list = new ArrayList<AmlSummaryDTO>( entities.size() );
        for ( AmlSummaryEntity amlSummaryEntity : entities ) {
            list.add( amlSummaryEntityToAmlSummaryDTO( amlSummaryEntity ) );
        }

        return list;
    }

    protected AmlSummaryDTO amlSummaryEntityToAmlSummaryDTO(AmlSummaryEntity amlSummaryEntity) {
        if ( amlSummaryEntity == null ) {
            return null;
        }

        AmlSummaryDTO.AmlSummaryDTOBuilder amlSummaryDTO = AmlSummaryDTO.builder();

        amlSummaryDTO.id( amlSummaryEntity.getId() );
        amlSummaryDTO.subjectId( amlSummaryEntity.getSubjectId() );
        amlSummaryDTO.scenarioName( amlSummaryEntity.getScenarioName() );
        amlSummaryDTO.scenarioCode( amlSummaryEntity.getScenarioCode() );
        amlSummaryDTO.scenarioDescription( amlSummaryEntity.getScenarioDescription() );
        amlSummaryDTO.raisonSocial( amlSummaryEntity.getRaisonSocial() );
        amlSummaryDTO.complexity( amlSummaryEntity.getComplexity() );
        amlSummaryDTO.alertNumber( amlSummaryEntity.getAlertNumber() );
        amlSummaryDTO.scoreTotal( amlSummaryEntity.getScoreTotal() );
        amlSummaryDTO.scoreAvg( amlSummaryEntity.getScoreAvg() );

        return amlSummaryDTO.build();
    }
}

package com.peaqock.aml.mapper;

import com.peaqock.aml.domain.RapportEnity;
import com.peaqock.aml.dto.RapportDto;
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
public class IRapportMapperImpl implements IRapportMapper {

    @Override
    public RapportEnity toEntity(RapportDto dto) {
        if ( dto == null ) {
            return null;
        }

        RapportEnity.RapportEnityBuilder rapportEnity = RapportEnity.builder();

        rapportEnity.searchedName( dto.getSearchedName() );
        rapportEnity.flag( dto.getFlag() );
        rapportEnity.status( dto.getStatus() );
        rapportEnity.comment( dto.getComment() );

        return rapportEnity.build();
    }

    @Override
    public RapportDto toDto(RapportEnity entity) {
        if ( entity == null ) {
            return null;
        }

        RapportDto.RapportDtoBuilder rapportDto = RapportDto.builder();

        rapportDto.searchedName( entity.getSearchedName() );
        rapportDto.flag( entity.getFlag() );
        rapportDto.status( entity.getStatus() );
        rapportDto.comment( entity.getComment() );

        return rapportDto.build();
    }

    @Override
    public List<RapportEnity> toEntityList(List<RapportDto> dtos) {
        if ( dtos == null ) {
            return null;
        }

        List<RapportEnity> list = new ArrayList<RapportEnity>( dtos.size() );
        for ( RapportDto rapportDto : dtos ) {
            list.add( toEntity( rapportDto ) );
        }

        return list;
    }

    @Override
    public List<RapportDto> toDTOList(List<RapportEnity> entities) {
        if ( entities == null ) {
            return null;
        }

        List<RapportDto> list = new ArrayList<RapportDto>( entities.size() );
        for ( RapportEnity rapportEnity : entities ) {
            list.add( toDto( rapportEnity ) );
        }

        return list;
    }

    @Override
    public Iterable<RapportEnity> toEntityIterable(Iterable<RapportDto> dtos) {
        if ( dtos == null ) {
            return null;
        }

        ArrayList<RapportEnity> iterable = new ArrayList<RapportEnity>();
        for ( RapportDto rapportDto : dtos ) {
            iterable.add( toEntity( rapportDto ) );
        }

        return iterable;
    }

    @Override
    public Iterable<RapportDto> toDTOIterable(Iterable<RapportEnity> entities) {
        if ( entities == null ) {
            return null;
        }

        ArrayList<RapportDto> iterable = new ArrayList<RapportDto>();
        for ( RapportEnity rapportEnity : entities ) {
            iterable.add( toDto( rapportEnity ) );
        }

        return iterable;
    }
}

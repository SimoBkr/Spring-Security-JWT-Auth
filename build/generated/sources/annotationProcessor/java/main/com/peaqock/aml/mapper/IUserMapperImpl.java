package com.peaqock.aml.mapper;

import com.peaqock.aml.domain.UserEntity;
import com.peaqock.aml.dto.payload.UserPayload;
import com.peaqock.aml.shared.UserInfo;
import com.peaqock.aml.shared.enums.GroupEnum;
import com.peaqock.aml.shared.enums.RoleEnum;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-10-21T20:18:55+0100",
    comments = "version: 1.5.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.1.jar, environment: Java 11.0.16.1 (Amazon.com Inc.)"
)
@Component
public class IUserMapperImpl implements IUserMapper {

    @Override
    public UserEntity toEntity(UserInfo dto) {
        if ( dto == null ) {
            return null;
        }

        UserEntity.UserEntityBuilder userEntity = UserEntity.builder();

        userEntity.username( dto.getUsername() );
        userEntity.email( dto.getEmail() );
        userEntity.firstName( dto.getFirstName() );
        userEntity.lastName( dto.getLastName() );
        userEntity.organization( dto.getOrganization() );
        userEntity.telephone( dto.getTelephone() );
        userEntity.jobTitle( dto.getJobTitle() );
        userEntity.profile( dto.getProfile() );
        Set<RoleEnum> set = dto.getRoles();
        if ( set != null ) {
            userEntity.roles( new LinkedHashSet<RoleEnum>( set ) );
        }
        Set<GroupEnum> set1 = dto.getGroups();
        if ( set1 != null ) {
            userEntity.groups( new LinkedHashSet<GroupEnum>( set1 ) );
        }
        userEntity.active( dto.isActive() );

        return userEntity.build();
    }

    @Override
    public UserInfo toDto(UserEntity entity) {
        if ( entity == null ) {
            return null;
        }

        UserInfo.UserInfoBuilder userInfo = UserInfo.builder();

        userInfo.id( entity.getId() );
        userInfo.username( entity.getUsername() );
        userInfo.firstName( entity.getFirstName() );
        userInfo.lastName( entity.getLastName() );
        userInfo.email( entity.getEmail() );
        userInfo.organization( entity.getOrganization() );
        userInfo.telephone( entity.getTelephone() );
        userInfo.jobTitle( entity.getJobTitle() );
        userInfo.profile( entity.getProfile() );
        if ( entity.getActive() != null ) {
            userInfo.active( entity.getActive() );
        }
        Set<RoleEnum> set = entity.getRoles();
        if ( set != null ) {
            userInfo.roles( new LinkedHashSet<RoleEnum>( set ) );
        }
        Set<GroupEnum> set1 = entity.getGroups();
        if ( set1 != null ) {
            userInfo.groups( new LinkedHashSet<GroupEnum>( set1 ) );
        }
        userInfo.creationDate( entity.getCreationDate() );

        return userInfo.build();
    }

    @Override
    public List<UserEntity> toEntityList(List<UserInfo> dtos) {
        if ( dtos == null ) {
            return null;
        }

        List<UserEntity> list = new ArrayList<UserEntity>( dtos.size() );
        for ( UserInfo userInfo : dtos ) {
            list.add( toEntity( userInfo ) );
        }

        return list;
    }

    @Override
    public List<UserInfo> toDTOList(List<UserEntity> entities) {
        if ( entities == null ) {
            return null;
        }

        List<UserInfo> list = new ArrayList<UserInfo>( entities.size() );
        for ( UserEntity userEntity : entities ) {
            list.add( toDto( userEntity ) );
        }

        return list;
    }

    @Override
    public Iterable<UserEntity> toEntityIterable(Iterable<UserInfo> dtos) {
        if ( dtos == null ) {
            return null;
        }

        ArrayList<UserEntity> iterable = new ArrayList<UserEntity>();
        for ( UserInfo userInfo : dtos ) {
            iterable.add( toEntity( userInfo ) );
        }

        return iterable;
    }

    @Override
    public Iterable<UserInfo> toDTOIterable(Iterable<UserEntity> entities) {
        if ( entities == null ) {
            return null;
        }

        ArrayList<UserInfo> iterable = new ArrayList<UserInfo>();
        for ( UserEntity userEntity : entities ) {
            iterable.add( toDto( userEntity ) );
        }

        return iterable;
    }

    @Override
    public UserEntity payloadToEntity(UserPayload dto) {
        if ( dto == null ) {
            return null;
        }

        UserEntity.UserEntityBuilder userEntity = UserEntity.builder();

        userEntity.email( dto.getEmail() );
        userEntity.firstName( dto.getFirstName() );
        userEntity.lastName( dto.getLastName() );
        userEntity.organization( dto.getOrganization() );
        userEntity.telephone( dto.getTelephone() );
        userEntity.jobTitle( dto.getJobTitle() );
        userEntity.profile( dto.getProfile() );

        return userEntity.build();
    }
}

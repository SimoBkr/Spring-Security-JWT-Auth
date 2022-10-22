package com.peaqock.aml.dao;

import com.peaqock.aml.domain.UserEntity;
import com.peaqock.aml.shared.enums.GroupEnum;
import com.peaqock.aml.shared.enums.RoleEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    List<UserEntity> findAllByOrderByCreationDateDesc();

    boolean existsByUsernameIgnoreCase(String username);

    Optional<UserEntity> findByUsernameIgnoreCase(String username);

    @Modifying
    @Query("update UserEntity u set u.active = " +
            "case when u.active = true then false " +
            "when u.active = false then true " +
            "else true end where u.id = :id"
    )
    void updateStatusById(UUID id);

    @Query("from UserEntity a left join a.roles r where r = :role " +
            "and 1 = case when lower(a.username)  like lower(concat('%', :search,'%')) or :search is null then 1 else 0 end"
    )
    Page<UserEntity> findAllBySearch(RoleEnum role, String search, Pageable pageable);

    @Query("from UserEntity a left join a.groups g where g = :eGroup")
    List<UserEntity> findAllByGroups(@Param("eGroup") GroupEnum group);
}

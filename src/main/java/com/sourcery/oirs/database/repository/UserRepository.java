package com.sourcery.oirs.database.repository;

import com.sourcery.oirs.database.entity.UserEntity;
import com.sourcery.oirs.model.Role;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@Mapper
public interface UserRepository {

    @Select("SELECT * FROM employee e WHERE e.email = #{email}")
    Optional<UserEntity> findByEmail(@Param("email") String email);

    @Select("SELECT r.roleType from roles r WHERE employee_id = #{id}")
    List<Role> getRolesById(@Param("id") UUID id);
}



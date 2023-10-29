package com.sourcery.oirs.repository;

import com.sourcery.oirs.entity.UserEntity;
import com.sourcery.oirs.model.Role;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@Mapper
public interface UserRepository {

    @Select("SELECT * FROM employee e " +
            "LEFT JOIN roles r ON e.id = r.employee_id WHERE e.email = #{email}")
    Optional<UserEntity> findByEmail(@Param("email") String email);

    @Select("SELECT r.roleType from roles r WHERE employee_id = #{id}")
    List<Role> getRolesById(@Param("id") UUID id);
}



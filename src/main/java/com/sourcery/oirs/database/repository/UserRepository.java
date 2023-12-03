package com.sourcery.oirs.database.repository;

import com.sourcery.oirs.config.mybatis.UuidTypeHandler;
import com.sourcery.oirs.database.entity.UserEntity;
import com.sourcery.oirs.model.Role;
import com.sourcery.oirs.model.UserMainInfoResponseDTO;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@Mapper
public interface UserRepository {

    @Select("SELECT * FROM employee e WHERE e.email = #{email}")
    @Results(value = {
            @Result(property = "id", column = "id", typeHandler = UuidTypeHandler.class),
            @Result(property = "fullName", column = "full_Name"),
            @Result(property = "email", column = "email"),
            @Result(property = "password", column = "password"),
            @Result(property = "phoneNumber", column = "phone_number"),
            @Result(property = "position", column = "position"),
            @Result(property = "avatar", column = "avatar"),
            @Result(property = "roles", column = "id", javaType = List.class, many = @Many(select = "getRolesById"))
    })
    Optional<UserEntity> findByEmail(@Param("email") String email);

    @Select("SELECT e.email FROM employee e" +
            " LEFT JOIN employee_office eo ON e.id = eo.employee_id" +
            " WHERE eo.office_id = #{id} AND e.id IN (SELECT r.employee_id FROM roles r WHERE r.role_type = 'ADMIN')")
    @Results(value = {
            @Result(property = "id", column = "id", typeHandler = UuidTypeHandler.class),
            @Result(property = "fullName", column = "full_name"),
            @Result(property = "email", column = "email"),
            @Result(property = "roles", column = "id", javaType = List.class, many = @Many(select = "getRolesById"))
    })
    List<UserEntity> getAdminsByOfficeId(@Param("id") UUID id);

    @Select("SELECT r.role_type FROM roles r WHERE r.employee_id = #{id}")
    List<Role> getRolesById(@Param("id") UUID id);

    @Select("SELECT e.full_name FROM employee e WHERE e.email = #{email}")
    String getUserNameByEmail(@Param("email") String email);

    @Update("UPDATE employee SET  avatar = #{avatar} WHERE id = #{id}")
    void updateAvatar (UserEntity user);

        @Select("SELECT * FROM employee e WHERE e.id = #{id}")
    @Results(value = {
            @Result(property = "id", column = "id", typeHandler = UuidTypeHandler.class),
            @Result(property = "fullName", column = "full_Name"),
            @Result(property = "email", column = "email"),
            @Result(property = "password", column = "password"),
            @Result(property = "phoneNumber", column = "phone_number"),
            @Result(property = "position", column = "position"),
            @Result(property = "avatar", column = "avatar"),
            @Result(property = "roles", column = "id", javaType = List.class, many = @Many(select = "getRolesById"))
    })
    Optional<UserEntity> findById(@Param("id") UUID id);

    @Select("SELECT Employee.ID as id, Employee.FULL_NAME as fullName FROM Employee ")
    List<UserMainInfoResponseDTO> getAllUsers();
}



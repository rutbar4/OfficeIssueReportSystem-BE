package com.sourcery.oirs.database.repository;

import com.sourcery.oirs.config.mybatis.UuidTypeHandler;
import com.sourcery.oirs.database.entity.AddressEntity;
import com.sourcery.oirs.database.entity.CountryEntity;
import com.sourcery.oirs.database.entity.OfficeEntity;
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

    @Select("SELECT * FROM office o WHERE o.id = #{id}")
    @Results(value = {
            @Result(property = "id", column = "id", typeHandler = UuidTypeHandler.class),
            @Result(property = "name", column = "office_name")
    })
    Optional <OfficeEntity> getOfficeById(@Param("id") UUID id);

    @Select("SELECT * FROM country c WHERE c.id = #{id}")
    @Results(value = {
            @Result(property = "id", column = "id", typeHandler = UuidTypeHandler.class),
            @Result(property = "name", column = "country_name")
    })
    Optional <CountryEntity> getCountryById(@Param("id") UUID id);

    @Select("SELECT * FROM address e WHERE e.employee_id = #{id}")
    @Results(value = {
            @Result(property = "id", column = "id", typeHandler = UuidTypeHandler.class),
            @Result(property = "street", column = "street"),
            @Result(property = "postcode", column = "post_code"),
            @Result(property = "state", column = "state_province"),
            @Result(property = "city", column = "city"),
            @Result(property = "countryId", column = "country_id", typeHandler = UuidTypeHandler.class),
            @Result(property = "employeeId", column = "employee_id", typeHandler = UuidTypeHandler.class)
    })
    Optional <AddressEntity> findUserAddressByEmployeeId(@Param("id") UUID id);

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

    @Select("SELECT office_id FROM employee_office WHERE employee_id = #{id}")
    Optional<UUID> getOfficeIdByEmployeeId (@Param("id") UUID id);

}



package com.sourcery.oirs.database.repository;

import com.sourcery.oirs.config.mybatis.UuidTypeHandler;
import com.sourcery.oirs.database.entity.AddressEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.*;

import java.util.Optional;
import java.util.UUID;

@Repository
@Mapper
public interface AddressRepository {

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
    Optional<AddressEntity> findUserAddressByEmployeeId(@Param("id") UUID id);

    @Update("UPDATE address SET street = #{street}, city = #{city}, state_province = #{state},post_code = #{postcode}, country_id = #{countryId} WHERE employee_id = #{employeeId}")
    void update(AddressEntity address);



}

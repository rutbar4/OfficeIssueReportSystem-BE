package com.sourcery.oirs.database.repository;


import com.sourcery.oirs.model.OfficeResponseDTO;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import com.sourcery.oirs.config.mybatis.UuidTypeHandler;
import com.sourcery.oirs.database.entity.OfficeEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Mapper
@Repository
public interface OfficeRepository {

    @Select("SELECT * FROM office")
    @Results(value = {
            @Result(property = "id", column = "id", typeHandler = UuidTypeHandler.class),
            @Result(property = "name", column = "office_name")
    })
    List<OfficeEntity> getAll();

    @Select("SELECT office_id FROM employee_office WHERE employee_id = #{id}")
    Optional<UUID> getOfficeIdByEmployeeId (@Param("id") UUID id);

    @Select("SELECT * FROM office o WHERE o.id = #{id}")
    @Results(value = {
            @Result(property = "id", column = "id", typeHandler = UuidTypeHandler.class),
            @Result(property = "name", column = "office_name")
    })
    Optional <OfficeEntity> getOfficeById(@Param("id") UUID id);
    @Select("SELECT " +
            "o.ID as id, " +
            "o.OFFICE_NAME as name, " +
            "c.COUNTRY_NAME as country " +
            "FROM Office o " +
            "LEFT JOIN Country c ON o.COUNTRY_ID = c.ID")
    List<OfficeResponseDTO> findAllOffices();


}

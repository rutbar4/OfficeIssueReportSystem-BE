package com.sourcery.oirs.database.repository;

import com.sourcery.oirs.config.mybatis.UuidTypeHandler;
import com.sourcery.oirs.database.entity.CountryEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@Mapper
public interface CountryRepository {

    @Select("SELECT * FROM country")
    @Results(value ={
            @Result(property = "id", column = "id", typeHandler = UuidTypeHandler.class),
            @Result(property = "name", column = "country_name")
    })
    List<CountryEntity>getAll();

    @Select("SELECT * FROM country c WHERE c.id = #{id}")
    @Results(value = {
            @Result(property = "id", column = "id", typeHandler = UuidTypeHandler.class),
            @Result(property = "name", column = "country_name")
    })
    Optional<CountryEntity> getCountryById(@Param("id") UUID id);

}

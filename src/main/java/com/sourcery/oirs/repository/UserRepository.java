package com.sourcery.oirs.repository;

import com.sourcery.oirs.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Mapper
public interface UserRepository {

    @Select("SELECT * FROM user u " +
            "LEFT JOIN roles r ON r.user_id = u.id " +
            "WHERE u.email = #{email}")
    Optional<UserEntity> findByEmail(@Param("email") String email);
}


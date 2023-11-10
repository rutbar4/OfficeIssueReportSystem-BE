package com.sourcery.oirs.database.repository;


import com.sourcery.oirs.database.entity.CommentEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Mapper
@Repository
public interface CommentRepository {

    @Select("SELECT c.id, c.text, c.parent_id as parentId, c.likes, c.time, c.issue_id as issueId, c.employee_id, " +
            "e.id as userEntity_id, e.full_name as userEntity_fullName, e.avatar as userEntity_avatar " +
            "FROM comment c " +
            "LEFT JOIN employee e ON c.employee_id = e.id " +
            "WHERE c.issue_id = #{id}")
    @Results({
            @Result(property = "userEntity.id", column = "userEntity_id"),
            @Result(property = "userEntity.fullName", column = "userEntity_fullName"),
            @Result(property = "userEntity.avatar", column = "userEntity_avatar")
    })
    List<CommentEntity> getAllCommentsByIssueId(@Param("id") UUID id);

    @Select("SELECT c.id, c.text, c.parent_id as parentId, c.likes, c.time, c.issue_id as issueId, c.employee_id, " +
            "e.id as userEntity_id, e.full_name as userEntity_fullName, e.avatar as userEntity_avatar " +
            "FROM comment c " +
            "LEFT JOIN employee e ON c.employee_id = e.id " +
            "WHERE c.id = #{id}")
    @Results({
            @Result(property = "userEntity.id", column = "userEntity_id"),
            @Result(property = "userEntity.fullName", column = "userEntity_fullName"),
            @Result(property = "userEntity.avatar", column = "userEntity_avatar")
    })
    Optional<CommentEntity> getCommentById(@Param("id") UUID id);


    @Insert("INSERT INTO comment (id, text, time, likes, parent_id, issue_id, employee_id) VALUES " +
            "(#{id}, #{text}, #{time}, #{likes}, #{parentId}, #{issueId}, #{employeeId})")
    void saveComment(@RequestBody CommentEntity commentEntity);

    @Update("UPDATE comment SET likes = #{likes} WHERE id = #{id}")
    void updateCommentVotes(@Param("id") UUID id, @Param("likes") Integer likes);
}

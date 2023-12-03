package com.sourcery.oirs.database.repository;

import com.sourcery.oirs.database.entity.PictureEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Mapper
@Repository
public interface PictureRepository {

    @Insert("INSERT " +
            "INTO picture (id, link, issue_id, employee_id)" +
            "VALUES (#{i.id}, #{i.url}, #{i.issueId}, #{i.userId}) ")
    void insertPicture (@Param("i") PictureEntity picture);

    @Select("SELECT LINK FROM picture WHERE ISSUE_ID = #{issueId}")
    List<String> getPictureLinksByIssueId(@Param("issueId") UUID issueId);

    @Insert("INSERT INTO picture (id, link, issue_id, employee_id)\n" +
            "SELECT \n" +
            "    #{i.id}::uuid, \n" +
            "    #{i.url}, \n" +
            "    #{i.issueId}::uuid, \n" +
            "    #{i.userId}::uuid\n" +
            "WHERE NOT EXISTS (\n" +
            "    SELECT 1 FROM picture \n" +
            "    WHERE link = #{i.url} AND issue_id = #{i.issueId}\n" +
            ")")
    void addPicture(@Param("i") PictureEntity picture);

    @Delete("DELETE FROM picture WHERE issue_id = #{issueId} AND link = #{link}")
    void deletePicture(@Param("issueId") UUID issueId, @Param("link") String link);

}

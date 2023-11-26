package com.sourcery.oirs.database.repository;

import com.sourcery.oirs.database.entity.PictureEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface PictureRepository {

    @Insert("INSERT " +
            "INTO picture (id, link, issue_id, employee_id)" +
            "VALUES (#{i.id}, #{i.url}, #{i.issueId}, #{i.userId}) ")
    void insertPicture (@Param("i") PictureEntity picture);
}

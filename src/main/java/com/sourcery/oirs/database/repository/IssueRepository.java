package com.sourcery.oirs.database.repository;


import com.sourcery.oirs.database.entity.IssueEntity;
import com.sourcery.oirs.model.IssueDetailsResponseDto;
import com.sourcery.oirs.model.Issue;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Mapper
@Repository
public interface IssueRepository {
    @Select("SELECT issue.ISSUE_NAME as name, Employee.FULL_NAME as employeeName, issue.Start_Time as dateCreated, Issue.ISSUE_STATUS as status, " +
            "Issue.RATING, Office.OFFICE_NAME, Issue.description as description, Issue.Id as id, Issue.COMMENT_COUNT as commentCount " +
            "FROM issue " +
            "LEFT JOIN Employee ON Issue.EMPLOYEE_ID = Employee.ID " +
            "LEFT JOIN Office ON Issue.OFFICE_ID = Office.ID " +
            "WHERE issue.ID = #{id}")
     Optional<IssueDetailsResponseDto> findById(@Param("id") UUID id);

    @Select("SELECT " +
            "Issue.ID as id, " +
            "issue.ISSUE_NAME as name, " +
            "Issue.DESCRIPTION as description, " +
            "Issue.ISSUE_STATUS as status, " +
            "Issue.RATING as upvoteCount, " +
            "Issue.COMMENT_COUNT as commentCount, " +
            "Issue.Start_Time as time, " +
            "Issue.EMPLOYEE_ID as employeeId, " +
            "Issue.OFFICE_ID as officeId " +
            "FROM issue ")
    List<Issue> findAll();

    @Insert("INSERT INTO issue (id, issue_name, issue_status, start_time, finish_time, rating, description, employee_id, office_id)" +
            "VALUES (#{i.id}, #{i.name}, #{i.status}, #{i.startTime}, #{i.finishTime}, #{i.rating}, #{i.description}, #{i.employeeId}, #{i.officeId}) ")
    void insertIssue (@Param ("i") IssueEntity issue);

    @Select("SELECT issue.ISSUE_NAME as name, Employee.FULL_NAME as employeeName, issue.Start_Time as dateCreated, Issue.ISSUE_STATUS as status, " +
            "Issue.RATING, Office.OFFICE_NAME, Issue.description as description, Issue.Id as id, Issue.COMMENT_COUNT as commentCount " +
            "FROM issue " +
            "LEFT JOIN Employee ON Issue.EMPLOYEE_ID = Employee.ID " +
            "LEFT JOIN Office ON Issue.OFFICE_ID = Office.ID " +
            "WHERE issue.ISSUE_NAME = #{name}")
    Optional<IssueDetailsResponseDto> findByName(@Param("name") String name);

    @Select("SELECT * FROM issue WHERE id = #{id}")
    Optional<Issue> findIssue(@Param("id") UUID id);

    @Delete("DELETE from issue where id = #{id}")
    void delete(@Param("id") UUID id);

    @Select("SELECT "
            + "Issue.ID as id, "
            + "Issue.ISSUE_NAME as name, "
            + "Issue.DESCRIPTION as description, "
            + "Issue.ISSUE_STATUS as status, "
            + "Issue.RATING as upvoteCount, "
            + "Issue.COMMENT_COUNT as commentCount, "
            + "Issue.Start_Time as time, "
            + "Issue.EMPLOYEE_ID as employee_id, "
            + "Issue.OFFICE_ID as officeID "
            + "FROM issue "
            + "WHERE Issue.ISSUE_STATUS= 'Open' ")
    List<Issue> findOpen();

    @Select("SELECT "
            + "Issue.ID as id, "
            + "Issue.ISSUE_NAME as name, "
            + "Issue.DESCRIPTION as description, "
            + "Issue.ISSUE_STATUS as status, "
            + "Issue.RATING as upvoteCount, "
            + "Issue.COMMENT_COUNT as commentCount, "
            + "Issue.Start_Time as time, "
            + "Issue.EMPLOYEE_ID as employee_id, "
            + "Issue.OFFICE_ID as officeID "
            + "FROM issue "
            + "WHERE Issue.ISSUE_STATUS= 'Pending' ")
    List<Issue> findPlanned();
    @Select("SELECT "
            + "Issue.ID as id, "
            + "Issue.ISSUE_NAME as name, "
            + "Issue.DESCRIPTION as description, "
            + "Issue.ISSUE_STATUS as status, "
            + "Issue.RATING as upvoteCount, "
            + "Issue.COMMENT_COUNT as commentCount, "
            + "Issue.Start_Time as time, "
            + "Issue.EMPLOYEE_ID as employee_id, "
            + "Issue.OFFICE_ID as officeID "
            + "FROM issue "
            + "WHERE Issue.issue_status= 'Resolved' ")
    List<Issue> findResolved();


    @Select("SELECT "
            + "Issue.ID as id, "
            + "Issue.ISSUE_NAME as name, "
            + "Issue.DESCRIPTION as description, "
            + "Issue.ISSUE_STATUS as status, "
            + "Issue.RATING as upvoteCount, "
            + "Issue.COMMENT_COUNT as commentCount, "
            + "Issue.Start_Time as time, "
            + "Issue.EMPLOYEE_ID as employee_id, "
            + "Issue.OFFICE_ID as officeID "
            + "FROM issue "
            + "WHERE Issue.issue_status= 'Closed' ")
    List<Issue> findClosed();

    @Select("SELECT "
            + "Issue.ID as id, "
            + "Issue.ISSUE_NAME as name, "
            + "Issue.DESCRIPTION as description, "
            + "Issue.ISSUE_STATUS as status, "
            + "Issue.RATING as upvoteCount, "
            + "Issue.COMMENT_COUNT as commentCount, "
            + "Issue.Start_Time as time, "
            + "Issue.EMPLOYEE_ID as employee_id, "
            + "Issue.OFFICE_ID as officeID "
            + "FROM issue "
            + "LEFT JOIN employee on issue.employee_id = employee.id "
            + "WHERE employee.email= #{email} ")
    List<Issue> findReportedBy(@Param("email") String email);
    @Select("SELECT office.id as id FROM office WHERE office_name = #{name}")
    UUID getOfficeIdByName (@Param("name") String name);
}

package com.sourcery.oirs.database.repository;

import com.sourcery.oirs.database.entity.IssueEntity;
import com.sourcery.oirs.database.entity.OfficeEntity;
import com.sourcery.oirs.dto.response.IssueDetailsResponseDto;
import com.sourcery.oirs.model.Issue;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Mapper
@Repository
public interface IssueRepository {
    String BASE_SELECT_FIELDS = "Issue.ID as id, "
            + "Issue.ISSUE_NAME as name, "
            + "Issue.DESCRIPTION as description, "
            + "Issue.ISSUE_STATUS as status, "
            + "Issue.COMMENT_COUNT as commentCount, "
            + "Issue.Start_Time as time, "
            + "Issue.EMPLOYEE_ID as employee_id, "
            + "Issue.OFFICE_ID as officeID, ";
    @Select("SELECT " +
            "issue.ISSUE_NAME as name, " +
            "Employee.FULL_NAME as employeeName, " +
            "Employee.avatar as employeeAvatar, " +
            "issue.Start_Time as dateCreated, " +
            "Issue.ISSUE_STATUS as status, " +
            "Office.OFFICE_NAME, " +
            "Issue.description as description, " +
            "Issue.Id as id, " +
            "Issue.COMMENT_COUNT as commentCount, " +
            "Issue.office_id as officeId, " +
            "Issue.employee_id as employeeId " +
            "FROM issue " +
            "LEFT JOIN Employee ON Issue.EMPLOYEE_ID = Employee.ID " +
            "LEFT JOIN Office ON Issue.OFFICE_ID = Office.ID " +
            "WHERE issue.ID = #{id}")
    Optional<IssueDetailsResponseDto> findById(@Param("id") UUID id);

    @Select("SELECT " +
            BASE_SELECT_FIELDS +
            "(SELECT COUNT(*) FROM Vote WHERE vote.ISSUE_ID = issue.ID) as upvoteCount "
            + "FROM issue, vote "
            + "WHERE (#{returnAllOffices} IS TRUE OR Issue.OFFICE_ID = #{officeID}) "
            + "AND (#{returnAllEmployees} IS TRUE OR Issue.EMPLOYEE_ID = #{employeeID}) "
            + "AND (Issue.ISSUE_NAME ILIKE \'%${searchParameter}%\') "
            + "GROUP BY issue.id ")
    List<Issue> findAll(
            @Param ("officeID") UUID officeID,
            @Param ("employeeID") UUID employeeID,
            @Param ("returnAllOffices") boolean returnAllOffices,
            @Param ("returnAllEmployees") boolean returnAllEmployees,
            @Param ("searchParameter") String searchParameter);

    @Insert("INSERT " +
            "INTO issue (id, issue_name, issue_status, start_time, finish_time, description, employee_id, office_id)" +
            "VALUES (#{i.id}, #{i.name}, #{i.status}, #{i.startTime}, #{i.finishTime}, #{i.description}, #{i.employeeId}, #{i.officeId}) ")
    void insertIssue (@Param ("i") IssueEntity issue);

    @Select("SELECT " +
            "issue.ISSUE_NAME as name, " +
            "Employee.FULL_NAME as employeeName, " +
            "issue.Start_Time as dateCreated, " +
            "Issue.ISSUE_STATUS as status, " +
            "Office.OFFICE_NAME, " +
            "Issue.description as description, " +
            "Issue.Id as id, " +
            "Issue.COMMENT_COUNT as commentCount " +
            "FROM issue " +
            "LEFT JOIN Employee ON Issue.EMPLOYEE_ID = Employee.ID " +
            "LEFT JOIN Office ON Issue.OFFICE_ID = Office.ID " +
            "WHERE issue.ISSUE_NAME = #{name}")
    Optional<IssueDetailsResponseDto> findByName(@Param("name") String name);

    @Select("SELECT * FROM issue WHERE id = #{id}")
    Optional<Issue> findIssue(@Param("id") UUID id);

    @Delete("DELETE from issue where id = #{id}")
    void delete(@Param("id") UUID id);

    @Update("UPDATE issue SET description = #{description}, office_id = #{officeId}, ISSUE_STATUS = #{status} WHERE id = #{id}")
    void update(Issue issue);

    @Select("SELECT " +
            BASE_SELECT_FIELDS
            + "(SELECT COUNT(*) FROM vote WHERE vote.ISSUE_ID = issue.ID) as upvoteCount "
            + "FROM issue LEFT JOIN vote ON issue.id = vote.issue_id "
            + "WHERE (#{returnAllOffices} IS TRUE OR Issue.OFFICE_ID = #{officeID}) "
            + "AND (#{returnAllEmployees} IS TRUE OR Issue.EMPLOYEE_ID = #{employeeID}) "
            + "AND (Issue.ISSUE_NAME ILIKE \'%${searchParameter}%\') "
            + "GROUP BY issue.id "
            + "ORDER BY ${sortParameter} Issue.Start_time DESC "
            + "LIMIT #{limit} OFFSET #{offset} " )
    List<Issue> findAllIssuesPage(@Param ("offset") int offset,
                                  @Param ("limit") int limit,
                                  @Param ("officeID") UUID officeID,
                                  @Param ("employeeID") UUID employeeID,
                                  @Param ("returnAllOffices") boolean returnAllOffices,
                                  @Param ("returnAllEmployees") boolean returnAllEmployees,
                                  @Param ("sortParameter") String sortParameter,
                                  @Param ("searchParameter") String searchParameter);

    @Select("SELECT "
            + BASE_SELECT_FIELDS
            + "(SELECT COUNT(*) FROM Vote WHERE vote.ISSUE_ID = issue.ID) as upvoteCount "
            + "FROM issue, vote "
            + "WHERE Issue.issue_status= #{status} "
            + "AND (#{returnAllOffices} IS TRUE OR Issue.OFFICE_ID = #{officeID}) "
            + "AND (#{returnAllEmployees} IS TRUE OR Issue.EMPLOYEE_ID = #{employeeID}) "
            + "AND (Issue.ISSUE_NAME ILIKE \'%${searchParameter}%\') "
            + "GROUP BY issue.id ")
    List<Issue> findByStatus(@Param("status") String status,
                             @Param ("officeID") UUID officeID,
                             @Param ("employeeID") UUID employeeID,
                             @Param ("returnAllOffices") boolean returnAllOffices,
                             @Param ("returnAllEmployees") boolean returnAllEmployees,
                             @Param ("searchParameter") String searchParameter);

    @Select("SELECT "
            + BASE_SELECT_FIELDS
            + "(SELECT COUNT(*) FROM Vote WHERE vote.ISSUE_ID = issue.ID) as upvoteCount "
            + "FROM issue LEFT JOIN vote ON issue.id = vote.issue_id "
            + "WHERE Issue.issue_status= #{status} "
            + "AND (#{returnAllOffices} IS TRUE OR Issue.OFFICE_ID = #{officeID}) "
            + "AND (#{returnAllEmployees} IS TRUE OR Issue.EMPLOYEE_ID = #{employeeID}) "
            + "AND (Issue.ISSUE_NAME ILIKE \'%${searchParameter}%\') "
            + "GROUP BY issue.id "
            + "ORDER BY ${sortParameter} Issue.Start_time DESC "
            + "LIMIT #{limit} OFFSET #{offset}")
    List<Issue> findByStatusPage(@Param("status") String status,
                                 @Param ("offset") int offset,
                                 @Param ("limit") int limit,
                                 @Param ("officeID") UUID officeID,
                                 @Param ("employeeID") UUID employeeID,
                                 @Param ("returnAllOffices") boolean returnAllOffices,
                                 @Param ("returnAllEmployees") boolean returnAllEmployees,
                                 @Param ("sortParameter") String sortParameter,
                                 @Param ("searchParameter") String searchParameter);

    @Select("SELECT "
            + BASE_SELECT_FIELDS +
            "(SELECT COUNT(*) FROM Vote WHERE vote.ISSUE_ID = issue.ID) as upvoteCount " +
            "FROM issue LEFT JOIN vote ON issue.id = vote.issue_id "+
            "WHERE Issue.employee_id= #{id} "+
            "GROUP BY issue.id ")
    List<Issue> findReportedBy(@Param("id") UUID id);

    @Select("SELECT "
            + BASE_SELECT_FIELDS
            + "(SELECT COUNT(*) FROM Vote WHERE vote.ISSUE_ID = issue.ID) as upvoteCount "
            + "FROM issue LEFT JOIN vote ON issue.id = vote.issue_id "
            + "WHERE Issue.employee_id= #{id} "
            + "AND (#{returnAllOffices} IS TRUE OR Issue.OFFICE_ID = #{officeID}) "
            + "AND (#{returnAllEmployees} IS TRUE OR Issue.EMPLOYEE_ID = #{employeeID}) "
            + "AND (Issue.ISSUE_NAME ILIKE \'%${searchParameter}%\') "
            + "GROUP BY issue.id "
            + "ORDER BY ${sortParameter} Issue.Start_time DESC "
            + "LIMIT #{limit} OFFSET #{offset} ")
    List<Issue> findReportedByPage(@Param("id") UUID id,  @Param ("offset") int offset, @Param ("limit") int limit,
                                   @Param ("officeID") UUID officeID,
                                   @Param ("employeeID") UUID employeeID,
                                   @Param ("returnAllOffices") boolean returnAllOffices,
                                   @Param ("returnAllEmployees") boolean returnAllEmployees,
                                   @Param ("sortParameter") String sortParameter,
                                   @Param ("searchParameter") String searchParameter);

    @Select("SELECT office.id as id FROM office WHERE office_name = #{name}")
    UUID getOfficeIdByName (@Param("name") String name);

    @Update("UPDATE issue SET comment_count = comment_count + 1 WHERE id = #{issueId}")
    void updateIssueCommentCount(@Param("issueId") UUID issueId);

    @Select("SELECT office_name AS name FROM office WHERE id = #{officeId}")
    Optional<OfficeEntity> getIssueOfficeByOfficeId(@Param("officeId") UUID officeId);
}

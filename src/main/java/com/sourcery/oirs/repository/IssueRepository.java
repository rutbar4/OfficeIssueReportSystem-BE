package com.sourcery.oirs.repository;


import com.sourcery.oirs.dto.IssueDetailsResponseDto;
import com.sourcery.oirs.model.Issue;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Mapper
@Repository
public interface IssueRepository {
    @Select("SELECT issue.ISSUE_NAME as name, Employee.FULL_NAME, issue.Start_Time as dateCreated, Issue.ISSUE_STATUS as status, " +
            "Issue.RATING, Office.OFFICE_NAME, Issue.description as description, Issue.Id as id " +
            "FROM issue " +
            "LEFT JOIN Employee ON Issue.EMPLOYEE_ID = Employee.ID " +
            "LEFT JOIN Office ON Issue.OFFICE_ID = Office.ID " +
            "WHERE issue.ID = #{id}")
     Optional<IssueDetailsResponseDto> findById(@Param("id") UUID id);

    @Select("SELECT Employee.FULL_NAME " +
            "FROM Issue " +
            "LEFT JOIN Employee ON Issue.EMPLOYEE_ID = Employee.ID " +
            "WHERE Issue.ID = #{id}")
    Optional<String> findEmployeeNameByIssueId(@Param("id") UUID issueId);

    @Select("SELECT Office.OFFICE_NAME " +
            "FROM Issue " +
            "LEFT JOIN Office ON Issue.OFFICE_ID = Office.ID " +
            "WHERE Issue.ID = #{id}")
    Optional<String> findOfficeNameByIssueId(@Param("id") UUID issueId);
}

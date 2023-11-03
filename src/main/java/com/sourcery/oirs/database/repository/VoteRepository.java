package com.sourcery.oirs.database.repository;

import com.sourcery.oirs.dto.response.VoteResponseDto;
import com.sourcery.oirs.model.Vote;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Mapper
@Repository
public interface VoteRepository {
//    @Select("SELECT EXISTS ( SELECT * FROM table WHERE issue.ID = #{IssueId} AND user.ID = #{UserId}")
//@Insert("INSERT INTO Vote (id, issue_id, employee_id) VALUES (#{id}, #{issueId}, #{employeeId})")
//void insert(Vote vote);

    @Insert("INSERT INTO Vote (id, issue_id, employee_id) " +
            "SELECT #{id}, #{issueId}, #{employeeId} " +
            "WHERE " +
            "NOT EXISTS (" +
            "SELECT issue_id, employee_id FROM Vote WHERE issue_id = #{issueId} AND employee_id = #{employeeId})")
    void insert(Vote vote);

    @Select("SELECT * FROM Vote WHERE ISSUE_ID = #{IssueId} AND EMPLOYEE_ID= #{employeeId}")
    Optional<VoteResponseDto> GetVote(@Param("IssueId") UUID IssueId, @Param("employeeId") UUID UserId);

    @Select("DELETE FROM Vote WHERE ISSUE_ID = #{IssueId} AND EMPLOYEE_ID= #{employeeId}")
    Optional<VoteResponseDto> DeleteVote(@Param("IssueId") UUID IssueId, @Param("employeeId") UUID UserId);

}

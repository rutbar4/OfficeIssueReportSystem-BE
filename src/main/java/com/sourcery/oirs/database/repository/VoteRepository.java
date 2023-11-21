package com.sourcery.oirs.database.repository;

import com.sourcery.oirs.dto.response.VoteCountResponseDto;
import com.sourcery.oirs.dto.response.VoteResponseDto;
import com.sourcery.oirs.model.Vote;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Mapper
@Repository
public interface VoteRepository {
    @Insert("INSERT INTO Vote (id, issue_id, employee_id) " +
            "SELECT #{id}, #{issueId}, #{employeeId} " +
            "WHERE " +
            "NOT EXISTS (" +
            "SELECT issue_id, employee_id FROM Vote WHERE issue_id = #{issueId} AND employee_id = #{employeeId})")
    void insert(Vote vote);

    @Select("SELECT * FROM Vote WHERE ISSUE_ID = #{IssueId} AND EMPLOYEE_ID= #{employeeId}")
    Optional<VoteResponseDto> getVote(@Param("IssueId") UUID IssueId, @Param("employeeId") UUID EmployeeId);

    @Select("SELECT COUNT(*) FROM Vote WHERE ISSUE_ID = #{IssueId}")
    VoteCountResponseDto getVoteCount(@Param("IssueId") UUID IssueId);

    @Select("DELETE FROM Vote WHERE ISSUE_ID = #{IssueId} AND EMPLOYEE_ID= #{employeeId}")
    Optional<VoteResponseDto> deleteVote(@Param("IssueId") UUID IssueId, @Param("employeeId") UUID EmployeeId);

}

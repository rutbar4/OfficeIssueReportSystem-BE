package com.sourcery.oirs.service;

import com.sourcery.oirs.database.entity.IssueEntity;
import com.sourcery.oirs.database.repository.IssueRepository;
import com.sourcery.oirs.exceptions.BusyIssueNameException;
import com.sourcery.oirs.exceptions.IssueNotFoundException;
import com.sourcery.oirs.model.Issue;
import com.sourcery.oirs.model.IssueDetailsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class IssueService {
    private static final String ISSUE_NOT_FOUND = "Issue with %s id not found";

    private final IssueRepository issueRepository;

    public List<Issue> getAllIssue() {
        return issueRepository.findAll();
    }

    public IssueDetailsResponseDto getIssueDetails(UUID id) {
        IssueDetailsResponseDto issue = issueRepository.findById(id)
                .orElseThrow(() -> new IssueNotFoundException(String.format(ISSUE_NOT_FOUND, id)));
        return issue;
    }

    public  void ReportNewIssue (Issue issue){

        Optional<IssueDetailsResponseDto> issueName = issueRepository.findByName(issue.getName());
        if (issueName.isPresent()){
            throw new BusyIssueNameException();
        }

        IssueEntity issueEntity = new IssueEntity(
                UUID.randomUUID(),
                issue.getName(),
                issue.getStatus(),
                issue.getDescription(),
                null,
                Timestamp.valueOf(LocalDateTime.now()),
                null,
                issue.getEmployeeId(),
                issue.getOfficeId(),
                null
        );

        issueRepository.insertIssue(issueEntity);

    }
}

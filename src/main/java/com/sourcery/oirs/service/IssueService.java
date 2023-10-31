package com.sourcery.oirs.service;

import com.sourcery.oirs.database.repository.IssueRepository;
import com.sourcery.oirs.exceptions.IssueNotFoundException;
import com.sourcery.oirs.model.Issue;
import com.sourcery.oirs.model.IssueDetailsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class IssueService {
    private static final String ISSUE_NOT_FOUND = "Issue with %s id not found";

    private final IssueRepository issueRepository;

    public List<Issue> getAllIssue() {
        List<Issue> allIssues = issueRepository.findAll();
        return allIssues;
    }

    public IssueDetailsResponseDto getIssueDetails(UUID id) {
        IssueDetailsResponseDto issue = issueRepository.findById(id)
                .orElseThrow(() -> new IssueNotFoundException(String.format(ISSUE_NOT_FOUND, id)));
        return issue;
    }
}

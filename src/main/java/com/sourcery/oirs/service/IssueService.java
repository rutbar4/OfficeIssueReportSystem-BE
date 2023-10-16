package com.sourcery.oirs.service;

import com.sourcery.oirs.dto.IssueDetailsResponseDto;
import com.sourcery.oirs.exceptions.IssueNotFoundException;
import com.sourcery.oirs.model.Issue;
import com.sourcery.oirs.repository.IssueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class IssueService {
    private static final String ISSUE_NOT_FOUND = "Issue with %s id not found";

    private final IssueRepository issueRepository;


    public List<Issue> getAllIssue() {
        return Stream.of(
                new Issue(UUID.randomUUID(),
                        "Example Issue 1",
                        "There is not enough parking slots in Kaunas office.",
                        "Open",
                        4L,
                        2L,
                        LocalDate.of(2023, 10, 8), UUID.randomUUID(), UUID.randomUUID()),
                new Issue(UUID.randomUUID(),
                        "Example Issue 2",
                        "Equipment failures occur during presentation or client meeting.",
                        "Open"
                        , 108L,
                        5L,
                        LocalDate.of(2023, 10, 8), UUID.randomUUID(), UUID.randomUUID()),
                new Issue(UUID.randomUUID(),
                        "Example Issue 3",
                        "Frequent connectivity issues and slow internet connection hinder productivity.",
                        "Open"
                        , 9L,
                        1L,
                        LocalDate.of(2023, 10, 8), UUID.randomUUID(), UUID.randomUUID()),
                new Issue(UUID.randomUUID(),
                        "Example Issue 4",
                        "Equipment failures occur during presentation or client meeting.",
                        "Open"
                        , 108L,
                        5L,
                        LocalDate.of(2023, 10, 8), UUID.randomUUID(), UUID.randomUUID())
                        ).collect(Collectors.toList());
    }

    public IssueDetailsResponseDto GetIssueDetails(UUID id) {
        IssueDetailsResponseDto issue = issueRepository.findById(id)
                .orElseThrow(() -> new IssueNotFoundException(String.format(ISSUE_NOT_FOUND, id)));
        return IssueDetailsResponseDto.of(issue);
    }

}

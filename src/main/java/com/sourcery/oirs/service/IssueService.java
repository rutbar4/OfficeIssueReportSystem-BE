package com.sourcery.oirs.service;

import com.sourcery.oirs.database.entity.IssueEntity;
import com.sourcery.oirs.database.entity.UserEntity;
import com.sourcery.oirs.database.repository.IssueRepository;
import com.sourcery.oirs.database.repository.UserRepository;
import com.sourcery.oirs.email.EmailService;
import com.sourcery.oirs.exceptions.BusyIssueNameException;
import com.sourcery.oirs.exceptions.IssueNotFoundException;
import com.sourcery.oirs.model.Issue;
import com.sourcery.oirs.dto.response.IssueDetailsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class IssueService {
    private static final String ISSUE_NOT_FOUND = "Issue with %s id not found";

    private final IssueRepository issueRepository;
    private final UserRepository userRepository;
    private final EmailService emailService;
    private final VoteService voteService;

    public List<Issue> getAllIssue() {
        var issues = issueRepository.findAll();
        for (var issue :issues) {
            var issueID = issue.getId();
            var count = voteService.voteCount(issueID).count;
            issue.setVoteCount(count);
        }
        return issues;
    }


    public IssueDetailsResponseDto getIssueDetails(UUID id) {
         var issue = issueRepository.findById(id)
                .orElseThrow(() -> new IssueNotFoundException(String.format(ISSUE_NOT_FOUND, id)));
                issue.setVoteCount(voteService.voteCount(issue.getId()).count);
         return issue;
    }


    public void deleteIssue(UUID id) {
        issueRepository.findIssue(id)
                .orElseThrow(() -> new IssueNotFoundException(String.format(ISSUE_NOT_FOUND, id)));
        issueRepository.delete(id);
    }
    public List<Issue> getIssuesByStatus(String status) { return issueRepository.findByStatus(status); }
    public List<Issue> getUserIssues(UUID id){ return issueRepository.findReportedBy(id); }





    // When saving a new issue in the database, use this method to send a message to the office admins about new issue
    // Also need to create real admins emails in the database
    private void sendEmailToAdmins(Issue issue) {
        List<String> emailsOfAdmins = getAdminsEmailByOffice(issue.getOfficeId());
        String emailOfIssueCreator = SecurityContextHolder.getContext().getAuthentication().getName();
        String nameOfIssueCreator = userRepository.getUserNameByEmail(emailOfIssueCreator);
        String messageToAdmin = createIssueMessage(nameOfIssueCreator, emailOfIssueCreator, issue.getName(),
                issue.getDescription(), LocalDate.from(issue.getTime()));
        emailsOfAdmins.forEach(email -> emailService.sendEmail(email, issue.getName(), messageToAdmin));
    }

    private List<String> getAdminsEmailByOffice(UUID officeId) {
        return userRepository.getAdminsByOfficeId(officeId)
                .stream().map(UserEntity::getEmail).toList();
    }

    private String createIssueMessage (String employee,
                                       String email,
                                       String issueName,
                                       String description,
                                       LocalDate time) {
        return String.format("""
                New Issue: %s%n
                Created by %s%n
                Email: %s/%n
                Created at %s%n
                Issue description: %s""", issueName, employee, email, time, description);
    }

    public void reportNewIssue (Issue issue) {
        Optional<IssueDetailsResponseDto> issueName = issueRepository.findByName(issue.getName());
        if (issueName.isPresent()){
            throw new BusyIssueNameException();
        }
        UUID officeId = issueRepository.getOfficeIdByName(issue.getName());
        issueRepository.insertIssue(
                IssueEntity.builder()
                        .id(UUID.randomUUID())
                        .name(issue.getName())
                        .status("open")
                        .description(issue.getDescription())
                        .commentCount(0.00)
                        .startTime(Timestamp.valueOf(LocalDateTime.now()))
                        .finishTime(null)
                        .employeeId(issue.getEmployeeId())
                        .officeId(issue.getOfficeId())
                        .rating(issue.getUpvoteCount())
                        .build()
        );
//        sendEmailToAdmins(issue);
    }
}

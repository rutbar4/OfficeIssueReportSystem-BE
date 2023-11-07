package com.sourcery.oirs.service;

import com.sourcery.oirs.database.entity.UserEntity;
import com.sourcery.oirs.database.repository.IssueRepository;
import com.sourcery.oirs.database.repository.UserRepository;
import com.sourcery.oirs.email.EmailService;
import com.sourcery.oirs.exceptions.IssueNotFoundException;
import com.sourcery.oirs.model.Issue;
import com.sourcery.oirs.model.IssueDetailRequestDto;
import com.sourcery.oirs.model.IssueDetailsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class IssueService {
    private static final String ISSUE_NOT_FOUND = "Issue with %s id not found";

    private final IssueRepository issueRepository;
    private final UserRepository userRepository;
    private final EmailService emailService;

    public List<Issue> getAllIssue() {
        return issueRepository.findAll();
    }


    public IssueDetailsResponseDto getIssueDetails(UUID id) {
        return issueRepository.findById(id)
                .orElseThrow(() -> new IssueNotFoundException(String.format(ISSUE_NOT_FOUND, id)));
    }


    public void deleteIssue(UUID id) {
        issueRepository.findIssue(id)
                .orElseThrow(() -> new IssueNotFoundException(String.format(ISSUE_NOT_FOUND, id)));
        issueRepository.delete(id);
    }


    // When saving a new issue in the database, use this method to send a message to the office admins about new issue
    // Also need to create real admins emails in the database
    private void sendEmailToAdmins(Issue issue) {
        List<String> emailsOfAdmins = getAdminsEmailByOffice(issue.getOfficeId());
        String emailOfIssueCreator = SecurityContextHolder.getContext().getAuthentication().getName();
        String nameOfIssueCreator = userRepository.getUserNameByEmail(emailOfIssueCreator);
        String messageToAdmin = createIssueMessage(nameOfIssueCreator, emailOfIssueCreator, issue.getName(),
                issue.getDescription(), issue.getTime());
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
                New Issue: %s\n
                Created by %s\n
                Email: %s/\n
                Created at %s\n
                Issue description: %s""", issueName, employee, email, time, description);
    }

    public void updateIssue(IssueDetailRequestDto requestDto, UUID id) {
        Issue existingIssue = issueRepository.findIssue(id)
                .orElseThrow(() -> new IssueNotFoundException(String.format(ISSUE_NOT_FOUND, id)));
        existingIssue.setDescription(requestDto.getDescription());
        existingIssue.setOfficeId(requestDto.getOfficeId());
        existingIssue.setStatus(requestDto.getStatus());
        issueRepository.update(existingIssue);
    }
}

package com.sourcery.oirs.service;

import com.sourcery.oirs.database.entity.IssueEntity;
import com.sourcery.oirs.database.entity.OfficeEntity;
import com.sourcery.oirs.database.entity.UserEntity;
import com.sourcery.oirs.database.repository.IssueRepository;
import com.sourcery.oirs.database.repository.OfficeRepository;
import com.sourcery.oirs.database.repository.UserRepository;
import com.sourcery.oirs.dto.response.IssueDetailsResponseDto;
import com.sourcery.oirs.email.EmailService;
import com.sourcery.oirs.exceptions.BusyIssueNameException;
import com.sourcery.oirs.exceptions.IssueNotFoundException;
import com.sourcery.oirs.exceptions.OfficeNotFoundException;
import com.sourcery.oirs.model.Issue;
import com.sourcery.oirs.model.IssueDetailRequestDto;
import com.sourcery.oirs.model.OfficeResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class IssueService {
    private static final String ISSUE_NOT_FOUND = "Issue with %s id not found";

    private final IssueRepository issueRepository;
    private final UserRepository userRepository;
    private final EmailService emailService;
    private final OfficeRepository officeRepository;
    private final VoteService voteService;
    private final PictureService pictureService;

    public List<Issue> getAllIssue(int offset, int limit, UUID officeID, UUID employeeID, String sortParameter, String searchParameter) {
        // If no office ID is given it makes the request return issues from all offices
        boolean returnAllOffices = officeID == null;
        // If no employee ID is given it makes the request return issues from all employees
        boolean returnAllEmployees = employeeID == null;
        if(!Objects.equals(sortParameter, "")) sortParameter += ',';
        return issueRepository.findAllIssuesPage((offset - 1) * limit, limit, officeID, employeeID, returnAllOffices, returnAllEmployees, sortParameter, searchParameter);
    }

    public IssueDetailsResponseDto getIssueById(UUID id) {
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


    public List<Issue> getIssuesByStatus(String status, int offset, int limit, UUID officeID, UUID employeeID, String sortParameter, String searchParameter) {
        // If no office ID is given it makes the request return issues from all offices
        boolean returnAllOffices = officeID == null;
        // If no employee ID is given it makes the request return issues from all employees
        boolean returnAllEmployees = employeeID == null;
        if(!Objects.equals(sortParameter, "")) sortParameter += ',';
        return issueRepository.findByStatusPage(status, (offset - 1) * limit, limit, officeID, employeeID, returnAllOffices, returnAllEmployees, sortParameter, searchParameter);
    }

    public List<Issue> getUserIssues(UUID id, int offset, int limit, UUID officeID, UUID employeeID, String sortParameter, String searchParameter) {
        // If no office ID is given it makes the request return issues from all offices
        boolean returnAllOffices = officeID == null;
        // If no employee ID is given it makes the request return issues from all employees
        boolean returnAllEmployees = employeeID == null;
        if(!Objects.equals(sortParameter, "")) sortParameter += ',';
        return issueRepository.findReportedByPage(id, (offset - 1) * limit, limit, officeID, employeeID, returnAllOffices, returnAllEmployees, sortParameter, searchParameter);
    }

    public void updateIssue(IssueDetailRequestDto requestDto, UUID id) {
        Issue existingIssue = issueRepository.findIssue(id)
                .orElseThrow(() -> new IssueNotFoundException(String.format(ISSUE_NOT_FOUND, id)));
        existingIssue.setDescription(requestDto.getDescription());
        existingIssue.setOfficeId(requestDto.getOfficeId());
        existingIssue.setStatus(requestDto.getStatus());
        issueRepository.update(existingIssue);
    }

    public List<OfficeResponseDTO> getAllOffices() {
        return officeRepository.findAllOffices();
    }

    public void reportNewIssue (Issue issue) {
        Optional<IssueDetailsResponseDto> issueName = issueRepository.findByName(issue.getName());
        if (issueName.isPresent()) {
            throw new BusyIssueNameException();
        }
        UUID officeId = issueRepository.getOfficeIdByName(issue.getName());

        UUID issueId = UUID.randomUUID();
        issueRepository.insertIssue(
                IssueEntity.builder()
                        .id(issueId)
                        .name(issue.getName())
                        .status("Open")
                        .description(issue.getDescription())
                        .commentCount(0.00)
                        .startTime(Timestamp.valueOf(LocalDateTime.now()))
                        .finishTime(null)
                        .employeeId(issue.getEmployeeId())
                        .officeId(issue.getOfficeId())
                        .rating(issue.getUpvoteCount())
                        .build()
        );

       List<String> images = issue.getImages();
       for (String url: images){
           pictureService.savePicture(url, issueId, issue.getEmployeeId());
       }

        sendEmailToAdmins(issue);
    }

    public int getAllPageCount(UUID officeID, UUID employeeID, String searchParameter) {
        // If no office ID is given it makes the request return issues from all offices
        boolean returnAllOffices = officeID == null;
        // If no employee ID is given it makes the request return issues from all employees
        boolean returnAllEmployees = employeeID == null;
        return (int) Math.ceil((double) (issueRepository.findAll(officeID, employeeID, returnAllOffices, returnAllEmployees, searchParameter).size()) / 10);
    }

    public int getStatusPageCount(String status, UUID officeID, UUID employeeID, String searchParameter) {
        // If no office ID is given it makes the request return issues from all offices
        boolean returnAllOffices = officeID == null;
        // If no employee ID is given it makes the request return issues from all employees
        boolean returnAllEmployees = employeeID == null;
        return issueRepository.findByStatus(status, officeID, employeeID, returnAllOffices, returnAllEmployees, searchParameter).size() / 10 + 1;
    }

    public int getUserPageCount(UUID id) {
        return issueRepository.findReportedBy(id).size() / 10 + 1;
    }

    private void sendEmailToAdmins(Issue issue) {
        List<String> emailsOfAdmins = getAdminsEmailByOffice(issue.getOfficeId());
        String emailOfIssueCreator = SecurityContextHolder.getContext().getAuthentication().getName();
        String nameOfIssueCreator = userRepository.getUserNameByEmail(emailOfIssueCreator);
        OfficeEntity office = issueRepository.getIssueOfficeByOfficeId(issue.getOfficeId())
                .orElseThrow(() -> new OfficeNotFoundException("Such office doesn't exist. No found by id " + issue.getOfficeId()));
        String messageToAdmin = createIssueMessage(nameOfIssueCreator, emailOfIssueCreator, office.getName(), issue.getName(),
                issue.getDescription(), LocalDateTime.now());
        emailsOfAdmins.forEach(email -> emailService.sendEmail(email, issue.getName(), messageToAdmin));
    }

    private List<String> getAdminsEmailByOffice(UUID officeId) {
        return userRepository.getAdminsByOfficeId(officeId)
                .stream().map(UserEntity::getEmail).toList();
    }

    private String createIssueMessage (String employee,
                                       String email,
                                       String office,
                                       String issueName,
                                       String description,
                                       LocalDateTime time) {
        String formattedDescription = removeHtmlTagsFromText(description);
        String formattedTime = time.format(DateTimeFormatter.ofPattern("yyyy MM dd HH:mm"));
        log.info("Office in message to backend: " + office);
        log.info("Date in message to backend: " + formattedTime);
        return String.format("""
                New Issue: %s%n
                Office: %s%n
                Created by: %s%n
                Email: %s%n
                Created at: %s%n
                Issue description: %s""", issueName, office, employee, email, formattedTime, formattedDescription);
    }

    private String removeHtmlTagsFromText(String input) {
        return input.replaceAll("<[^>]*>", "");
    }
}

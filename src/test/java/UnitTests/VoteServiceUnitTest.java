package UnitTests;

import com.sourcery.oirs.database.entity.UserEntity;
import com.sourcery.oirs.database.repository.IssueRepository;
import com.sourcery.oirs.database.repository.UserRepository;
import com.sourcery.oirs.database.repository.VoteRepository;
import com.sourcery.oirs.dto.response.IssueDetailsResponseDto;
import com.sourcery.oirs.dto.response.VoteCountResponseDto;
import com.sourcery.oirs.dto.response.VoteResponseDto;
import com.sourcery.oirs.service.VoteService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.runners.model.MultipleFailureException.assertEmpty;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

class VoteServiceUnitTest {
    UUID validIssueId;
    UUID validEmployeeId;
    VoteRepository voteRepository = Mockito.mock(VoteRepository.class);
    IssueRepository issueRepository = Mockito.mock(IssueRepository.class);
    UserRepository userRepository = Mockito.mock(UserRepository.class);
    VoteService voteService = new VoteService(voteRepository, issueRepository, userRepository);

    @BeforeEach
    void init() {
        validIssueId = UUID.randomUUID();
        validEmployeeId = UUID.randomUUID();
    }
    @Test
    void CreatVote_notExistingIssueId_returnNull() {
        //Act
        when(issueRepository.findById(Mockito.any())).thenReturn(Optional.empty());
        var result = voteService.createVote(validIssueId, validEmployeeId);

        //Assert
        assertEquals("issue does not exist", result.getBody());
        assertTrue(result.getStatusCode().isError());
    }

    @Test
    void CreatVote_notExistingUserId_returnMessage_isError() {
        //Setup
        Optional<IssueDetailsResponseDto> issueResponse = Optional.of(Mockito.mock(IssueDetailsResponseDto.class));

        //Act
        when(userRepository.findById(Mockito.any())).thenReturn(Optional.empty());
        when(issueRepository.findById(Mockito.any())).thenReturn(issueResponse);
        var result = voteService.createVote(validIssueId, validEmployeeId);

        //Assert
        assertEquals("employee does not exist", result.getBody());
        assertTrue(result.getStatusCode().isError());
    }

    @Test
    void CreatVote_validIssueIdAndUserId_returnNull() {
        //Setup
        Optional<IssueDetailsResponseDto> issueResponse = Optional.of(Mockito.mock(IssueDetailsResponseDto.class));
        Optional<UserEntity> userResponse = Optional.of(Mockito.mock(UserEntity.class));

        //Act
        when(issueRepository.findById(Mockito.any())).thenReturn(issueResponse);
        when(userRepository.findById(Mockito.any())).thenReturn(userResponse);
        doNothing().when(voteRepository).insert(Mockito.any());
        var result = voteService.createVote(validIssueId, validEmployeeId);

        //Assert
        assertEquals(result.getBody().getClass(), VoteResponseDto.class);
    }
    @Test
    void isVoted_ValidVotedIssueIdAndEmployeeId_true() {
        //Setup
        UUID validId = UUID.randomUUID();
        var voteResponseDto = new VoteResponseDto(validId);
        Optional<VoteResponseDto> voteResponse = Optional.of(voteResponseDto);

        //Act
        when(voteRepository.getVote(validIssueId, validEmployeeId)).thenReturn(voteResponse);
        var result = voteService.isVoted(validIssueId, validEmployeeId);

        //Assert
        assertTrue(result.isVoted);
    }
    @Test
    void isVoted_ValidVotedIssueIdAndEmployeeId_false() {
        //Act
        when(voteRepository.getVote(validIssueId, validEmployeeId)).thenReturn(Optional.empty());
        var result = voteService.isVoted(validIssueId, validEmployeeId);

        //Assert
        assertFalse(result.isVoted);
    }

    @Test
    void VoteCount_validIssueId_Equals5() {
        //Setup
        int expectedCount = 5;
        VoteCountResponseDto returnC = new VoteCountResponseDto(expectedCount);
        //Act
        when(voteRepository.getVoteCount(validIssueId)).thenReturn(returnC);
        var result = voteService.voteCount(validIssueId);

        //Assert
        assertEquals(expectedCount, result.count);
    }

    @Test
    void VoteCount_validIssueId_Equals0() {
        //Setup
        int expectedCount = 0;
        VoteCountResponseDto returnC = new VoteCountResponseDto(expectedCount);
        //Act
        when(voteRepository.getVoteCount(validIssueId)).thenReturn(returnC);
        var result = voteService.voteCount(validIssueId);

        //Assert
        assertEquals(expectedCount, result.count);
    }
}
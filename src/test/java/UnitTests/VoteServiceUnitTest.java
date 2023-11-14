package UnitTests;

import com.sourcery.oirs.database.entity.UserEntity;
import com.sourcery.oirs.database.repository.IssueRepository;
import com.sourcery.oirs.database.repository.UserRepository;
import com.sourcery.oirs.database.repository.VoteRepository;
import com.sourcery.oirs.dto.response.IssueDetailsResponseDto;
import com.sourcery.oirs.dto.response.VoteCountResponseDto;
import com.sourcery.oirs.dto.response.VoteResponseDto;
import com.sourcery.oirs.service.VoteService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

class VoteServiceUnitTest {
    UUID validIssueId;
    UUID validEmployeeId;
    VoteRepository voteRepository = Mockito.mock(VoteRepository.class);
    IssueRepository issueRepository = Mockito.mock(IssueRepository.class);
    UserRepository userRepository = Mockito.mock(UserRepository.class);
    VoteService voteService = new VoteService(voteRepository, issueRepository, userRepository);

    @Before
    public void Setup() {
        validIssueId = UUID.randomUUID();
        validEmployeeId = UUID.randomUUID();
    }
    @Test
    public void CreatVote_notExistingIssueId_returnNull() {
        //Setup

        //Act
        when(issueRepository.findById(Mockito.any())).thenReturn(Optional.empty());
        var result = voteService.CreateVote(validIssueId, validEmployeeId);

        //Assert
        assertNull(result);
    }

    @Test
    public void CreatVote_notExistingUserId_returnNull() {
        //Setup

        //Act
        when(userRepository.findById(Mockito.any())).thenReturn(Optional.empty());
        var result = voteService.CreateVote(validIssueId, validEmployeeId);

        //Assert
        assertNull(result);
    }

    @Test
    public void CreatVote_validIssueIdAndUserId_returnNull() {
        //Setup
        Optional<IssueDetailsResponseDto> issueResponse = Optional.of(Mockito.mock(IssueDetailsResponseDto.class));
        Optional<UserEntity> userResponse = Optional.of(Mockito.mock(UserEntity.class));

        //Act
        when(issueRepository.findById(Mockito.any())).thenReturn(issueResponse);
        when(userRepository.findById(Mockito.any())).thenReturn(userResponse);
        doNothing().when(voteRepository).insert(Mockito.any());
        var result = voteService.CreateVote(validIssueId, validEmployeeId);

        //Assert
        assertEquals(result.getClass(), VoteResponseDto.class);
    }
    @Test
    void isVoted_ValidVotedIssueIdAndEmployeeId_true() {
        //Setup

        //Act
//        when(voteRepository.GetVote(validIssueId, validEmployeeId)).thenReturn()

        //Assert
        assertTrue(voteService.IsVoted(validIssueId, validEmployeeId).isVoted);
    }

    @Test
    public void VoteCount_validIssueId_Equals5() {
        //Setup
        int expectedCount = 5;
        VoteCountResponseDto returnC = new VoteCountResponseDto(expectedCount);
        //Act
        when(voteRepository.GetVoteCount(validIssueId)).thenReturn(returnC);
        var result = voteService.VoteCount(validIssueId);

        //Assert
        assertEquals(expectedCount, result.count);
    }
}
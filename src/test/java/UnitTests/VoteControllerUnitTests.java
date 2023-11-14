package UnitTests;

import com.sourcery.oirs.database.entity.UserEntity;
import com.sourcery.oirs.database.repository.IssueRepository;
import com.sourcery.oirs.database.repository.UserRepository;
import com.sourcery.oirs.database.repository.VoteRepository;
import com.sourcery.oirs.dto.response.IssueDetailsResponseDto;
import com.sourcery.oirs.dto.response.VoteCountResponseDto;
import com.sourcery.oirs.dto.response.VoteResponseDto;
import com.sourcery.oirs.model.Vote;
import com.sourcery.oirs.service.VoteService;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;
import java.util.UUID;

import static java.util.Optional.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

public class VoteControllerUnitTests {
    UUID validIssueId;
    UUID validEmployeeId;
    VoteRepository voteRepository = Mockito.mock(VoteRepository.class);
    IssueRepository issueRepository = Mockito.mock(IssueRepository.class);
    UserRepository userRepository = Mockito.mock(UserRepository.class);
    VoteService voteService = new VoteService(voteRepository, issueRepository, userRepository);


    @BeforeEach
    public void Setup() {
        validIssueId = UUID.randomUUID();
        validEmployeeId = UUID.randomUUID();
    }


}

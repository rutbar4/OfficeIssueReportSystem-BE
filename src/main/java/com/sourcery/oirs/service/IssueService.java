package com.sourcery.oirs.service;

import com.sourcery.oirs.model.Issue;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class IssueService {

    public List<Issue> getAllIssue() {
        return Stream.of(
                new Issue(1L,
                        "Example Issue 1",
                        "There is not enough parking slots in Kaunas office.",
                        "Open",
                        4L,
                        2L,
                        LocalDate.of(2023, 10, 8)),
                new Issue(2L,
                        "Example Issue 2",
                        "Equipment failures occur during presentation or client meeting.",
                        "Open"
                        , 108L,
                        5L,
                        LocalDate.of(2023, 10, 8)),
                new Issue(3L,
                        "Example Issue 3",
                        "Frequent connectivity issues and slow internet connection hinder productivity.",
                        "Open",
                        9L,
                        1L,
                        LocalDate.of(2023, 10, 8)),
                new Issue(4L,
                        "Example Issue 4",
                        "Printer in the third floor hallway cannot be found via bluetooth/wi-fi," +
                                " therefore we have to use devices in another floors.",
                        "Close",
                        15L,
                        3L,
                        LocalDate.of(2023, 10, 8))
        ).collect(Collectors.toList());
    }
}

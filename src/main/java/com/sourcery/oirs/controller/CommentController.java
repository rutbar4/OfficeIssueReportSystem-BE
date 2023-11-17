package com.sourcery.oirs.controller;

import com.sourcery.oirs.model.Comment;
import com.sourcery.oirs.model.CreateCommentForm;
import com.sourcery.oirs.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @GetMapping("/issue-id/{issueId}")
    public List<Comment> getAllCommentByIssueId(@PathVariable UUID issueId) {
        return commentService.getAllCommentsByIssueId(issueId);
    }

    @PostMapping
    public Comment saveComment(@RequestBody CreateCommentForm createCommentForm) {
        return commentService.saveComment(createCommentForm);
    }

    @PatchMapping("/{id}/issue/{issueId}/vote")
    public Comment updateCommentVotes(@PathVariable UUID id, @PathVariable UUID issueId) {
        return commentService.updateCommentVotes(id, issueId);
    }
}

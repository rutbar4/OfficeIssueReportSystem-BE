package com.sourcery.oirs.service;

import com.sourcery.oirs.database.entity.CommentEntity;
import com.sourcery.oirs.database.repository.CommentRepository;
import com.sourcery.oirs.exceptions.CommentNotFoundException;
import com.sourcery.oirs.mapper.CommentMapper;
import com.sourcery.oirs.model.Comment;
import com.sourcery.oirs.model.CreateCommentForm;
import com.sourcery.oirs.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {
    private final CommentRepository commentRepository;


    public List<Comment> getAllCommentsByIssueId(UUID issueId) {
        UUID customUserId = getCustomUserId();
        List<UUID> commentsIds = commentRepository.getAllCommentsIdsByEmployeeId(issueId, customUserId);
        return commentRepository.getAllCommentsByIssueId(issueId)
                .stream()
                .map(CommentMapper::toComment)
                .peek(comment -> comment.setIsUpVoted(commentsIds.contains(comment.getId())))
                .collect(Collectors.toList());
    }

    public Comment saveComment(CreateCommentForm createCommentForm) {
        CommentEntity commentEntity = CommentMapper.toCommentEntity(createCommentForm);
        UUID commentId = commentEntity.getId();
        commentRepository.saveComment(commentEntity);
        CommentEntity savedComment = findCommentById(commentId);
        return CommentMapper.toComment(savedComment);
    }

    public Comment updateCommentVotes(UUID id, UUID issueId, Integer votes) {
        CommentEntity commentEntity = findCommentById(id);
        boolean commentIsUpVoted = isVoted(id, issueId);
        commentRepository.updateCommentVotes(id, votes);
        CommentEntity updatedCommentEntity = findCommentById(id);
        Comment updatedComment = CommentMapper.toComment(updatedCommentEntity);
        updatedComment.setIsUpVoted(commentIsUpVoted);
        return updatedComment;
    }

    public Comment getCommentById(UUID id) {
        return CommentMapper.toComment(findCommentById(id));
    }

    private CommentEntity findCommentById(UUID id) {
        return commentRepository.getCommentById(id)
                .orElseThrow(() -> new CommentNotFoundException("Such comment doesn't exist. No found by id " + id));
    }

    private boolean isVoted(UUID commentId, UUID IssueId) {
        UUID customUserId = getCustomUserId();
        if (commentRepository.checkIfIsVotedForComment(commentId, getCustomUserId()) != null) {
            commentRepository.deleteCommentUpvote(commentId, customUserId);
            return false;
        }
        commentRepository.saveCommentUpvote(commentId, customUserId, IssueId);
        return true;
    }

    private UUID getCustomUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        return customUserDetails.getId();
    }
}

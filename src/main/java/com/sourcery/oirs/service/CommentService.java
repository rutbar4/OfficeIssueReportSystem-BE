package com.sourcery.oirs.service;

import com.sourcery.oirs.database.entity.CommentEntity;
import com.sourcery.oirs.database.repository.CommentRepository;
import com.sourcery.oirs.exceptions.CommentNotFoundException;
import com.sourcery.oirs.mapper.CommentMapper;
import com.sourcery.oirs.model.Comment;
import com.sourcery.oirs.model.CreateCommentForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    public List<Comment> getAllCommentsByIssueId(UUID issueId) {
        return commentRepository.getAllCommentsByIssueId(issueId)
                .stream().map(CommentMapper::toComment).toList();
    }

    public Comment saveComment(CreateCommentForm createCommentForm) {
        CommentEntity commentEntity = CommentMapper.toCommentEntity(createCommentForm);
        UUID commentId = commentEntity.getId();
        commentRepository.saveComment(commentEntity);
        CommentEntity savedComment = findCommentById(commentId);
        return CommentMapper.toComment(savedComment);
    }

    public Comment updateCommentVotes(UUID id, Integer votes) {
        CommentEntity commentEntity = findCommentById(id);
        commentRepository.updateCommentVotes(id, votes);
        CommentEntity updatedCommentEntity = findCommentById(id);
        return CommentMapper.toComment(updatedCommentEntity);
    }

    public Comment getCommentById(UUID id) {
        return CommentMapper.toComment(findCommentById(id));
    }

    private CommentEntity findCommentById(UUID id) {
        return commentRepository.getCommentById(id)
                .orElseThrow(() -> new CommentNotFoundException("Such comment doesn't exist. No found by id " + id));
    }
}

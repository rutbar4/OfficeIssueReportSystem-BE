package com.sourcery.oirs.mapper;

import com.sourcery.oirs.database.entity.CommentEntity;
import com.sourcery.oirs.model.Comment;
import com.sourcery.oirs.model.CreateCommentForm;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommentMapper {

    public static Comment toComment(CommentEntity commentEntity) {
        return Comment.builder()
                .id(commentEntity.getId())
                .text(commentEntity.getText())
                .time(commentEntity.getTime())
                .votes(commentEntity.getLikes())
                .parentId(commentEntity.getParentId())
                .issueId(commentEntity.getIssueId())
                .employee(UserMapper.toUser(commentEntity.getUserEntity()))
                .isUpVoted(false)
                .build();
    }

    public static CommentEntity toCommentEntity(CreateCommentForm commentForm) {
        return CommentEntity.builder()
                .id(UUID.randomUUID())
                .text(commentForm.getText())
                .time(commentForm.getTime())
                .likes(commentForm.getVotes())
                .parentId(commentForm.getParentId())
                .issueId(commentForm.getIssueId())
                .employeeId(commentForm.getEmployeeId())
                .build();
    }
}

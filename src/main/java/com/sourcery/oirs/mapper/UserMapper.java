package com.sourcery.oirs.mapper;

import com.sourcery.oirs.database.entity.UserEntity;
import com.sourcery.oirs.model.UserToComment;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserMapper {

    public static UserToComment toUser(UserEntity userEntity) {
        return UserToComment.builder()
                .id(userEntity.getId())
                .fullName(userEntity.getFullName())
                .avatar(userEntity.getAvatar())
                .build();
    }
}

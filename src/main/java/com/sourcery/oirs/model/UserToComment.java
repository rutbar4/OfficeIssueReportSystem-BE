package com.sourcery.oirs.model;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class UserToComment {
    UUID id;
    String fullName;
    String avatar;
}

package com.sourcery.oirs.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(enumAsRef = true)
public enum Role {
    ADMIN,
    USER
}

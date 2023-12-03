package com.sourcery.oirs.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class RefreshResponse {
    String jwt;
}

package com.backend.entities.request;

import lombok.Builder;
import lombok.Data;
import org.apache.commons.lang3.RandomStringUtils;

@Data
@Builder
public class CreateUserRequest {
    @Builder.Default
    private String name = RandomStringUtils.randomAlphabetic(6);
    @Builder.Default
    private String job = RandomStringUtils.randomAlphabetic(4);
}

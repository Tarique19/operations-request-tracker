package com.starq.operations.tracker.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateRequestDto(

        @NotBlank(message = "Title must not be blank")
        @Size(min=5, max = 255, message = "Title must not exceed 255 characters")
        String title,

        @Size(max = 1000, message = "Description must not exceed 1000 characters")
        String description
) {}


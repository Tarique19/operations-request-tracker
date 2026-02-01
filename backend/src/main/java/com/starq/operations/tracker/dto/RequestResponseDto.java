package com.starq.operations.tracker.dto;

public record RequestResponseDto(
        Long id,
        String title,
        String description,
        String status
) {}


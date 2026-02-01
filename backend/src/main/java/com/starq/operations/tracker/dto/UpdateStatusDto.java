package com.starq.operations.tracker.dto;

import com.starq.operations.tracker.domain.RequestStatus;
import jakarta.validation.constraints.NotNull;

public record UpdateStatusDto(
        @NotNull RequestStatus status
) {}


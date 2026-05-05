package com.salusteam.salus.crisis.dto;

import com.salusteam.salus.crisis.enums.CrisisStatus;
import java.time.LocalDateTime;
import java.util.UUID;

public record CrisisResponseDTO(
        UUID id,
        Integer intensity,
        CrisisStatus status,
        LocalDateTime timestamp,
        LocalDateTime resolvedAt
) {}
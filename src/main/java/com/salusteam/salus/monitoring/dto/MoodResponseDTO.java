package com.salusteam.salus.monitoring.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record MoodResponseDTO(
        UUID id,
        Integer moodLevel,
        List<String> tags,
        LocalDateTime timestamp
) {}
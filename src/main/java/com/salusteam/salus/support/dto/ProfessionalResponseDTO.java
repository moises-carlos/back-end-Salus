package com.salusteam.salus.support.dto;

import java.util.UUID;

public record ProfessionalResponseDTO(
        UUID id,
        String name,
        String specialty,
        String bio,
        String contactInfo
) {}
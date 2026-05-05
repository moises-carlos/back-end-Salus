package com.salusteam.salus.auth.dto;


import java.util.UUID;

public record AuthResponseDTO(
        UUID id,
        String name,
        String role
) {}
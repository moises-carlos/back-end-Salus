package com.salusteam.salus.crisis.controller;



import com.salusteam.salus.crisis.dto.CrisisRequestDTO;
import com.salusteam.salus.crisis.dto.CrisisResponseDTO;
import com.salusteam.salus.crisis.service.CrisisService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/crisis")
@RequiredArgsConstructor
public class CrisisController {

    private final CrisisService crisisService;

    @PostMapping("/activate")
    public ResponseEntity<CrisisResponseDTO> activate(
            @RequestParam UUID userId,
            @RequestBody @Valid CrisisRequestDTO dto) {
        return ResponseEntity.ok(crisisService.activate(userId, dto));
    }

    @PutMapping("/{id}/resolve")
    public ResponseEntity<CrisisResponseDTO> resolve(
            @RequestParam UUID userId,
            @PathVariable UUID id) {
        return ResponseEntity.ok(crisisService.resolve(userId, id));
    }
}
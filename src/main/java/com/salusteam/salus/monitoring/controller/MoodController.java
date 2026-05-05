package com.salusteam.salus.monitoring.controller;


import com.salusteam.salus.monitoring.dto.MoodRequestDTO;
import com.salusteam.salus.monitoring.dto.MoodResponseDTO;
import com.salusteam.salus.monitoring.service.MoodService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/monitoring")
@RequiredArgsConstructor
public class MoodController {

    private final MoodService moodService;

    @PostMapping("/check-in")
    public ResponseEntity<MoodResponseDTO> checkIn(
            @RequestParam UUID userId,
            @RequestBody @Valid MoodRequestDTO dto) {
        return ResponseEntity.ok(moodService.checkIn(userId, dto));
    }

    @GetMapping("/history")
    public ResponseEntity<List<MoodResponseDTO>> getHistory(@RequestParam UUID userId) {
        return ResponseEntity.ok(moodService.getHistory(userId));
    }

    @GetMapping("/patterns")
    public ResponseEntity<Double> getPatterns(@RequestParam UUID userId) {
        return ResponseEntity.ok(moodService.getAverageMood(userId));
    }
}
package com.salusteam.salus.support.controller;


import com.salusteam.salus.support.dto.ProfessionalResponseDTO;
import com.salusteam.salus.support.service.ProfessionalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/support")
@RequiredArgsConstructor
public class ProfessionalController {

    private final ProfessionalService professionalService;

    @GetMapping("/professionals")
    public ResponseEntity<List<ProfessionalResponseDTO>> listAll(
            @RequestParam(required = false) String specialty) {
        if (specialty != null) {
            return ResponseEntity.ok(professionalService.listBySpecialty(specialty));
        }
        return ResponseEntity.ok(professionalService.listAll());
    }
}
package com.salusteam.salus.support.service;


import com.salusteam.salus.support.dto.ProfessionalResponseDTO;
import com.salusteam.salus.support.repository.ProfessionalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfessionalService {

    private final ProfessionalRepository professionalRepository;

    public List<ProfessionalResponseDTO> listAll() {
        return professionalRepository.findAll()
                .stream()
                .map(p -> new ProfessionalResponseDTO(
                        p.getId(),
                        p.getUser().getName(),
                        p.getSpecialty(),
                        p.getBio(),
                        p.getContactInfo()
                ))
                .toList();
    }

    public List<ProfessionalResponseDTO> listBySpecialty(String specialty) {
        return professionalRepository.findBySpecialty(specialty)
                .stream()
                .map(p -> new ProfessionalResponseDTO(
                        p.getId(),
                        p.getUser().getName(),
                        p.getSpecialty(),
                        p.getBio(),
                        p.getContactInfo()
                ))
                .toList();
    }
}

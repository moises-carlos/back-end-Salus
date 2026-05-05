package com.salusteam.salus.crisis.service;

import com.salusteam.salus.auth.model.User;
import com.salusteam.salus.auth.repository.UserRepository;
import com.salusteam.salus.crisis.dto.CrisisRequestDTO;
import com.salusteam.salus.crisis.dto.CrisisResponseDTO;
import com.salusteam.salus.crisis.enums.CrisisStatus;
import com.salusteam.salus.crisis.model.CrisisAlert;
import com.salusteam.salus.crisis.repository.CrisisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CrisisService {

    private final CrisisRepository crisisRepository;
    private final UserRepository userRepository;

    public CrisisResponseDTO activate(UUID userId, CrisisRequestDTO dto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (crisisRepository.existsByUserIdAndStatus(userId, CrisisStatus.ACTIVE)) {
            throw new RuntimeException("Já existe uma crise ativa");
        }

        CrisisAlert alert = CrisisAlert.builder()
                .user(user)
                .intensity(dto.getIntensity())
                .build();

        crisisRepository.save(alert);

        return toDTO(alert);
    }

    public CrisisResponseDTO resolve(UUID userId, UUID crisisId) {
        CrisisAlert alert = crisisRepository.findByIdAndUserId(crisisId, userId)
                .orElseThrow(() -> new RuntimeException("Crise não encontrada"));

        alert.setStatus(CrisisStatus.RESOLVED);
        alert.setResolvedAt(LocalDateTime.now());

        crisisRepository.save(alert);

        return toDTO(alert);
    }

    public List<CrisisResponseDTO> getHistory(UUID userId) {
        return crisisRepository.findByUserIdOrderByTimestampDesc(userId)
                .stream()
                .map(this::toDTO)
                .toList();
    }

    private CrisisResponseDTO toDTO(CrisisAlert alert) {
        return new CrisisResponseDTO(
                alert.getId(),
                alert.getIntensity(),
                alert.getStatus(),
                alert.getTimestamp(),
                alert.getResolvedAt()
        );
    }
}
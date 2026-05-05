package com.salusteam.salus.monitoring.service;

import com.salusteam.salus.auth.model.User;
import com.salusteam.salus.auth.repository.UserRepository;
import com.salusteam.salus.monitoring.dto.MoodRequestDTO;
import com.salusteam.salus.monitoring.dto.MoodResponseDTO;
import com.salusteam.salus.monitoring.model.MoodEntry;
import com.salusteam.salus.monitoring.repository.MoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MoodService {

    private final MoodRepository moodRepository;
    private final UserRepository userRepository;

    public MoodResponseDTO checkIn(UUID userId, MoodRequestDTO dto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        MoodEntry entry = MoodEntry.builder()
                .user(user)
                .moodLevel(dto.getMoodLevel())
                .tags(dto.getTags())
                .notes(dto.getNotes())
                .build();

        moodRepository.save(entry);

        return new MoodResponseDTO(entry.getId(), entry.getMoodLevel(), entry.getTags(), entry.getTimestamp());
    }

    public List<MoodResponseDTO> getHistory(UUID userId) {
        return moodRepository.findByUserIdOrderByTimestampDesc(userId)
                .stream()
                .map(e -> new MoodResponseDTO(e.getId(), e.getMoodLevel(), e.getTags(), e.getTimestamp()))
                .toList();
    }

    public Double getAverageMood(UUID userId) {
        return moodRepository.findAverageMoodLevelByUserId(userId);
    }
}
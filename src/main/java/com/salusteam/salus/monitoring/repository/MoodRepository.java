package com.salusteam.salus.monitoring.repository;

import com.salusteam.salus.monitoring.model.MoodEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface MoodRepository extends JpaRepository<MoodEntry, UUID> {

    List<MoodEntry> findByUserIdOrderByTimestampDesc(UUID userId);

    List<MoodEntry> findByUserIdAndTimestampBetweenOrderByTimestampAsc(
            UUID userId,
            LocalDateTime start,
            LocalDateTime end
    );

    @Query("SELECT AVG(m.moodLevel) FROM MoodEntry m WHERE m.user.id = :userId")
    Double findAverageMoodLevelByUserId(UUID userId);
}
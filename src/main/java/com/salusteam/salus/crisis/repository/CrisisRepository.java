package com.salusteam.salus.crisis.repository;

import com.salusteam.salus.crisis.enums.CrisisStatus;
import com.salusteam.salus.crisis.model.CrisisAlert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CrisisRepository extends JpaRepository<CrisisAlert, UUID> {

    List<CrisisAlert> findByUserIdOrderByTimestampDesc(UUID userId);

    Optional<CrisisAlert> findByIdAndUserId(UUID id, UUID userId);

    boolean existsByUserIdAndStatus(UUID userId, CrisisStatus status);
}
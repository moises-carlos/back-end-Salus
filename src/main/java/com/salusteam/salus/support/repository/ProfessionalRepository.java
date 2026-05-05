package com.salusteam.salus.support.repository;

import com.salusteam.salus.support.model.Professional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProfessionalRepository extends JpaRepository<Professional, UUID> {

    List<Professional> findBySpecialty(String specialty);

    Optional<Professional> findByUserId(UUID userId);

    boolean existsByCrmCrp(String crmCrp);
}
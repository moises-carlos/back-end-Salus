package com.salusteam.salus.crisis.model;


import com.salusteam.salus.auth.model.User;
import com.salusteam.salus.crisis.enums.CrisisStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "crisis_alerts")
public class CrisisAlert {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private Integer intensity;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CrisisStatus status;

    @Column(name = "resolved_at")
    private LocalDateTime resolvedAt;

    @Column(name = "timestamp", nullable = false, updatable = false)
    private LocalDateTime timestamp;



    @PrePersist
    private void prePersist() {
        this.timestamp = LocalDateTime.now();
        this.status = CrisisStatus.ACTIVE;
    }

    // Getters e Setters
}
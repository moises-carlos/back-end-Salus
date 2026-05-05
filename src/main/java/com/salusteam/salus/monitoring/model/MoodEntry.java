package com.salusteam.salus.monitoring.model;

import com.salusteam.salus.auth.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "mood_entries")
public class MoodEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "mood_level", nullable = false)
    private Integer moodLevel;

    @ElementCollection
    @CollectionTable(name = "mood_tags", joinColumns = @JoinColumn(name = "mood_entry_id"))
    @Column(name = "tag")
    private List<String> tags;

    @Column(name = "notes", columnDefinition = "TEXT")
    private String notes;
    @Column(name = "timestamp", nullable = false, updatable = false)
    private LocalDateTime timestamp;

    @PrePersist
    private void prePersist() {
        this.timestamp = LocalDateTime.now();
    }

}

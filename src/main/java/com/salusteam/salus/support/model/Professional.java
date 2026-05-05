package com.salusteam.salus.support.model;

import com.salusteam.salus.auth.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "professionals")
public class Professional {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Column(name = "crm_crp", nullable = false, unique = true)
    private String crmCrp;

    @Column(nullable = false)
    private String specialty;

    @Column(columnDefinition = "TEXT")
    private String bio;

    @Column(name = "contact_info", nullable = false)
    private String contactInfo;
}
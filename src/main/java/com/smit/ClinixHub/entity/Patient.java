package com.smit.ClinixHub.entity;

import com.smit.ClinixHub.entity.types.BloodGroupType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
        name = "patients",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "unique_patient_name_birthdate",
                        columnNames = {"name", "birth_date"}
                )
        },
        indexes = {
                @Index(
                        name = "idx_patient_birth_date",
                        columnList = "birth_date"
                )
        }
)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 40)
    private String name;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String gender;

    @Enumerated(EnumType.STRING)
    @Column(name = "blood_group")
    private BloodGroupType bloodGroup;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name = "insurance_id")
    private Insurance insurance;

    @OneToMany(
            mappedBy = "patient",
            cascade = CascadeType.REMOVE,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private List<Appointment> appointments = new ArrayList<>();
}
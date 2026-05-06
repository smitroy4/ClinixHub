package com.smit.ClinixHub.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Insurance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    private String policyNumber;

    @Column(nullable = false, length = 100)
    private String provider;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDate createdAt;

    @OneToOne(mappedBy = "insurance")
    private Patient patient;

}

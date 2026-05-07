package com.smit.ClinixHub.repository;

import com.smit.ClinixHub.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}

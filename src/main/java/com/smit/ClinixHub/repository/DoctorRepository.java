package com.smit.ClinixHub.repository;

import com.smit.ClinixHub.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}

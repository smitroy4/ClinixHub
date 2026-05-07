package com.smit.ClinixHub.repository;

import com.smit.ClinixHub.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}

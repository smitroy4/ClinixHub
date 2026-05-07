package com.smit.ClinixHub.controller;

import com.smit.ClinixHub.service.AppointmentService;
import com.smit.ClinixHub.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;
    private AppointmentService appointmentService;

}

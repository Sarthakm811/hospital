package com.college.hospital.controller;

import com.college.hospital.entity.Patient;
import com.college.hospital.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patient")
public class PatientController {

    @Autowired
    private PatientService patientService;

    // Add Patient
    @PreAuthorize("hasRole('PATIENT')")
    @PostMapping("/add")
    public Patient createPatient(@RequestBody Patient patient) {

        return patientService.createPatient(patient);
    }

    // Get Patient By Id
    @PreAuthorize("hasAnyRole('PATIENT','ADMIN')")
    @GetMapping("/{id}")
    public Patient getPatientById(@PathVariable Long id) {

        return patientService.getPatientById(id);
    }

    // Get All Patients
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all")
    public List<Patient> getAllPatients() {

        return patientService.getAllPatients();
    }

    // Update Patient
    @PreAuthorize("hasRole('PATIENT')")
    @PutMapping("/update/{id}")
    public Patient updatePatient(@PathVariable Long id,
                                 @RequestBody Patient patient) {

        return patientService.updatePatient(id, patient);
    }

    // Delete Patient
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public String deletePatient(@PathVariable Long id) {

        return patientService.deletePatient(id);
    }
}
package com.college.hospital.service;

import com.college.hospital.entity.Patient;
import com.college.hospital.entity.User;
import com.college.hospital.repository.PatientRepository;
import com.college.hospital.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private UserRepository userRepository;
    // Add Patient
    public Patient createPatient(Patient patient) {

        Long userId = patient.getUser().getId();

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        patient.setUser(user);

        return patientRepository.save(patient);
    }

    // Get Patient By Id
    public Patient getPatientById(Long id) {

        return patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found"));
    }

    // Get All Patients
    public List<Patient> getAllPatients() {

        return patientRepository.findAll();
    }

    // Update Patient
    public Patient updatePatient(Long id, Patient updatedPatient) {

        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        patient.setAge(updatedPatient.getAge());
        patient.setGender(updatedPatient.getGender());
        patient.setBloodGroup(updatedPatient.getBloodGroup());

        return patientRepository.save(patient);
    }

    // Delete Patient
    public String deletePatient(Long id) {

        patientRepository.deleteById(id);

        return "Patient deleted successfully";
    }
}
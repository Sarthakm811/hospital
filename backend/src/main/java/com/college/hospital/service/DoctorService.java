package com.college.hospital.service;

import com.college.hospital.entity.Appointment;
import com.college.hospital.entity.Doctor;
import com.college.hospital.repository.AppointmentRepository;
import com.college.hospital.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class DoctorService {
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private AppointmentRepository appointmentRepository;
    public Doctor addDoctor(Doctor doctor)
    {
        return doctorRepository.save(doctor);
    }

    public List<Doctor> getAllDoctors() {

        return doctorRepository.findAll();
    }

    public Doctor updateAvailability(
            Long userId,
            String availableTime) {

        Doctor doctor = doctorRepository
                .findByUserId(userId)
                .orElseThrow(() ->
                        new RuntimeException("Doctor not found"));

        doctor.setAvailableTime(availableTime);

        return doctorRepository.save(doctor);
    }

    public Doctor getDoctorById(Long id) {
        return doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
    }
    public Doctor getDoctorByUserId(Long userId){

        return doctorRepository
                .findByUserId(userId)
                .orElseThrow(() ->
                        new RuntimeException("Doctor not found"));
    }

    public List<Doctor> getDoctorsBySpecialization(String specialization) {
        return doctorRepository.findBySpecialization(specialization);
    }

    public String deleteDoctor(Long id) {
        doctorRepository.deleteById(id);
        return "Doctor deleted successfully";
    }
    public String completeAppointment(Long id) {

        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        appointment.setStatus("COMPLETED");

        appointmentRepository.save(appointment);

        return "Appointment marked as completed";
    }
}

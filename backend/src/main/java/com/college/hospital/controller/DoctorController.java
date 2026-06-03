package com.college.hospital.controller;

import com.college.hospital.entity.Doctor;
import com.college.hospital.repository.AppointmentRepository;
import com.college.hospital.service.AppointmentService;
import com.college.hospital.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private AppointmentService appointmentService;
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add")
    public Doctor addDoctor(@RequestBody Doctor doctor)
    {
        return doctorService.addDoctor(doctor);
    }
    @PreAuthorize("hasAnyRole('ADMIN','PATIENT','DOCTOR')")
    @GetMapping
    public List<Doctor> getAllDoctors() {

        return doctorService.getAllDoctors();
    }
    @PreAuthorize("hasRole('DOCTOR')")
    @PutMapping("/update/{id}")
    public Doctor updateAvailability(@PathVariable Long id,
                                     @RequestParam String availableTime) {
        return doctorService.updateAvailability(id, availableTime);
    }
    @GetMapping("/id/{id}")
    public Doctor getDoctorById(@PathVariable Long id) {
        return doctorService.getDoctorById(id);
    }

    @PreAuthorize("hasRole('DOCTOR')")
    @GetMapping("/user/{userId}")
    public Doctor getDoctorByUserId(
            @PathVariable Long userId){
        return doctorService.getDoctorByUserId(userId);

    }

    @GetMapping("/specialization/{type}")
    public List<Doctor> getDoctorsBySpecialization(@PathVariable String type) {
        return doctorService.getDoctorsBySpecialization(type);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteDoctor(@PathVariable Long id) {
        return doctorService.deleteDoctor(id);
    }
    @PutMapping("/complete/{id}")
    public String completeAppointment(@PathVariable Long id) {

        return appointmentService.completeAppointment(id);
    }
}
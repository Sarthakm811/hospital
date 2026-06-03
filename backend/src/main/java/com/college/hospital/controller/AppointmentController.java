package com.college.hospital.controller;

import com.college.hospital.entity.Appointment;
import com.college.hospital.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;

    @PreAuthorize("hasRole('PATIENT')")
    @PostMapping("/book")
    public Appointment bookAppointment(@RequestBody Appointment request) {

        return appointmentService.bookAppointment(request);
    }


    @GetMapping("/patient/{patientId}")
    public List<Appointment> getAppointmentsByPatient(
            @PathVariable Long patientId){

        return appointmentService.getAppointmentsByPatient(patientId);
    }
    @GetMapping("/doctor/{doctorId}")
    public List<Appointment> getAppointmentsByDoctor(@PathVariable Long doctorId) {

        return appointmentService.getAppointmentsByDoctor(doctorId);
    }

    @DeleteMapping("/cancel/{id}")
    public String cancelAppointment(@PathVariable Long id) {

        return appointmentService.cancelAppointment(id);
    }
    @GetMapping
    public List<Appointment> getAllAppointments() {
        return appointmentService.getAllAppointments();
    }
    @GetMapping("/history/{patientId}")
    public List<Appointment> getAppointmentHistory(
            @PathVariable Long patientId) {

        return appointmentService.getAppointmentHistory(patientId);
    }
}
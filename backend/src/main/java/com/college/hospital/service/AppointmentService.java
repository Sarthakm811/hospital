package com.college.hospital.service;

import com.college.hospital.entity.Appointment;
import com.college.hospital.entity.Doctor;
import com.college.hospital.entity.Patient;
import com.college.hospital.repository.AppointmentRepository;
import com.college.hospital.repository.DoctorRepository;
import com.college.hospital.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private DoctorRepository doctorRepository;

    public Appointment bookAppointment(Appointment appointment) {

        Long patientId = appointment.getPatient().getId();
        Long doctorId = appointment.getDoctor().getId();

        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        appointment.setPatient(patient);
        appointment.setDoctor(doctor);

        String token = generateToken(appointment.getAppointmentDate());
        appointment.setTokenNumber(token);

        appointment.setStatus("BOOKED");

        return appointmentRepository.save(appointment);
    }


    public List<Appointment> getAppointmentsByDoctor(Long doctorId) {

        return appointmentRepository.findByDoctorId(doctorId);
    }
    public List<Appointment> getAppointmentsByPatient(Long patientId) {

        return appointmentRepository.findByPatientId(patientId);
    }

    public String cancelAppointment(Long id) {

        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        appointment.setStatus("CANCELLED");

        appointmentRepository.save(appointment);

        return "Appointment cancelled successfully";
    }

    public List<Appointment> getAllAppointments() {
       return appointmentRepository.findAll();
    }
    public String generateToken(String appointmentDate) {

        long todayCount =
                appointmentRepository.countByAppointmentDate(appointmentDate);

        return "TOKEN-" + (todayCount + 1);
    }
    public List<Appointment> getAppointmentHistory(Long patientId) {

        return appointmentRepository
                .findByPatientIdAndStatus(patientId, "COMPLETED");
    }
    public String completeAppointment(Long id) {

        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        appointment.setStatus("COMPLETED");

        appointmentRepository.save(appointment);

        return "Appointment marked as completed";
    }
}
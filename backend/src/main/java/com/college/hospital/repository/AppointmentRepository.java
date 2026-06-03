package com.college.hospital.repository;

import com.college.hospital.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findByPatientId(Long patientId);

    List<Appointment> findByDoctorId(Long doctorId);
    long countByAppointmentDate(String appointmentDate);
    List<Appointment> findByPatientIdAndStatus(
            Long patientId,
            String status
    );

}
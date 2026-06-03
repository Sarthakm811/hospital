package com.college.hospital.repository;

import com.college.hospital.entity.Patient;
import com.college.hospital.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    Patient findByUser(User user);
}
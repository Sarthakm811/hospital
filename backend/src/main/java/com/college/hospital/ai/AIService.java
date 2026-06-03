package com.college.hospital.ai;

import com.college.hospital.entity.Doctor;
import com.college.hospital.repository.DoctorRepository;
import org.springframework.ai.ollama.OllamaChatClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AIService {

    private final OllamaChatClient chatClient;
    private final DoctorRepository doctorRepository;


    public AIService(OllamaChatClient chatClient, DoctorRepository doctorRepository) {
        this.chatClient = chatClient;
        this.doctorRepository = doctorRepository;
    }

    public String suggestDoctor(String symptoms) {

        String prompt = "You are a medical assistant. Based on symptoms: " + symptoms +
                ", suggest ONLY one doctor specialization from this list: " +
                "[Cardiologist, Dermatologist, Neurologist, Orthopedic, General Physician]. " +
                "Return ONLY the specialization name.";

        return chatClient.call(prompt).trim();
    }


    public List<Doctor> getDoctorsBySymptoms(String symptoms) {

        String specialization = suggestDoctor(symptoms);

        return doctorRepository.findBySpecializationIgnoreCase(specialization);
    }
}
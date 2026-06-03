package com.college.hospital.ai;

import com.college.hospital.entity.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ai")
public class AIController {

    @Autowired
    private AIService aiService;

    @GetMapping("/suggest")
    public String suggestDoctor(@RequestParam String symptoms) {
        return aiService.suggestDoctor(symptoms);
    }

    @GetMapping("/doctors")
    public List<Doctor> suggestDoctors(@RequestParam String symptoms) {
        return aiService.getDoctorsBySymptoms(symptoms);
    }
}
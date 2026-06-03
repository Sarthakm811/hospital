package com.college.hospital.controller;

import com.college.hospital.dto.LoginRequest;
import com.college.hospital.entity.User;
import com.college.hospital.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User register(@RequestBody User user) {

        return userService.registerUser(user);
    }
    @PostMapping("/login")
    public Map<String, Object> login(
            @RequestBody LoginRequest request) {

        return userService.login(request);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/approve/{id}")
    public User approveDoctor(@PathVariable Long id) {
        return userService.approveDoctor(id);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<User> getAllUsers(){

        return userService.getAllUsers();
    }
    @PostMapping("/send-otp")
    public String sendOtp(
            @RequestBody Map<String,String> request){

        return userService.sendOtp(
                request.get("email")
        );
    }
    @PostMapping("/reset-password")
    public String resetPassword(
            @RequestBody Map<String,String> request){

        return userService.resetPassword(

                request.get("email"),

                request.get("otp"),

                request.get("newPassword")
        );
    }
}

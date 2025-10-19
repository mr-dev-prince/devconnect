package com.devconnect.backend.controller;

import com.devconnect.backend.payload.ApiResponse;
import com.devconnect.backend.repository.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootController {
    // dependency injection
    private final UserRepository userRepository;

    // constructor injection [ best practice ]
    public RootController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public ApiResponse<String> root(){
        return new ApiResponse<>(true, "DevConnect Backend is running at 8080", "v1.0.0", 200);
    }

    // Health Check
    @GetMapping("/health")
    public ApiResponse<String> health(){
        boolean dbUp = true;
        try{
            userRepository.count();
        } catch (Exception e){
            dbUp = false;
        }

        return new ApiResponse<>(dbUp, dbUp ? "All Systems OK" : "Database not reachable", null, dbUp ? 200 : 503);
    }
}

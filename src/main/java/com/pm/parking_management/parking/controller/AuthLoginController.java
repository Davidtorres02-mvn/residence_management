package com.pm.parking_management.parking.controller;

import com.pm.parking_management.parking.model.request.LoginRequest;
import com.pm.parking_management.parking.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j

public class AuthLoginController {

    @Autowired
    private AuthService authService;

    @CrossOrigin(origins = {})
    @PostMapping("/api/residence/v1/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request){
        return authService.authenticate(request);
    }

}

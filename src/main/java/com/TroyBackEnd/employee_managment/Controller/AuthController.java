package com.TroyBackEnd.employee_managment.Controller;

import com.TroyBackEnd.employee_managment.Service.AuthService;
import com.TroyBackEnd.employee_managment.Shared.GlobalResponse;
import com.TroyBackEnd.employee_managment.dtos.LoginRequest;
import com.TroyBackEnd.employee_managment.dtos.SginupRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/singup")
    public ResponseEntity<GlobalResponse<String>> sginup(@RequestBody SginupRequest sginupRequest) {
        authService.signup(sginupRequest);
        System.out.println(sginupRequest.employeeId());
        return new ResponseEntity<>(new GlobalResponse<>("Signed Up"), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<GlobalResponse<String>> login(@RequestBody LoginRequest loginRequest) {
        String token = authService.login(loginRequest);

        return new ResponseEntity<>(new GlobalResponse<>(token), HttpStatus.OK);
    }
}

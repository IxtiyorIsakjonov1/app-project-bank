package com.example.appprojectbank.Controller;

import com.example.appprojectbank.Payload.ReqSignUp;
import com.example.appprojectbank.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    @PostMapping("register")
    public HttpEntity<?> register(@RequestBody ReqSignUp reqSignUp){
        return ResponseEntity.ok().body(authService.register(reqSignUp));
    }
    @PostMapping("login")
    public HttpEntity<?> login(@RequestBody ReqSignUp reqSignUp){
        return ResponseEntity.ok().body(authService.login(reqSignUp.getPhoneNumber(),reqSignUp.getPassword()));
    }
}


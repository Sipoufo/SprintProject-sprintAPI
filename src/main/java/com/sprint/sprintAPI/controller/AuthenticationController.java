package com.sprint.sprintAPI.controller;

import com.sprint.sprintAPI.entity.auth.AuthenticationResponse;
import com.sprint.sprintAPI.entity.auth.LoginRequest;
import com.sprint.sprintAPI.entity.auth.RegisterRequest;
import com.sprint.sprintAPI.service.auth.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.POST})
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

//    @PostMapping("/admin/register")
//    public ResponseEntity<AuthenticationResponse> register(
//            @RequestBody RegisterRequest request
//    ){
//        return ResponseEntity.ok(authenticationService.register(request));
//    }
//
//    @PostMapping("/user/register")
//    public ResponseEntity<AuthenticationResponse> authMobile(
//            @RequestBody RegisterRequest request
//    ){
//        return ResponseEntity.ok(authenticationService.authMobile(request));
//    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody LoginRequest request
    ){
        System.out.println(request.email);
        return ResponseEntity.ok(authenticationService.login(request));
    }
}

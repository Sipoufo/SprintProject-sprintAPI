package com.sprint.sprintAPI.service.auth;

import com.sprint.sprintAPI.entity.auth.AuthenticationResponse;
import com.sprint.sprintAPI.entity.auth.LoginRequest;
import com.sprint.sprintAPI.entity.auth.RegisterRequest;


public interface AuthenticationService {
    public AuthenticationResponse register(RegisterRequest request);
    public AuthenticationResponse login(LoginRequest request);
    public AuthenticationResponse authMobile(RegisterRequest request);
}

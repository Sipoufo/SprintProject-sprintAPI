package com.sprint.sprintAPI.service.auth;

import com.sprint.sprintAPI.entity.Role;
import com.sprint.sprintAPI.entity.Users;
import com.sprint.sprintAPI.entity.auth.AuthenticationResponse;
import com.sprint.sprintAPI.entity.auth.LoginRequest;
import com.sprint.sprintAPI.entity.auth.RegisterRequest;
import com.sprint.sprintAPI.error.UserException;
import com.sprint.sprintAPI.repository.UsersRepository;
import com.sprint.sprintAPI.service.JWTService;
import com.sprint.sprintAPI.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticateServiceImpl implements AuthenticationService {
    @Autowired
    private UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = Users
                .builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ADMIN)
                .build();
        usersRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse
                .builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authMobile(RegisterRequest request) {
        var user = usersRepository
                .findByEmail(request.email);
        if (!user.isPresent()) {
            var jwtToken = jwtService.generateToken(user.get());
            return AuthenticationResponse
                    .builder()
                    .token(jwtToken)
                    .build();
        }
        var user1 = Users
                .builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        usersRepository.save(user1);
        var jwtToken = jwtService.generateToken(user1);
        return AuthenticationResponse
                .builder()
                .token(jwtToken)
                .build();
    }

    @Override
    public AuthenticationResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = usersRepository
                .findByEmail(request.email);
        if (!user.isPresent()) {
            throw new UsernameNotFoundException("This User is not register");
        }

        var jwtToken = jwtService.generateToken(user.get());
        return AuthenticationResponse
                .builder()
                .token(jwtToken)
                .build();
    }
}
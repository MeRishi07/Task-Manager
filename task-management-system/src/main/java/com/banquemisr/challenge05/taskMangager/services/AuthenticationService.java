package com.banquemisr.challenge05.taskMangager.services;

import com.banquemisr.challenge05.taskMangager.config.JWTservice;
import com.banquemisr.challenge05.taskMangager.entity.AuthenticationRequest;
import com.banquemisr.challenge05.taskMangager.entity.AuthenticationResponce;
import com.banquemisr.challenge05.taskMangager.entity.User;
import com.banquemisr.challenge05.taskMangager.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JWTservice jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponce register(User request) {
        var user = User
                .builder()
                .name(request.getName())
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();
        userRepo.save(user);
        var jwtToken = jwtService.generateToken(user);

            return AuthenticationResponce.builder()
                    .token(jwtToken).build() ;
    }

    public AuthenticationResponce authenticate(AuthenticationRequest request) {
           authenticationManager.authenticate(
                   new UsernamePasswordAuthenticationToken(
                          request.getUsername(),
                          request.getPassword()
                   )
           );

            var user = userRepo.findByUsername(request.getUsername())
                    .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponce.builder()
                .token(jwtToken).build() ;
    }
}

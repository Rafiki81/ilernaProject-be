package com.rafiki.ilernaprojectbe.controller;

import com.rafiki.ilernaprojectbe.configuration.JwtUtils;
import com.rafiki.ilernaprojectbe.model.User;
import com.rafiki.ilernaprojectbe.model.request.LoginRequest;
import com.rafiki.ilernaprojectbe.model.request.SignUpRequest;
import com.rafiki.ilernaprojectbe.model.response.JwtResponse;
import com.rafiki.ilernaprojectbe.model.response.MessageResponse;
import com.rafiki.ilernaprojectbe.service.UserDetailsImpl;
import com.rafiki.ilernaprojectbe.service.UserDetailsServiceImpl;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 4000)
@RestController
@RequestMapping("/flashes/authorization")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthController {

    final AuthenticationManager authenticationManager;
    final UserDetailsServiceImpl userDetailsService;
    final PasswordEncoder encoder;
    final JwtUtils jwtUtils;

    @PostMapping("/signUp")
    public ResponseEntity<?> registerUser(@RequestBody SignUpRequest signUpRequest) {
        if (userDetailsService.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userDetailsService.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        userDetailsService.save(User.builder()
                .username(signUpRequest.getUsername())
                .email(signUpRequest.getEmail())
                .password(encoder.encode(signUpRequest.getPassword()))
                .build());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new MessageResponse("User registered successfully!"));
    }

    @PostMapping("/signIn")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        return ResponseEntity.status(HttpStatus.OK).body(new JwtResponse(jwt,
                userDetails.getId().toString(),
                userDetails.getUsername(),
                userDetails.getEmail(),null));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logoutUser() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(null);
        return ResponseEntity.ok(new MessageResponse("logout successful"));
    }

}

package com.example.demo.Security.Controller;

import com.example.demo.Security.DTO.LoginRequest;
import com.example.demo.Security.DTO.LoginResponse;
import com.example.demo.Security.DTO.RegistrationRequest;
import com.example.demo.Security.DTO.UserDataResponse;
import com.example.demo.Security.Entity.Role;
import com.example.demo.Security.Entity.User;
import com.example.demo.Security.Repository.RoleRepository;
import com.example.demo.Security.Repository.UserRepository;
import com.example.demo.Security.Service.CustomUserDetailsService;
import com.example.demo.Security.Utils.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api")
@Validated
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private CustomUserDetailsService userService;

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@Valid @RequestBody LoginRequest loginRequest) {
        User user = (User) userService.loadUserByUsername(loginRequest.getEmail());

        if (userService.checkPassword(user, loginRequest.getPassword())) {
            String token = jwtUtil.generateToken(user.getUsername());
            return ResponseEntity.ok(new LoginResponse(token));
        } else {
            return ResponseEntity.status(401).body("Invalid username or password");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@Valid @RequestBody RegistrationRequest registrationRequest) {
        User user = new User();
        user.setFirstName(registrationRequest.getFirstName());
        user.setLastName(registrationRequest.getLastName());
        user.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
        user.setEmail(registrationRequest.getEmail());

        Set<Role> roles = new HashSet<>();
        if (registrationRequest.isAdmin()) {
            Role adminRole = roleRepository.findByName("ROLE_ADMIN");
            if (adminRole != null) {
                roles.add(adminRole);
            }
        } else {
            Role userRole = roleRepository.findByName("ROLE_USER");
            if (userRole != null) {
                roles.add(userRole);
            }
        }
        user.setRoles(roles);

        userRepository.save(user);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/profile")
    public UserDataResponse getUserData(@AuthenticationPrincipal UserDetails userDetails) {
        User user = (User) userDetails;
        UserDataResponse userDataResponse = new UserDataResponse();
        userDataResponse.setId(user.getId());
        userDataResponse.setFirstName(user.getFirstName());
        userDataResponse.setLastName(user.getLastName());
        return userDataResponse;
    }
}

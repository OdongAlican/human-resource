package com.humanresource.hr.authentication;

import com.humanresource.hr.config.JwtService;
import com.humanresource.hr.exception.NotFoundException;
import com.humanresource.hr.role.Role;
import com.humanresource.hr.role.RoleRepository;
import com.humanresource.hr.user.User;
import com.humanresource.hr.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request, Long roleID) {

        Optional<Role> role = roleRepository.findById(roleID);

        if (role.isPresent()) {
            var user = User.builder()
                    .email(request.getEmail())
                    .first_name(request.getFirst_name())
                    .last_name(request.getLast_name())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .address(request.getAddress())
                    .phone(request.getPhone())
                    .role(role.get())
                    .build();
            userRepository.save(user);
            return generateToken(user, role.get());
        }
        return null;
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) throws NotFoundException {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new NotFoundException("User not found"));
        return generateToken(user, user.getRole());
    }

    private AuthenticationResponse generateToken(User user, Role role) {
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .firstname(user.getFirst_name())
                .lastname(user.getLast_name())
                .email(user.getEmail())
                .role(role)
                .build();
    }
}

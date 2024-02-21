package com.humanresource.hr.authentication;

import com.humanresource.hr.role.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthenticationResponse {
    private String token;
    private String firstname;
    private String lastname;
    private String email;
    private Role role;
}

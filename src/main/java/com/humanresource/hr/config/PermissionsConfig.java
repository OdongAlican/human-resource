package com.humanresource.hr.config;

import com.humanresource.hr.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class PermissionsConfig {
    public static User build(User user) {
        List<GrantedAuthority> authorities = user.getRole().getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getName()))
                .collect(Collectors.toList());

        User newUser = new User();
        newUser.setId(user.getId());
        newUser.setFirst_name(user.getFirst_name());
        newUser.setLast_name(user.getLast_name());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword());
        newUser.setPhone(user.getPhone());
        newUser.setAddress(user.getAddress());
        newUser.setRole(user.getRole());
        newUser.setAuthorities(authorities);

        return newUser;
    }

}

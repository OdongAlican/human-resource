package com.humanresource.hr.user;

import com.humanresource.hr.helper.Constants;
import com.humanresource.hr.role.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = Constants.NULL_FIELD_VALIDATOR)
    @Size(max = 20, min = 3, message = Constants.NAME_VALIDATOR)
    private String first_name;

    @NotNull(message = Constants.NULL_FIELD_VALIDATOR)
    @Size(max = 20, min = 3, message = Constants.NAME_VALIDATOR)
    private String last_name;

    @Email(message = Constants.EMAIL_VALIDATOR)
    @NotNull(message = Constants.NULL_FIELD_VALIDATOR)
    @NotBlank(message = Constants.BLANK_FIELD_VALIDATOR)
    private String email;

    @NotNull(message = Constants.NULL_FIELD_VALIDATOR)
    @NotBlank(message = Constants.BLANK_FIELD_VALIDATOR)
    private String password;

    @NotNull(message = Constants.NULL_FIELD_VALIDATOR)
    @Pattern(regexp = Constants.PHONE_REGEX, message = Constants.PHONE_VALIDATOR)
    private String phone;

    @NotBlank(message = Constants.BLANK_FIELD_VALIDATOR)
    @NotNull(message = Constants.NULL_FIELD_VALIDATOR)
    private String address;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "role_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Role role;

    private Collection<? extends GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.getName()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
package com.humanresource.hr.user;

import com.humanresource.hr.role.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 20, min = 3, message = "Must be at least characters and max 20")
    @NotNull(message = "First Name cannot be null")
    @NotBlank
    private String first_name;

    @Size(max = 20, min = 3, message = "Must be at least characters and max 20")
    @NotNull(message = "Last name cannot be null")
    @NotBlank
    private String last_name;

    @Size(max = 20, min = 3, message = "Must be at least characters and max 20")
    @NotNull(message = "Email cannot be null")
    @Email(message = "Must be a valid email")
    private String email;

    @NotNull
    @NotBlank
    private String address;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "role_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Role role;
}
package com.humanresource.hr.user;

import com.humanresource.hr.role.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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

    @Email(message = "Must be a valid email")
    @NotNull(message = "Email cannot be null")
    private String email;

    @Pattern(regexp = "^\\+256[347]\\d{8}$", message = "Invalid Ugandan phone number")
    @NotNull(message = "Phone number must be provided")
    private String phone;

    @NotNull
    @NotBlank(message = "Address cannot be blank")
    private String address;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "role_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Role role;
}
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

    @NotNull(message = "First Name must be provided")
    @Size(max = 20, min = 3, message = "Must be at least 3 and max 20 characters")
    private String first_name;

    @NotNull(message = "Last name cannot be null")
    @Size(max = 20, min = 3, message = "Must be at least 3 and max 20 characters")
    private String last_name;

    @Email(message = "Must be a valid email")
    @NotNull(message = "Email must be provided")
    private String email;

    @NotNull(message = "Phone number must be provided")
    @Pattern(regexp = "^\\+256\\d{9}", message = "Invalid Ugandan phone number")
    private String phone;

    @NotBlank(message = "Address cannot be blank")
    @NotNull(message = "Phone number must be provided")
    private String address;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "role_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Role role;
}
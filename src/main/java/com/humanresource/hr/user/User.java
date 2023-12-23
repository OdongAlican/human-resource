package com.humanresource.hr.user;

import com.humanresource.hr.role.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Length;

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

    @Length(max = 20, min = 3)
    @NotBlank(message = "First name cannot be null")
    private String first_name;

    @Length(max = 20, min = 3)
    @NotBlank(message = "Last name cannot be null")
    private String last_name;

    @NotBlank(message = "Email cannot be null")
    @Length(min = 3, max = 20)
    private String email;

    @NotBlank(message = "Address cannot be null")
    @Length(min = 3, max = 20)
    private String address;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "role_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Role role;
}
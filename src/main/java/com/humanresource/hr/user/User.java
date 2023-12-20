package com.humanresource.hr.user;

import com.humanresource.hr.role.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
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

    @NotNull
    @Length(max = 20)
    private String first_name;

    @NotNull
    @Length(max = 20)
    private String last_name;

    @NotNull
    @Email
    private String email;

    @NotNull
    private String address;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "role_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Role role;
}
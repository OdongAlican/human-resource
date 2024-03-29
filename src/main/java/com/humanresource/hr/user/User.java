package com.humanresource.hr.user;

import com.humanresource.hr.helper.Constants;
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

    @NotNull(message = Constants.NULL_FIELD_VALIDATOR)
    @Size(max = 20, min = 3, message = Constants.NAME_VALIDATOR)
    private String first_name;

    @NotNull(message = Constants.NULL_FIELD_VALIDATOR)
    @Size(max = 20, min = 3, message = Constants.NAME_VALIDATOR)
    private String last_name;

    @Email(message = Constants.EMAIL_VALIDATOR)
    @NotNull(message = Constants.NULL_FIELD_VALIDATOR)
    private String email;

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
}
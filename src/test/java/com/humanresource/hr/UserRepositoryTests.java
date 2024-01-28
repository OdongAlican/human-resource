package com.humanresource.hr;

import com.humanresource.hr.role.Role;
import com.humanresource.hr.role.RoleRepository;
import com.humanresource.hr.user.User;
import com.humanresource.hr.user.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTests {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Test
    public void saveUser() {
        Role role = createRole();

        User user = User.builder()
                .first_name("John")
                .last_name("Doe")
                .phone("+256777338787")
                .email("johndoe@gmail.com")
                .address("Uganda")
                .role(role)
                .build();

        userRepository.save(user);

        Assertions.assertThat(user.getId()).isGreaterThan(0);
    }

    private Role createRole() {
        Role role = Role.builder()
                .name("Admin")
                .build();

        return roleRepository.save(role);
    }
}

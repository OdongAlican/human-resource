package com.humanresource.hr.repository;

import com.humanresource.hr.role.Role;
import com.humanresource.hr.role.RoleRepository;
import com.humanresource.hr.user.User;
import com.humanresource.hr.user.UserRepository;
import lombok.NonNull;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

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
        Assertions.assertThat(user.getEmail()).isEqualTo("johndoe@gmail.com");
        Assertions.assertThat(user.getFirst_name()).isNotEqualTo("johndoe@gmail.com");
        Assertions.assertThat(user.getFirst_name()).isEqualTo("John");
        Assertions.assertThat(user.getLast_name()).isEqualTo("Doe");
    }

    @Test
    public void getUser() {
        Optional<User> user = userRepository.findById(1L);
        user.ifPresent(value -> Assertions.assertThat(value.getId()).isEqualTo(1L));
        user.ifPresent(value -> Assertions.assertThat(value.getAddress()).isEqualTo("Uganda"));
    }

    @Test
    public void getListOfUser() {
        List<User> users = userRepository.findAll();
        Assertions.assertThat(users.size()).isGreaterThan(0);
    }

    private @NonNull Role createRole() {
        return roleRepository.save(Role.builder().name("Admin").build());
    }
}

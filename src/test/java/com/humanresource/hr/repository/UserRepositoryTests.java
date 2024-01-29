package com.humanresource.hr.repository;

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
    public void testSaveUser() {
        Role role = createRole();
        User user = createUser(role);
        User createdUser = userRepository.save(user);
        Assertions.assertThat(createdUser.getEmail()).isEqualTo("johndoe@gmail.com");
        Assertions.assertThat(createdUser.getFirst_name()).isEqualTo("John");
        Assertions.assertThat(createdUser.getLast_name()).isEqualTo("Doe");
        Assertions.assertThat(createdUser.getLast_name()).isNotEqualTo("sammy");
    }

    @Test
    public void testUpdateUser() {

        Role role = createRole();
        User user = createUser(role);
        User response = userRepository.save(user);
        User foundUser = findUserById(response.getId());

        foundUser.setEmail("updatedEmail@gmail.com");
        userRepository.save(foundUser);

        User updatedUser = findUserById(response.getId());

        Assertions.assertThat(updatedUser).isNotNull();
        Assertions.assertThat(updatedUser.getId()).isEqualTo(response.getId());
        Assertions.assertThat(updatedUser.getEmail()).isEqualTo("updatedEmail@gmail.com");
        Assertions.assertThat(updatedUser.getEmail()).isNotEqualTo("johndoe@gmail.com");
        Assertions.assertThat(updatedUser.getLast_name()).isEqualTo("Doe");

    }

    private User createUser(Role role) {
        return User.builder()
                .first_name("John")
                .last_name("Doe")
                .phone("+256777338787")
                .email("johndoe@gmail.com")
                .address("Uganda")
                .role(role)
                .build();
    }


    private User findUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    private Role createRole() {
        return roleRepository.save(Role.builder().name("TestRole").build());
    }
}

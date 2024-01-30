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
import org.springframework.test.annotation.DirtiesContext;

import java.util.ArrayList;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Test
    @DirtiesContext
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
    @DirtiesContext
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

    @Test
    @DirtiesContext
    public void testCreateMultipleUsers() {
        Role role = createRole();
        int numberOfUsersToCreate = 5;

        List<User> users = new ArrayList<User>();

        for (int i = 1; i <= numberOfUsersToCreate; i++) {

            User user = User.builder()
                    .first_name("john" + i)
                    .last_name("Doe" + i)
                    .phone("+256777338787")
                    .email("john" + i + "@gmail.com")
                    .address("Uganda")
                    .role(role)
                    .build();
            users.add(user);
        }

        List<User> createdUsers = userRepository.saveAll(users);

        Assertions.assertThat(createdUsers.size()).isEqualTo(numberOfUsersToCreate);
        Assertions.assertThat(createdUsers.size()).isNotEqualTo(6);

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

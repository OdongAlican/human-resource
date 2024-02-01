package com.humanresource.hr.repository;

import com.humanresource.hr.role.Role;
import com.humanresource.hr.role.RoleRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RoleRepositoryTests {
    @Autowired
    private RoleRepository roleRepository;

    @Test
    @DirtiesContext
    public void testSaveRole() {
        Role role = createRole();
        Role response = roleRepository.save(role);

        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response.getName()).isEqualTo("Admin");
        Assertions.assertThat(response.getName()).isNotEqualTo("Accountant");
    }

    @Test
    @DirtiesContext
    public void testUpdateRole() {
        Role role = createRole();
        Role response = roleRepository.save(role);

        response.setName("User");
        Role result = roleRepository.save(response);
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getName()).isEqualTo("User");
        Assertions.assertThat(result.getName()).isNotEqualTo("Admin");
    }

    private Role createRole() {
        return Role.builder().name("Admin").build();
    }
}

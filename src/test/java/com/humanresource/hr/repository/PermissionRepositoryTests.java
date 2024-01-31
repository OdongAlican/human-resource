package com.humanresource.hr.repository;

import com.humanresource.hr.permission.Permission;
import com.humanresource.hr.permission.PermissionRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Optional;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PermissionRepositoryTests {

    @Autowired
    private PermissionRepository permissionRepository;

    @Test
    @DirtiesContext
    public void testSavePermission() {
        Permission permission = createPermission();
        Permission data = permissionRepository.save(permission);

        Permission response = permissionRepository.findById(data.getId()).orElse(null);

        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response.getName()).isEqualTo("create");
        Assertions.assertThat(response.getName()).isNotEqualTo("update");
    }

    @Test
    @DirtiesContext
    public void testUpdatePermission() {
        Permission permission = createPermission();
        Permission data = permissionRepository.save(permission);

        Permission response = permissionRepository.findById(data.getId()).orElse(null);

        assert response != null;
        response.setName("update");
        permissionRepository.save(response);

        Permission perm = permissionRepository.findById(data.getId()).orElse(null);
        Assertions.assertThat(perm).isNotNull();
        Assertions.assertThat(perm.getName()).isEqualTo("update");
        Assertions.assertThat(perm.getName()).isNotEqualTo("create");
    }

    @Test
    @DirtiesContext
    public void testDeletePermission() {
        Permission permission = createPermission();
        Permission perm = permissionRepository.save(permission);

        permissionRepository.deleteById(perm.getId());
        Optional<Permission> result = permissionRepository.findById(perm.getId());
        Assertions.assertThat(result.isEmpty()).isEqualTo(true);
        Assertions.assertThat(result.isPresent()).isEqualTo(false);
    }

    private Permission createPermission() {
        return Permission.builder().name("create").build();
    }
}

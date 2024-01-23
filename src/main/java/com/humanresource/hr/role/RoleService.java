package com.humanresource.hr.role;

import com.humanresource.hr.helper.Constants;
import com.humanresource.hr.helper.DeleteResponse;
import com.humanresource.hr.permission.Permission;
import com.humanresource.hr.permission.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    public List<Role> fetchAllRoles() {
        try {
            return roleRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Role createRole(Role role) {
        try {
            return roleRepository.save(role);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Optional<Role> findOneRole(Long roleId) {
        try {
            return roleRepository.findById(roleId);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Role assignPermissionsToRole(Long roleID, Long permID) {

        try {
            Set<Permission> permissions = null;
            Optional<Role> role = roleRepository.findById(roleID);
            Optional<Permission> permission = permissionRepository.findById(permID);

            if (role.isPresent() && permission.isPresent()) {
                permissions = role.get().getPermissions();
                permissions.add(permission.get());
                role.get().setPermissions(permissions);
                return roleRepository.save(role.get());
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public DeleteResponse deleteRole(Long roleID) {
        DeleteResponse response = new DeleteResponse();
        if (roleRepository.existsById(roleID)) {
            roleRepository.deleteById(roleID);
            response.setMessage(Constants.DELETED_SUCCESSFULLY);
            response.setSuccess(true);
        } else {
            response.setMessage(Constants.ENTITY_NOT_FOUND);
            response.setSuccess(false);
        }
        return response;
    }

    public Role fetchRole(Long roleID) {
        return roleRepository.findById(roleID).orElse(null);
    }

    public Role updateRole(Long roleID, Role role) {

        try {
            Role currentData = roleRepository.findById(roleID).orElse(null);
            assert currentData != null;
            currentData.setName(role.getName());
            return roleRepository.save(currentData);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}

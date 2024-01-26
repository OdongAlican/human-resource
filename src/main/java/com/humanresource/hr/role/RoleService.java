package com.humanresource.hr.role;

import com.humanresource.hr.exception.NotFoundException;
import com.humanresource.hr.helper.Constants;
import com.humanresource.hr.helper.DeleteResponse;
import com.humanresource.hr.permission.Permission;
import com.humanresource.hr.permission.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PermissionService permissionService;

    public List<Role> fetchAllRoles() {
        return roleRepository.findAll();
    }

    public Role createRole(Role role) {
        return roleRepository.save(role);
    }

    public Role findOneRole(Long roleId) throws NotFoundException {
        return roleRepository.findById(roleId)
                .orElseThrow(() -> new NotFoundException("User with ID " + roleId + " is not found"));
    }

    public Role assignPermissionsToRole(Long roleID, Long permID) throws NotFoundException {

        Role role = findOneRole(roleID);

        if (role == null)
            throw new NotFoundException("User with ID " + roleID + " is not found");

        Permission permission = permissionService.findOnePermission(permID);
        Set<Permission> permissions = role.getPermissions();
        permissions.add(permission);
        role.setPermissions(permissions);
        return roleRepository.save(role);

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

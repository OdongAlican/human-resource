package com.humanresource.hr.permission;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PermissionService {
    @Autowired
    public PermissionRepository permissionRepository;

    public Permission createPermission(Permission permission) {
        return permissionRepository.save(permission);
    }

    public List<Permission> fetchAllPermissions() {
        return permissionRepository.findAll();
    }

    public Map<String, Object> deletePermission(Long permID) {
        Map<String, Object> response = new HashMap<>();
        try {
            permissionRepository.deleteById(permID);
            response.put("response", "Deleted successfully");
            response.put("success", true);
            return response;
        } catch (Exception e) {
            response.put("response", e.getMessage());
            return response;
        }
    }
}

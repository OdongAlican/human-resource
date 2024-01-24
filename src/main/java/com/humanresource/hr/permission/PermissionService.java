package com.humanresource.hr.permission;

import com.humanresource.hr.helper.Constants;
import com.humanresource.hr.helper.DeleteResponse;
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

    public Permission findOnePermission(Long permID) {
        return permissionRepository.findById(permID)
                .orElseThrow(() -> new IllegalArgumentException(Constants.NOT_FOUND));
    }

    public DeleteResponse deletePermission(Long permID) {
        DeleteResponse response = new DeleteResponse();
        if (permissionRepository.existsById(permID)) {
            permissionRepository.deleteById(permID);
            response.setMessage(Constants.DELETED_SUCCESSFULLY);
            response.setSuccess(true);
        } else {
            response.setMessage(Constants.ENTITY_NOT_FOUND);
            response.setSuccess(false);
        }

        return response;
    }
}

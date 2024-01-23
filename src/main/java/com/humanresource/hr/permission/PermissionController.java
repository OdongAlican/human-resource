package com.humanresource.hr.permission;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @PostMapping("/permissions")
    public ResponseEntity<?> createPermission(@RequestBody Permission permission) {
        try {
            Permission response = permissionService.createPermission(permission);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/permissions")
    public ResponseEntity<List<Permission>> fetchAllPermissions() {
        try {
            List<Permission> permissions = permissionService.fetchAllPermissions();
            return new ResponseEntity<>(permissions, HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @DeleteMapping("/permissions/{permID}")
    public ResponseEntity<?> deletePermission(@PathVariable Long permID) {
        try {
            var response = permissionService.deletePermission(permID);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

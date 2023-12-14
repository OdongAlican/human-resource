package com.humanresource.hr.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @GetMapping("/roles")
    public ResponseEntity<?> fetchAllRoles(){
        try {
            List<Role> roles = roleService.fetchAllRoles();
            return new ResponseEntity<>(roles, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/roles")
    public ResponseEntity<?> createRole(@RequestBody Role role){
        try {
            Role response = roleService.createRole(role);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/roles/{roleID}/permissions/{permID}")
    public ResponseEntity<?> assignPermissionsToRole(
            @PathVariable Long roleID,
            @PathVariable Long permID
    ){
        try{
            Role response = roleService.assignPermissionsToRole(roleID, permID);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/roles/{roleID}")
    public ResponseEntity<?> deleteRole(@PathVariable Long roleID){
        try{
            var response = roleService.deleteRole(roleID);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

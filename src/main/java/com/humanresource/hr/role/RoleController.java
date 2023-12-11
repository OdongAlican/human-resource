package com.humanresource.hr.role;

import com.humanresource.hr.user.User;
import com.humanresource.hr.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class RoleController {

    private RoleRepository roleRepository;

    @GetMapping("/roles")
    public ResponseEntity<?> fetchAllRoles(){
        try {
            List<Role> roles = roleRepository.findAll();
            return new ResponseEntity<>(roles, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}

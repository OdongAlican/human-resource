package com.humanresource.hr.user;

import com.humanresource.hr.role.Role;
import com.humanresource.hr.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users = userService.fetchAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping("/users/{roleId}")
    public ResponseEntity<?> createUser( @PathVariable Long roleId, @RequestBody User user){
        Optional<Role> optionalRole = roleService.findOneRole(roleId);

        if (optionalRole.isPresent()) {
            Role role = optionalRole.get();

            User requestBody = User.builder()
                    .first_name(user.getFirst_name())
                    .last_name(user.getLast_name())
                    .email(user.getEmail())
                    .address(user.getAddress())
                    .role(role)
                    .build();

            try {
                User response = userService.saveUser(requestBody);
                return new ResponseEntity<>(response, HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>("Role not found", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/users/{id}")
    public  ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody User user){

        Optional<User> currentUser = userService.findUSer(id);

        if(currentUser.isEmpty()){
            return new ResponseEntity<>("User does not exist", HttpStatus.NOT_FOUND);
        }

        try {
            User existingUser = currentUser.get();
            existingUser.setFirst_name(user.getFirst_name());
            existingUser.setLast_name(user.getLast_name());
            existingUser.setAddress(user.getAddress());
            existingUser.setEmail(user.getEmail());

            User response = userService.saveUser(existingUser);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<?> getSingleUser(@PathVariable Long id){
        try{
           Optional<User> user = userService.findUSer(id);
           return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
package com.humanresource.hr.user;

import com.humanresource.hr.exception.ControllerRequestException;
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
    public ResponseEntity<User> createUser( @PathVariable Long roleId, @RequestBody User user){

        Optional<Role> role = roleService.findOneRole(roleId);
        if (role.isEmpty())  throw new ControllerRequestException("Role not found");

        try {
            User response = userService.saveUser(user, role.get());
            return new ResponseEntity<>(response, HttpStatus.CREATED);

        } catch (Exception e) {
            throw new ControllerRequestException(e.getMessage());
        }
    }

    @PutMapping("/users/{userID}/role/{roleID}")
    public  ResponseEntity<?> updateUser(@PathVariable Long roleID, @PathVariable Long userID,@RequestBody User user){

        Optional<User> currentUser = userService.findUSer(userID);
        Optional<Role> optionalRole = roleService.findOneRole(roleID);

        if(currentUser.isEmpty() ){
            return new ResponseEntity<>("User does not exist", HttpStatus.NOT_FOUND);
        }

        else if (optionalRole.isPresent()){
            try {
                Role role = optionalRole.get();
                User existingUser = currentUser.get();
                User response = userService.updateUser(existingUser, user, role);
                return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        } else {
            return new ResponseEntity<>("Role not found", HttpStatus.NOT_FOUND);
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

    @DeleteMapping("/users/{userID}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userID){
        try{
            var response = userService.deleteUser(userID);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
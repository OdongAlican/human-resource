package com.humanresource.hr.user;

import com.humanresource.hr.helper.Constants;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Constants.GENERAL_ROUTE)
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.fetchAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping("/users/{roleId}")
    public ResponseEntity<?> createUser(@PathVariable Long roleId, @RequestBody @Valid User user) {
        try {
            User response = userService.saveUser(user, roleId);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/users/{roleId}")
    public ResponseEntity<?> updateUser(@PathVariable Long roleId, @RequestBody @Valid User user) {
        try {
            User response = userService.updateUser(user, roleId);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/users/{userID}")
    public ResponseEntity<?> getSingleUser(@PathVariable Long userID) {
        try {
            User user = userService.findUser(userID);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/users/{userID}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userID) {
        try {
            var response = userService.deleteUser(userID);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
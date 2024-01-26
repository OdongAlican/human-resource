package com.humanresource.hr.user;

import com.humanresource.hr.exception.NotFoundException;
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
    public ResponseEntity<User> createUser(
            @PathVariable Long roleId,
            @RequestBody @Valid User user
    ) throws NotFoundException {
        User response = userService.saveUser(user, roleId);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/users/{roleId}")
    public ResponseEntity<User> updateUser(
            @PathVariable Long roleId,
            @RequestBody @Valid User user
    ) throws NotFoundException {
        User response = userService.updateUser(user, roleId);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/users/{userID}")
    public ResponseEntity<User> getSingleUser(
            @PathVariable Long userID
    ) throws NotFoundException {
        User user = userService.findUser(userID);
        return new ResponseEntity<>(user, HttpStatus.OK);
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
package com.humanresource.hr.user;

import com.humanresource.hr.exception.ControllerRequestException;
import com.humanresource.hr.helper.Constants;
import com.humanresource.hr.helper.DeleteResponse;
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
    public ResponseEntity<User> createUser(@PathVariable Long roleId, @RequestBody User user) {
        try {
            User response = userService.saveUser(user, roleId);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new ControllerRequestException(e.getMessage());
        }
    }

    @PutMapping("/users/{roleId}")
    public ResponseEntity<User> updateUser(@PathVariable Long roleId, @RequestBody User user) {
        try {
            User response = userService.updateUser(user, roleId);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new ControllerRequestException(e.getMessage());
        }
    }

    @GetMapping("/users/{userID}")
    public ResponseEntity<User> getSingleUser(@PathVariable Long userID) {
        try {
            User user = userService.findUser(userID);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            throw new ControllerRequestException(e.getMessage());
        }
    }

    @DeleteMapping("/users/{userID}")
    public ResponseEntity<DeleteResponse> deleteUser(@PathVariable Long userID) {
        try {
            var response = userService.deleteUser(userID);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            throw new ControllerRequestException(e.getMessage());
        }
    }
}
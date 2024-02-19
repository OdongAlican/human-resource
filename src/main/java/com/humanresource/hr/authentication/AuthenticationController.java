package com.humanresource.hr.authentication;

import com.humanresource.hr.exception.NotFoundException;
import com.humanresource.hr.user.User;
import com.humanresource.hr.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    @Autowired
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<User> register(@RequestBody User user) throws NotFoundException {
        User response = userService.findUser(user.getId());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}

package com.humanresource.hr.user;

import com.humanresource.hr.helper.Constants;
import com.humanresource.hr.helper.DeleteResponse;
import com.humanresource.hr.role.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> fetchAllUsers(){ return userRepository.findAll(); }

    public User saveUser(User user, Role role){

        User requestBody = User.builder()
                .first_name(user.getFirst_name())
                .last_name(user.getLast_name())
                .email(user.getEmail())
                .address(user.getAddress())
                .phone(user.getPhone())
                .role(role)
                .build();
        return userRepository.save(requestBody);
    }

    public User updateUser(User existingUser, User requestBody, Role role){

        existingUser.setFirst_name(requestBody.getFirst_name());
        existingUser.setLast_name(requestBody.getLast_name());
        existingUser.setAddress(requestBody.getAddress());
        existingUser.setEmail(requestBody.getEmail());
        existingUser.setRole(role);
        return userRepository.save(existingUser);
    }

    public User findUSer(Long userId){ return userRepository.findById(userId).orElse(null);}

    public DeleteResponse deleteUser(Long userID) {
        DeleteResponse deleteResponse = new DeleteResponse();
        if(userRepository.existsById(userID)){
            userRepository.deleteById(userID);
            deleteResponse.setMessage(Constants.DELETED_SUCCESSFULLY);
            deleteResponse.setSuccess(true);
        } else {
            deleteResponse.setMessage(Constants.ENTITY_NOT_FOUND);
            deleteResponse.setSuccess(false);
        }
        return deleteResponse;
    }
}
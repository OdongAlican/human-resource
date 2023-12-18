package com.humanresource.hr.user;

import com.humanresource.hr.role.Role;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> fetchAllUsers(){ return userRepository.findAll(); }

    public User saveUser(User user, Role role){

        User requestBody = User.builder()
                .first_name(user.getFirst_name())
                .last_name(user.getLast_name())
                .email(user.getEmail())
                .address(user.getAddress())
                .role(role)
                .build();
        try {
            return userRepository.save(requestBody);
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public User updateUser(User existingUser, User requestBody, Role role){
        try {
            existingUser.setFirst_name(requestBody.getFirst_name());
            existingUser.setLast_name(requestBody.getLast_name());
            existingUser.setAddress(requestBody.getAddress());
            existingUser.setEmail(requestBody.getEmail());
            existingUser.setRole(role);
            return userRepository.save(existingUser);

        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }

    }

    public Optional<User> findUSer(Long userId){ return userRepository.findById(userId);}

    public Object deleteUser(Long userID) {

        Map<String,String> response = new HashMap<String, String>();
        try{
            userRepository.deleteById(userID);
            response.put("response", "Deleted successfully");
            response.put("success", "true");
            return response;

        } catch (Exception e){
            response.put("response", e.getMessage());
            return response;
        }
    }
}
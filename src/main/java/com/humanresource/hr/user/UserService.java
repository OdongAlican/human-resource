package com.humanresource.hr.user;

import com.humanresource.hr.helper.Constants;
import com.humanresource.hr.helper.DeleteResponse;
import com.humanresource.hr.role.Role;
import com.humanresource.hr.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleService roleService;

    public List<User> fetchAllUsers() {
        return userRepository.findAll();
    }

    public User saveUser(User user, Long roleID) {

        Role role = roleService.findOneRole(roleID).orElse(null);
        assert role != null;

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

    public User updateUser(User request, Long userID, Long roleID) {

        User user = findUSer(userID);
        Role role = roleService.findOneRole(roleID).orElse(null);

        assert user != null && role != null;

        user.setFirst_name(request.getFirst_name());
        user.setLast_name(request.getLast_name());
        user.setAddress(request.getAddress());
        user.setEmail(request.getEmail());
        user.setRole(role);
        return userRepository.save(user);
    }

    public User findUSer(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public DeleteResponse deleteUser(Long userID) {
        DeleteResponse deleteResponse = new DeleteResponse();
        if (userRepository.existsById(userID)) {
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
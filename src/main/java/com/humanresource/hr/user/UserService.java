package com.humanresource.hr.user;

import com.humanresource.hr.helper.Constants;
import com.humanresource.hr.helper.DeleteResponse;
import com.humanresource.hr.role.Role;
import com.humanresource.hr.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional(readOnly = true)
    public User findUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException(Constants.NOT_FOUND + userId));
    }

    @Transactional
    public User saveUser(User user, Long roleID) {
        Role role = roleService.findOneRole(roleID);
        if (role == null) {
            throw new IllegalArgumentException(Constants.NOT_FOUND + roleID);
        }

        User newUser = User.builder()
                .first_name(user.getFirst_name())
                .last_name(user.getLast_name())
                .email(user.getEmail())
                .address(user.getAddress())
                .phone(user.getPhone())
                .role(role)
                .build();

        return userRepository.save(newUser);
    }

    @Transactional
    public User updateUser(User request, Long userID, Long roleID) {
        User user = findUser(userID);
        Role role = roleService.findOneRole(roleID);
        if (user == null || role == null) {
            throw new IllegalArgumentException(Constants.NOT_FOUND);
        }

        user.setFirst_name(request.getFirst_name());
        user.setLast_name(request.getLast_name());
        user.setAddress(request.getAddress());
        user.setEmail(request.getEmail());
        user.setRole(role);

        return userRepository.save(user);
    }

    @Transactional
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

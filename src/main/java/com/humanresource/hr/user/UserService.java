package com.humanresource.hr.user;

import com.humanresource.hr.exception.NotFoundException;
import com.humanresource.hr.helper.Constants;
import com.humanresource.hr.helper.DeleteResponse;
import com.humanresource.hr.role.Role;
import com.humanresource.hr.role.RoleService;
import lombok.NonNull;
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

    public User findUser(Long userId) throws NotFoundException {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User with ID " + userId + " is not found"));
    }

    public User saveUser(User user, Long roleID) throws NotFoundException {
        Role role = roleService.findOneRole(roleID);
        if (role == null) {
            throw new NotFoundException(("User with ID " + roleID + " is not found"));
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

    public User updateUser(@NonNull User request, Long roleId) throws NotFoundException {

        User user = findUser(request.getId());
        Role role = roleService.findOneRole(roleId);

        user.setFirst_name(request.getFirst_name());
        user.setLast_name(request.getLast_name());
        user.setAddress(request.getAddress());
        user.setPhone(request.getPhone());
        user.setRole(role);
        user.setEmail(request.getEmail());
        return userRepository.save(user);
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

package com.humanresource.hr.user;

import com.humanresource.hr.role.Role;
import com.humanresource.hr.role.RoleService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private RoleService roleService;

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
    };

    public Optional<User> findUSer(Long userId){ return userRepository.findById(userId);}
}
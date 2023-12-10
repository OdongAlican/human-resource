package com.humanresource.hr.user;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> fetchAllUsers(){ return userRepository.findAll(); }

    public User saveUser(User user){
        return userRepository.save(user);
    };

    public Optional<User> findUSer(Long userId){ return userRepository.findById(userId);}
}
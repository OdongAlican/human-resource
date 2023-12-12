package com.humanresource.hr.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public List<Role> fetchAllRoles(){
        try {
            return roleRepository.findAll();
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public Role createRole(Role role){
        try {
            return roleRepository.save(role);
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public Optional<Role> findOneRole(Long roleId){
        try{
            return roleRepository.findById(roleId);
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}

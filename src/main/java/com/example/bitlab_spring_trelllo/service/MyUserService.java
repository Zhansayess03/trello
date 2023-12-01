package com.example.bitlab_spring_trelllo.service;

import com.example.bitlab_spring_trelllo.mapper.UsersMapper;
import com.example.bitlab_spring_trelllo.model.Permission;
import com.example.bitlab_spring_trelllo.model.User;
import com.example.bitlab_spring_trelllo.repository.PermissionRepository;
import com.example.bitlab_spring_trelllo.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class MyUserService implements UserDetailsService {

    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private PermissionRepository permissionRepository;
    @Autowired
    private UsersMapper usersMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = usersRepository.findAllByEmail(email);
        if(user ==null){
            throw new UsernameNotFoundException("User NOt Found");
        }
        return user;
    }
    public String signUp(User signUpUser){
        String flag = "userExist";
        User checkUser = usersRepository.findAllByEmail(signUpUser.getEmail());
        if(checkUser==null){
            List<Permission> permissions = new ArrayList<>();
            permissions.add(permissionRepository.findAllByRole("ROLE_USER"));
            User user = new User();
            user.setEmail(signUpUser.getEmail());
            user.setFullName(signUpUser.getFullName());
            user.setAge(signUpUser.getAge());
            user.setPassword(passwordEncoder.encode(signUpUser.getPassword()));
            user.setPermissions(permissions);
            usersRepository.save(user);
            flag = "registeredSuccess";
        }
        return flag;
    }

    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (User) authentication.getPrincipal();
    }
}

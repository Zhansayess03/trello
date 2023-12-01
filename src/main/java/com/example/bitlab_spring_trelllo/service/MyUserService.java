package com.example.bitlab_spring_trelllo.service;

import com.example.bitlab_spring_trelllo.mapper.UsersMapper;
import com.example.bitlab_spring_trelllo.model.Permission;
import com.example.bitlab_spring_trelllo.model.Users;
import com.example.bitlab_spring_trelllo.repository.PermissionRepository;
import com.example.bitlab_spring_trelllo.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
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
        Users users = usersRepository.findAllByEmail(email);
        if(users==null){
            throw new UsernameNotFoundException("User NOt Found");
        }
        return users;
    }
    public String signUp(Users signUpUser){
        String flag = "userExist";
        Users checkUser = usersRepository.findAllByEmail(signUpUser.getEmail());
        if(checkUser==null){
            List<Permission> permissions = new ArrayList<>();
            permissions.add(permissionRepository.findAllByRole("ROLE_USER"));
            Users users = new Users();
            users.setEmail(signUpUser.getEmail());
            users.setFullName(signUpUser.getFullName());
            users.setAge(signUpUser.getAge());
            users.setPassword(passwordEncoder.encode(signUpUser.getPassword()));
            users.setPermissions(permissions);
            usersRepository.save(users);
            flag = "registeredSuccess";
        }
        return flag;
    }
}

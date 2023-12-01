package com.example.bitlab_spring_trelllo.controller;

import com.example.bitlab_spring_trelllo.model.Users;
import com.example.bitlab_spring_trelllo.service.MyUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/users")
@RequiredArgsConstructor
public class UsersController {
    @Autowired

    private MyUserService userService;

    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

    @GetMapping(value = "/sign-up")
    public String signUpPage(){
        return "sign-up";
    }

    @PostMapping(value = "/sign-up")
    public String signUpPost(@RequestParam("email") String email,
                             @RequestParam("fullName") String fullName,
                             @RequestParam("age") int age,
                             @RequestParam("password") String password,
                             @RequestParam("re-password") String rePassword){
        if(password.equals(rePassword)) {
            Users users = new Users();
            users.setEmail(email);
            users.setFullName(fullName);
            users.setAge(age);
            users.setPassword(password);
            String text = userService.signUp(users);
            if(text.equals("registeredSuccess")) {
                return "redirect:/users/login";
            }else {
                return "redirect:/users/sign-up?UserExist";
            }
        }else {
            return "redirect:/users/sign-up?PasswordNotMatches";
        }
    }
}

package com.codekraft.cdfipb.recruitementfrontend.controllers;

import com.codekraft.cdfipb.recruitementfrontend.domains.User;
import com.codekraft.cdfipb.recruitementfrontend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class StaticPagesController {

    @Autowired
    private UserRepository userRepo;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping(name = "/register", method = RequestMethod.GET)
    public String register(Model model){
        model.addAttribute("user", new User());
        return "register";
    }

    @RequestMapping(value = "/register-account", method = RequestMethod.POST)
    public String register(@Valid User user, BindingResult bindingResult, Model model){

        if (bindingResult.hasErrors()) {
            return "register";
        }

        System.out.println(user.getPassword());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("ROLE_USER");
        userRepo.save(user);
        System.out.println(user.getPassword());

        //Authenticate user and add to application context
        Authentication auth  = new UsernamePasswordAuthenticationToken(user,user.getPassword(),user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);

        //Check the roles and redirect user
        if (auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER"))) {
            System.out.println("I am a job seeker");
            return "redirect:/employee";
        }
        System.out.println("I am an employee");
        return "redirect:/users/current-user";
    }

    @RequestMapping("/password/reset")
    public String reset(){
        return "reset";
    }
}

package rw.ac.rca.auth.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rw.ac.rca.auth.models.User;
import org.springframework.security.authentication.AuthenticationManager;
import rw.ac.rca.auth.services.MyUserDetailsService;

import java.io.IOException;

@RestController
@RequestMapping("/user")
public class UserController {
//    @Autowired
//    AuthenticationManager authManager;
    private MyUserDetailsService myUserDetailsService;

    public UserController( @Autowired MyUserDetailsService myUserDetailsService) {
        this.myUserDetailsService = myUserDetailsService;
    }

    @PostMapping("/register")
    public Object register(@RequestBody User user, HttpServletResponse res, HttpServletRequest req) throws IOException {
         return this.myUserDetailsService.register(user, res, req);
    }
    @PostMapping("/login")
    public Object login(@RequestBody String email,@RequestBody String password, HttpServletResponse res, HttpServletRequest req) throws IOException {
        return this.myUserDetailsService.login(email,password, req, res);
    }
}

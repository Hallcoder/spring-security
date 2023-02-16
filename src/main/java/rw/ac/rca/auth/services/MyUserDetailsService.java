package rw.ac.rca.auth.services;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import rw.ac.rca.auth.models.User;
import rw.ac.rca.auth.repository.UserRepository;

import java.io.IOException;
import java.util.HashMap;

@Component
public class MyUserDetailsService implements UserDetailsService {
    private HashMap messages = new HashMap();
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findUserByUsername(username);
        return user;
    }

    public Object register(User user, HttpServletResponse response, HttpServletRequest request) throws IOException {
        String userEmail = user.getEmail();
        String inputs[] = new String[]{user.getUsername(), user.getEmail(), user.getPassword()};
        int i = inputs.length-1;
        while(i > 0){
            if(inputs[i] == null || inputs[i] == "") {
                messages.put("status","failed");
                messages.put("error_message","Please provide all the required information");
                response.setStatus(403);
                new ObjectMapper().writeValue(response.getOutputStream(), messages);

                break;
            }
            i--;
        }

        User us = this.userRepository.findByEmail(userEmail);
        System.out.println("User"  +us);
        if(us != null){
            messages.put("status","failed");
            messages.put("error_message","User with that email already exists");
            new ObjectMapper().writeValue(response.getOutputStream(), messages);
            response.setStatus(403);
            return null;
        }
        messages.clear();
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        User savedUser  = this.userRepository.save(user);

        return savedUser;
    }
    public Object login(String email,String password,HttpServletRequest req, HttpServletResponse res){
        User user = this.userRepository.findByEmail(email);
        return null;
    }
}

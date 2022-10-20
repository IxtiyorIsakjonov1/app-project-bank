package com.example.appprojectbank.Service;


import com.example.appprojectbank.Entity.User;
import com.example.appprojectbank.Entity.enums.RoleName;
import com.example.appprojectbank.Payload.ReqSignUp;
import com.example.appprojectbank.Repository.RoleRepository;
import com.example.appprojectbank.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class AuthService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AuthenticationManager authenticationManager ;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        if (userRepository.existsByUsername(username)){
             return   userRepository.findByUsername(username).orElseThrow(() -> new IllegalStateException("tamom1"));
        }else if(userRepository.existsByPhoneNumber(username)){
         return   userRepository.findByPhoneNumber(username).orElseThrow(() -> new IllegalStateException("tamom2"));
        }
        throw  new UsernameNotFoundException(username);
    }

    public String register(ReqSignUp reqSignUp){
        Optional<User> byPhoneNumber = userRepository.findByPhoneNumber(reqSignUp.getPhoneNumber());
        if (byPhoneNumber.isPresent()){
            return "Bunday telefon raqam bazada bor";
        }else{
            userRepository.save(
                    new User(
                            passwordEncoder.encode(reqSignUp.getPassword()),
                            reqSignUp.getUsername(),
                            reqSignUp.getPhoneNumber(),
                            roleRepository.findAllByName(RoleName.USER)));
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(reqSignUp.getUsername(),reqSignUp.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            User principal = (User)authentication.getPrincipal();
            String token = jwtTokenProvider.generateToken(principal.getUsername());
            return token;
        }
    }
    public String login(String phoneNumber, String password){
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                phoneNumber, password));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String token = jwtTokenProvider.generateTokenNotRemember(authenticate);
        return token;

    }

    public UserDetails loadUserById(Long fromString) {
        Optional<User> byId = userRepository.findById(fromString);
        if (byId.isPresent()){
            return  byId.get();
        }else{
            throw  new UsernameNotFoundException(fromString.toString());
        }

    }
}

package com.n7.service.impl;

import com.n7.entity.User;
import com.n7.model.CustomUserDetail;
import com.n7.model.UserModel;
import com.n7.repository.UserRepo;
import com.n7.request.LoginRequest;
import com.n7.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private PasswordEncoder passwordEncoder;
    private UserRepo userRepo;
    private AuthenticationManager authenticationManager;
    private JwtService jwtService;
    public UserModel login(LoginRequest loginRequest) {
        try{
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String jwt = jwtService.generateToken((CustomUserDetail) userDetails);
            UserModel userModel = new UserModel();
            userModel.setAccessToken(jwt);
            userModel.setRoleId(((CustomUserDetail) userDetails).getRoleId());
            return userModel;
        }catch (BadCredentialsException ex){
            throw new BadCredentialsException("Invalid username or password");
        }
    }
}

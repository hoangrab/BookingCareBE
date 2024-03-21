package com.n7.service.impl;

import com.n7.dto.UserDTO;
import com.n7.entity.Role;
import com.n7.entity.User;
import com.n7.exception.ResourceNotFoundException;
import com.n7.model.CustomUserDetail;
import com.n7.model.MajorModel;
import com.n7.model.UserModel;
import com.n7.repository.RoleRepo;
import com.n7.repository.UserRepo;
import com.n7.request.LoginRequest;
import com.n7.request.RegisterRequest;
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
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    public Map<String,String> login(LoginRequest loginRequest) {
        try{
            Map<String,String> map = new HashMap<>();
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String jwt = jwtService.generateToken((CustomUserDetail) userDetails);
            map.put("accessToken",jwt);
            map.put("roleId",String.valueOf(((CustomUserDetail) userDetails).getRoleId()));
            return map;
        }catch (BadCredentialsException ex){
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    @Transactional
    public UserModel register(UserDTO userDTO) {
        Optional<Role> role = roleRepo.findById(userDTO.getIdRole());
        if(!role.isPresent()) throw new ResourceNotFoundException("Invalid id role");
        User u = new User();
        u.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        u.setUsername(userDTO.getUserName());
        u.setFullname(userDTO.getFullName());
        u.setRole(role.get());
        u.setEnabled(true);
        UserModel userModel = convertEntityToModel(u);
        userRepo.save(u);
        return userModel;
    }

    @Override
    public List<User> getAllDoctorByMajor(Long id) {
        return null;
    }

    public UserModel convertEntityToModel(User user) {
        MajorModel majorModel = new MajorModel();
        if(user.getMajor()!=null) {
            majorModel.setId(user.getMajor().getId());
            majorModel.setName(user.getMajor().getName());
        }
        UserModel userModel = new UserModel(user.getId(),user.getAvatar(),user.getFullname(),user.getUsername(),user.getPhone(),
                user.getGmail(),user.getRole().getId(),user.isEnabled(),majorModel);
        return userModel;
    }
}

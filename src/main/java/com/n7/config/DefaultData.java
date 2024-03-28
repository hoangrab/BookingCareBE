package com.n7.config;

import com.n7.constant.RoleName;
import com.n7.entity.Role;
import com.n7.entity.User;
import com.n7.repository.RoleRepo;
import com.n7.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class DefaultData implements ApplicationRunner {
    private final RoleRepo roleRepo;
    private final UserRepo userRepo;
    private final PasswordEncoder encoder;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        Role role = roleRepo.findByName(RoleName.ROLE_ADMIN);
        if(role==null) {
            role = new Role(RoleName.ROLE_ADMIN,"Manage all system");
            roleRepo.save(role);
        }
        Role role1 = roleRepo.findByName(RoleName.ROLE_DOCTOR);
        if(role1==null) {
            role1 = new Role(RoleName.ROLE_DOCTOR,"Nothing");
            roleRepo.save(role1);
        }
        User user = userRepo.findByUsername("admin");
        if(user == null) {
            user = new User();
            user.setUsername("admin");
            user.setPassword(encoder.encode("123456"));
            user.setRole(role);
            userRepo.save(user);
        }
    }
}

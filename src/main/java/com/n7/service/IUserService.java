package com.n7.service;

import com.n7.entity.User;

import java.util.List;

public interface IUserService {
    List<User> getAllDoctorByMajor(Long id);

}

package com.n7.controller;

import com.n7.dto.UserDTO;
import com.n7.model.UserModel;
import com.n7.response.ErrorResponse;
import com.n7.response.SuccessResponse;
import com.n7.service.impl.MajorService;
import com.n7.service.impl.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final MajorService majorService;

    @GetMapping("/doctor/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") Long id) {
        try{
            Optional<UserModel> userModel = userService.getDoctorById(id);
            if(!userModel.isPresent()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse<>("Not found doctor have id: " + id));
            }
            return ResponseEntity.ok().body(new SuccessResponse<>("Get data success",userModel.get()));
        }catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse<>("Server Error"));
        }

    }


    @GetMapping("/doctor")
    public ResponseEntity<?> getUserByMajor(@RequestParam(value = "majorId") Long majorId) {
        try {
            if(!majorService.findById(majorId).isPresent()){
                return ResponseEntity.badRequest().body(new ErrorResponse<>("Not found Id Major"));
            }
            List<UserModel> list = userService.getAllDoctorByMajor(majorId);
            return ResponseEntity.ok().body(new SuccessResponse<>("Get data success",list));
        }catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse<>("Server Error"));
        }
    }

    @PostMapping("/doctor")
    public ResponseEntity<?> updateUser(@Valid @RequestBody UserDTO userDTO) {
        return ResponseEntity.ok().body(null);
    }
}

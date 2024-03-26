package com.n7.controller;

import com.n7.dto.UserDTO;
import com.n7.model.UserModel;
import com.n7.response.ErrorResponse;
import com.n7.response.SuccessResponse;
import com.n7.service.impl.MajorService;
import com.n7.service.impl.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
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
    @GetMapping("/doctors")
    public ResponseEntity<?> getDoctorBy(@RequestParam(value = "page",defaultValue = "1") int page,
                                         @RequestParam(value = "size",defaultValue = "100") int size,
                                        @RequestParam(value = "majorId",required = false) Long majorId) {
        try {
            if(majorId != null && !majorService.findById(majorId).isPresent()){
                return ResponseEntity.badRequest().body(new ErrorResponse<>("Not found Id Major"));
            }
            Pageable pageable = PageRequest.of(page-1,size);
            List<UserModel> list = userService.getAllDoctorByMajor(majorId,pageable);
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

package com.n7.controller;

import com.n7.model.ScheduleModel;
import com.n7.repository.ScheduleUserRepo;
import com.n7.response.ErrorResponse;
import com.n7.response.SuccessResponse;
import com.n7.service.impl.ScheduleService;
import com.n7.service.impl.UserService;
import com.n7.utils.ConvertTimeUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;
    private final UserService userService;
    private final ScheduleUserRepo scheduleUserRepo;

    @GetMapping("schedule")
    public ResponseEntity<?> getAllSchedule(@RequestParam("idDoctor") Long idDoctor) {
        try{
            if(!userService.getDoctorById(idDoctor).isPresent()) {
                return ResponseEntity.badRequest().body(new ErrorResponse<>("Khong tim thay id Doctor :" + idDoctor));
            }
            List<ScheduleModel> list =  scheduleService.getAllSchedule(idDoctor);
            return ResponseEntity.ok().body(new SuccessResponse<>("Get data success",list));
        }catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse<>("Error!!!"));
        }
    }

}

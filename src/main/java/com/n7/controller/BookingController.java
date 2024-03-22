package com.n7.controller;

import com.n7.dto.BookingDTO;
import com.n7.response.ErrorResponse;
import com.n7.response.SuccessResponse;
import com.n7.service.impl.MailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class BookingController {
    private final MailService mailService;

    @GetMapping("/bookings")
    public ResponseEntity<?> getAllBooking() {
        return ResponseEntity.ok().body(null);
    }

    @PostMapping("/booking")
    public ResponseEntity<?> createBooking(@Valid @RequestBody BookingDTO bookingDTO) {
        return ResponseEntity.ok().body(null);
    }

    @PostMapping
    public ResponseEntity<?> updataBooking(@Valid @RequestPart BookingDTO bookingDTO) {
        return ResponseEntity.ok().body(null);
    }

    @DeleteMapping("booking/{id}")
    public ResponseEntity<?> deleteBooking(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(null);
    }

    @GetMapping("testmail")
    public ResponseEntity<?> testmail() {
        try{
            mailService.sendMail("hoant0355@gmail.com","Thư hủy lịch khám","Bác sĩ chơi đá chưa về, xin cảm ơn");
            return ResponseEntity.ok(new SuccessResponse<>("Đã gửi thành công mail"));
        }catch (Exception ex) {
            return ResponseEntity.ok(new ErrorResponse<>(ex.getMessage()));
        }
    }
}

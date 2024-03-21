package com.n7.controller;

import com.n7.dto.BookingDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/")
public class BookingController {
    @GetMapping("/bookings")
    public ResponseEntity<?> getAllBooking() {
        return ResponseEntity.ok().body(null);
    }

    @PostMapping("/booking")
    public ResponseEntity<?> craetBooking(@Valid @RequestBody BookingDTO bookingDTO) {
        return ResponseEntity.ok().body(null);
    }
}

package com.n7.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
public class BookingController {
    @GetMapping("/bookings")
    public ResponseEntity<?> getAllBooking() {
        return ResponseEntity.ok().body(null);
    }
}

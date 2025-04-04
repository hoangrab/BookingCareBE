package com.n7.controller;

import com.n7.constant.Status;
import com.n7.dto.HistoryDTO;
import com.n7.entity.Booking;
import com.n7.entity.History;
import com.n7.model.HistoryModel;
import com.n7.service.impl.BookingService;
import com.n7.service.impl.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/histories")
public class HistoryController {
    @Autowired
    private HistoryService historyService;
    private BookingService bookingService;
    @GetMapping("")
    public ResponseEntity<List<HistoryModel>> getHistory() {
        return ResponseEntity.ok(HistoryModel.fromEntityList(historyService.getAllHistory()));
    }
    @GetMapping("/{id}")
    public ResponseEntity<HistoryModel> getHistoryById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(HistoryModel.fromEntity(historyService.findById(id)));
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
    @PostMapping("")
    public ResponseEntity<HistoryModel> saveHistory(@RequestBody HistoryDTO historyDTO) {
        try {
            Booking booking = bookingService.findById(historyDTO.getBookingId()).get();
            if(booking.getStatus().equals(Status.ACCEPTING)) {
                bookingService.updateBooking(historyDTO.getBookingId(),Status.SUCCESS);
            }
            return ResponseEntity.ok(HistoryModel.fromEntity(historyService.saveHistory(historyDTO)));
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteHistory(@PathVariable Long id) {
        try {
            History history = historyService.findById(id);
            historyService.deleteHistory(history);
            return ResponseEntity.ok().build();
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<HistoryModel> updateHistory(@RequestBody HistoryDTO historyDTO, @PathVariable Long id) {
        try {
            return ResponseEntity.ok(HistoryModel.fromEntity(historyService.updateHistory(historyDTO,id)));
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
}

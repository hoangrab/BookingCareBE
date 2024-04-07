package com.n7.controller;

import com.n7.constant.Status;
import com.n7.dto.BookingDTO;
import com.n7.entity.Booking;
import com.n7.exception.ResourceNotFoundException;
import com.n7.model.BookingModel;
import com.n7.response.ErrorResponse;
import com.n7.response.SuccessResponse;
import com.n7.service.impl.BookingService;
import com.n7.service.impl.MailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class BookingController {
    private final BookingService bookingService;
    private final MailService mailService;

    @GetMapping("/bookings")
    public ResponseEntity<?> getAllBooking(@RequestParam(value = "status",required = false) Status status,
                                           @RequestParam(value = "idDoctor",required = false) Long id,
                                           @RequestParam(value = "start",required = false) String start,
                                           @RequestParam(value = "end",required = false) String end) {
        try{
            List<BookingModel> list = bookingService.findByParam(status, id,start,end);
            return ResponseEntity.ok(new SuccessResponse<>("Get data ok",list));
        }catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse<>(ex.getMessage()));
        }
    }

    @PostMapping("/booking")
    public ResponseEntity<?> createBooking(@Valid @RequestBody BookingDTO bookingDTO) {
        try{
            System.out.println(bookingDTO);
            bookingService.creatBooking(bookingDTO);
            return ResponseEntity.ok().body(new SuccessResponse<>("ok"));
        }
        catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse<>("hi" +ex.getMessage()));
        }
    }

    @PostMapping("booking/{id}")
    public ResponseEntity<?> updataBooking(@PathVariable("id") Long id,
                                           @RequestPart("status") Status status) {
        try{
            if(bookingService.findById(id).isEmpty()) {
                return ResponseEntity.badRequest().body(new ErrorResponse<>("Booking cannot found with id: " + id));
            }
            bookingService.updateBooking(id,status);
            return ResponseEntity.ok().body(new SuccessResponse<>("Đã cập nhật thành công lịch hẹn với id: " + id));
        }catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse<>(ex.getMessage()));
        }
    }

    @DeleteMapping("booking/{id}")
    public ResponseEntity<?> deleteBooking(@PathVariable("id") Long id) {
        try{
            Optional<Booking> booking = bookingService.findById(id);
            if(booking.isEmpty()) {
                return ResponseEntity.badRequest().body(new ErrorResponse<>("Booking cannot found with id: " + id));
            }
            bookingService.deleteBooking(id);
            mailService.sendMail(booking.get().getEmail(),"Thư hủy lịch khám","Bác sĩ bạn đặt đang có việc bận đột xuất. Rất " +
                    "xin lỗi và mong được gặp lại bạn vào 1 thời gian gần nhât");
            return ResponseEntity.ok().body(new SuccessResponse<>("Đã xóa thành công lịch hẹn với id: " + id));
        }catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse<>(ex.getMessage()));
        }
    }

}

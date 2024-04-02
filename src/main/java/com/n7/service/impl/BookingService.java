package com.n7.service.impl;

import com.n7.constant.Status;
import com.n7.dto.BookingDTO;
import com.n7.entity.Booking;
import com.n7.entity.Schedule;
import com.n7.entity.ScheduleUser;
import com.n7.entity.User;
import com.n7.exception.ResourceAlreadyExitsException;
import com.n7.exception.ResourceNotFoundException;
import com.n7.model.BookingModel;
import com.n7.repository.BookingRepo;
import com.n7.repository.ScheduleRepo;
import com.n7.repository.ScheduleUserRepo;
import com.n7.repository.UserRepo;
import com.n7.utils.ConvertTimeUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingService {
    private final BookingRepo bookingRepo;
    private final UserRepo userRepo;
    private final ScheduleRepo scheduleRepo;
    private final ScheduleUserRepo scheduleUserRepo;

    public Optional<Booking> findById(Long id) {
        return bookingRepo.findById(id);
    }

    public List<BookingModel> findByParam(Status status, Long id, String start, String end) {
        Date en = null, st = null;
        if(start!=null) st = ConvertTimeUtils.stringToDate(start);
        if(end!=null) en = ConvertTimeUtils.stringToDate(end);
        return bookingRepo.findByCustom(id,status,st,en).stream().map(this::convertEntityToModel).collect(Collectors.toList());
    }

    @Transactional
    public void creatBooking(BookingDTO bookingDTO) {
        Optional<User> user = userRepo.findById(bookingDTO.getIdUser());
        if(user.isEmpty()) {
            throw new ResourceNotFoundException("Khong tim thay id Doctor");
        }
        user.get().getListSchedule().stream().forEach( e -> {
            if(e.getSchedule().equals(bookingDTO.getDate()) && e.getSchedule().getSession().equals(bookingDTO.getSession())) {
                throw new ResourceAlreadyExitsException("Lịch đặt đã bị trùng!!!");
            }
        });
        // Save Booking
        Booking booking = convertDtoToEntity(bookingDTO,Status.PENDING1);
        booking.setUser(user.get());
        bookingRepo.save(booking);

        // Save Schedule of Doctor
        Schedule schedule = new Schedule(ConvertTimeUtils.stringToDate(bookingDTO.getDate()),bookingDTO.getSession());
        scheduleRepo.save(schedule);
        ScheduleUser scheduleUser = new ScheduleUser();
        scheduleUser.setUser(user.get());
        scheduleUser.setSchedule(schedule);
        scheduleUserRepo.save(scheduleUser);
    }

    @Transactional
    public void updateBooking(Long id,Status status) {
        Optional<Booking> booking = bookingRepo.findById(id);
        booking.get().setStatus(status);
        bookingRepo.save(booking.get());
    }

    public void deleteBooking(Long id) {
        Optional<Booking> booking = bookingRepo.findById(id);
        booking.get().setStatus(Status.FAILURE);
        bookingRepo.save(booking.get());
    }

    public Booking convertDtoToEntity(BookingDTO bookingDTO, Status status) {
        return new Booking(bookingDTO.getName(),bookingDTO.getDob(),bookingDTO.getPhone(),bookingDTO.getEmail(),bookingDTO.getGender(),
                bookingDTO.getAddress(),ConvertTimeUtils.stringToDate(bookingDTO.getDate()),bookingDTO.getSession(),status,bookingDTO.getNote());
    }

    public BookingModel convertEntityToModel(Booking booking) {
        BookingModel bookingModel = new BookingModel();
        bookingModel.setId(booking.getId());
        bookingModel.setName(booking.getFullName());
        bookingModel.setGender(booking.getGender());
        bookingModel.setDob(booking.getDob());
        bookingModel.setEmail(booking.getEmail());
        bookingModel.setPhone(booking.getPhone());
        bookingModel.setDate(ConvertTimeUtils.dateToString(booking.getDate()));
        bookingModel.setSession(booking.getSession().toString());
        bookingModel.setStatus(booking.getStatus().toString());
        bookingModel.setNote(booking.getNote());
        bookingModel.setNameDoctor(booking.getUser().getFullname());
        bookingModel.setMajor(booking.getUser().getMajor().getName());
        return bookingModel;
    }
}

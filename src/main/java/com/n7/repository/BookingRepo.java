package com.n7.repository;

import com.n7.constant.Status;
import com.n7.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepo extends JpaRepository<Booking, Long> {
    List<Booking> findAllByUserIdAndStatus(Long id, Status status);
}

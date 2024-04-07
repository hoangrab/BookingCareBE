package com.n7.repository;

import com.n7.entity.Schedule;
import com.n7.entity.ScheduleUser;
import com.n7.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleUserRepo extends JpaRepository<ScheduleUser,Long> {
    List<ScheduleUser> findAllByUserId(Long id);
}

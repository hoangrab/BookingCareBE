package com.n7.repository;

import com.n7.entity.ScheduleUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleUserRepo extends JpaRepository<ScheduleUser,Long> {
}

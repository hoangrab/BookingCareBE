package com.n7.service.impl;

import com.n7.entity.Schedule;
import com.n7.model.ScheduleModel;
import com.n7.repository.ScheduleRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepo scheduleRepo;

    public List<ScheduleModel> getAllSchedule(Long idDoctor) {
        return scheduleRepo.findByListUser_Id(idDoctor).stream()
                .map(this::convertToModel).collect(Collectors.toList());
    }

    public ScheduleModel convertToModel(Schedule schedule) {
        ScheduleModel scheduleModel = new ScheduleModel();
        scheduleModel.setId(schedule.getId());
        scheduleModel.setDate(schedule.getDate());
        scheduleModel.setSession(schedule.getSession().toString());
        return scheduleModel;
    }
}

package com.n7.service.impl;

import com.n7.dto.HistoryDTO;
import com.n7.entity.History;
import com.n7.repository.HistoryRepo;
import com.n7.utils.ConvertTimeUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HistoryService {
    private final HistoryRepo historyRepo;
    private final BookingService bookingService;

    public List<History> getAllHistory() {
        return historyRepo.findAll();
    }

    public History saveHistory(HistoryDTO historyDTO) {
        History history = History.builder().build();
        history = convertDtoToEntity(historyDTO, history);
        return historyRepo.save(history);
    }

    public History updateHistory(HistoryDTO historyDTO, Long id) {
        History history = historyRepo.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
        history = convertDtoToEntity(historyDTO, history);
        return historyRepo.save(history);
    }
    public void deleteHistory(History history) {
        historyRepo.deleteById(history.getId());
    }

    public History findById(Long id) {
        return historyRepo.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
    }

    private History convertDtoToEntity(HistoryDTO historyDTO, History history){
        history.setBhyt(historyDTO.getBhyt());
        history.setAddress(historyDTO.getAddress());
        history.setName(historyDTO.getFullName());
        history.setFromDate(ConvertTimeUtils.stringToDate(historyDTO.getFromDate()));
        history.setMedicalSummary(historyDTO.getMedicalSummary());
        history.setToDate(ConvertTimeUtils.stringToDate(historyDTO.getToDate()));
        history.setNation(historyDTO.getNation());
        history.setBookingId(historyDTO.getBookingId());
        history.setMedicine(historyDTO.getMedicineStr());
        history.setTotalMoney(historyDTO.getTotalMoney());
        return history;
    }
}

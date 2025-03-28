package com.n7.service.impl;

import com.n7.entity.History;
import com.n7.repository.HistoryRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HistoryService {
    private final HistoryRepo historyRepo;

    public List<History> getAllHistory() {
        return null;
    }

    public History saveHistory(History history) {
        return historyRepo.save(history);
    }

    public void deleteHistory(History history) {
        historyRepo.deleteById(history.getId());
    }
}

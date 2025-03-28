package com.n7.controller;

import com.n7.entity.History;
import com.n7.service.impl.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class HistoryController {
    @Autowired
    private HistoryService historyService;

    @GetMapping("/history/list")
    public ResponseEntity<List<History>> getHistory() {
        return ResponseEntity.ok(historyService.getAllHistory());
    }

    @PostMapping("/history")
    public ResponseEntity<History> saveHistory(@RequestBody History history) {
        try {
            historyService.saveHistory(history);
            return ResponseEntity.ok(history);
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping
    public ResponseEntity<History> deleteHistory(@RequestBody History history) {
        try {
            return null;
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }

    }
}

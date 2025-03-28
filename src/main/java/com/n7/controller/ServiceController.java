package com.n7.controller;

import com.n7.entity.Service;
import com.n7.service.impl.ServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/private/")
public class ServiceController {
    private final ServiceService serviceService;

    @GetMapping("list")
    public List<Service> getAllServices() {
        return serviceService.findAll();
    }

    @GetMapping("service/{id}")
    public ResponseEntity<Service> getServiceById(@PathVariable Long id) {
        return ResponseEntity.ok(serviceService.findById(id));
    }


    @PostMapping("/service")
    public ResponseEntity<?> addService(@RequestBody Service service) {
        try {
            return ResponseEntity.ok(serviceService.save(service));
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Error when saving service");
        }
    }
}

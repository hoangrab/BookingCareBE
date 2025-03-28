package com.n7.service.impl;

import com.n7.repository.ServiceRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceService {
    private final ServiceRepo serviceRepo;

    public List<com.n7.entity.Service> findAll() {
        return serviceRepo.findAll();
    }

    public com.n7.entity.Service findById(Long id) {
        return serviceRepo.findById(id).get();
    }

    public com.n7.entity.Service save(com.n7.entity.Service service) {
        return serviceRepo.save(service);
    }
}

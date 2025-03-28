package com.n7.service.impl;

import com.n7.entity.Medicine;
import com.n7.repository.MedicineRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicineService {
    private final MedicineRepo medicineRepo;

    public List<Medicine> getAllMedicine() {
        return medicineRepo.findAll();
    }
    public Medicine getMedicineById(Long id) {
        return medicineRepo.findById(id).get();
    }



}

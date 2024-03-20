package com.n7.service.impl;

import com.n7.dto.MajorDTO;
import com.n7.entity.Major;
import com.n7.model.MajorModel;
import com.n7.repository.MajorRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MajorService {
    private MajorRepo majorRepo;
    public List<MajorModel> getAll(){
        return majorRepo.findAll().stream().map(this::convertEntityToModel).collect(Collectors.toList());
    }

    public boolean findById(long id) {
        return majorRepo.findById(id) != null;
    }
    public boolean checkNameMajor(String name) {
        return majorRepo.findByName(name)!=null;
    }

    @Transactional
    public boolean saveMajor(MajorDTO majorDTO,Long id,String image,String idImage) {
        Major ma = new Major();
        if(id!=null) ma.setId(id);
        convertDTOtoEntity(majorDTO,ma);
        if(idImage != null && image != null) {
            ma.setIdImage(idImage);
            ma.setAvatar(image);
        }
        Major major = majorRepo.save(ma);
        return major != null;
    }

    @Transactional
    public void deleteMajor(Long id) {
        majorRepo.deleteById(id);
    }

    private void convertDTOtoEntity(MajorDTO majorDTO, Major major) {
        major.setId(majorDTO.getId());
        major.setName(majorDTO.getName());
        major.setDescription(majorDTO.getDescription());
    }

    public MajorModel convertEntityToModel(Major major) {
        MajorModel model = new MajorModel();
        model.setId(major.getId());
        model.setName(major.getName());
        model.setDescription(major.getDescription());
        model.setImage(major.getAvatar());
        return model;
    }
}

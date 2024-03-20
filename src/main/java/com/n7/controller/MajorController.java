package com.n7.controller;

import com.n7.dto.MajorDTO;
import com.n7.model.MajorModel;
import com.n7.response.ErrorResponse;
import com.n7.response.SuccessResponse;
import com.n7.service.impl.CloudinaryService;
import com.n7.service.impl.MajorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class MajorController {
    private CloudinaryService cloudinaryService;
    private MajorService majorService;
    @PostMapping(value = "creat",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> createMajor(@RequestPart("file") MultipartFile multipartFile,
                                        @Valid @RequestBody MajorDTO majorDTO) {
        try {
            if(!majorService.checkNameMajor(majorDTO.getName())){
                return ResponseEntity.badRequest().body(new ErrorResponse<>("Tên khoa đã tồn tại"));
            }
            Map data = cloudinaryService.upload(multipartFile);
            majorService.saveMajor(majorDTO,data.get("url").toString(),data.get("id_url").toString());
            return ResponseEntity.ok().body(new SuccessResponse<>("Đã tạo thành công"));
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorResponse<>(e.getMessage()));
        }
    }

    @PutMapping(value = "update",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> updateMajor(@RequestPart(value = "file",required = false) MultipartFile multipartFile,
                                        @Valid @RequestBody MajorDTO majorDTO) {
        try {
            String image = null, idImage = null;
            if(!majorService.findById(majorDTO.getId())) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse<>("Khoa không tồn tại!!!"));
            }
            if(multipartFile!=null){
                Map data = cloudinaryService.upload(multipartFile);
                image = data.get("url").toString();
                idImage = data.get("id_url").toString();
            }
            majorService.saveMajor(majorDTO,image,idImage);
            return ResponseEntity.ok().body(new SuccessResponse<>("Đã cập nhật thành công"));
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(value = "majors")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> getAllMajor() {
        List<MajorModel> list = new ArrayList<>();
        list = majorService.getAll();
        return ResponseEntity.ok(new SuccessResponse<>("Get success",list));
    }
}

package com.n7.service.impl;

import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryService {
    @Autowired
    private Cloudinary cloudinary;

    public Map upload(MultipartFile multipartFile) throws IOException {
        Map data = this.cloudinary.uploader().upload(multipartFile.getBytes(),Map.of());
        return data;
    }

    public Map delete(String id) throws IOException {
        Map data = this.cloudinary.uploader().destroy(id,Map.of());
        return data;
    }
}

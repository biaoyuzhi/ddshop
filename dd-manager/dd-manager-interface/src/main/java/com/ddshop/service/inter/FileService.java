package com.ddshop.service.inter;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface FileService {
    Map<String,Object> uploadImage(MultipartFile upfile);
}

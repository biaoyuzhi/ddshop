package com.ddshop.service.impl;

import com.ddshop.service.inter.FileService;
import com.ddshop.utils.FtpUtils;
import com.ddshop.utils.IDUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
@Service
public class FileServiceImpl implements FileService{
            /*"state": "SUCCESS",
            "title": "1516284309513001740.jpg",
            "original": "1.jpg",
            "type": ".jpg",
            "url": "/ueditor/jsp/upload/image/2018/01/18/1516284309513001740.jpg",
            "size": "52870"*/
    @Override
    public Map<String, Object> uploadImage(MultipartFile upfile) {
        HashMap<String, Object> map = new HashMap<>();
        try {
            String name = upfile.getOriginalFilename();
            String type = name.substring(name.lastIndexOf("."));
            String name1 = IDUtils.genImageName();
            String s = new DateTime().toString("yyyy/MM/dd");
            boolean b = FtpUtils.uploadFile("120.79.90.102", 21, "ftpuser",
                    "biaoyuzhi@163.com", "/home/ftpuser/www/images",
                    s, name1+type, upfile.getInputStream());
            if (b){
                map.put("state","SUCCESS");
                map.put("title",name1+type);
                map.put("original",name);
                map.put("type",type);
                map.put("url","http://120.79.90.102:81/images/"+s+"/"+name1+type);
                map.put("size",upfile.getSize());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return map;
    }
}

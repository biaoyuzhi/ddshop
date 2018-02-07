package com.ddshop.service.inter;

import com.ddshop.pojo.po.TbContent;

import java.util.List;

public interface ContentService {
    List<TbContent> getContentListByCid(Long cid);
}

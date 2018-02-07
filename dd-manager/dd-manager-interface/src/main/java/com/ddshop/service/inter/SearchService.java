package com.ddshop.service.inter;

import com.ddshop.pojo.dto.TbSearchItemResult;

public interface SearchService {
    public TbSearchItemResult search(String keyword,Integer page,int i);
}

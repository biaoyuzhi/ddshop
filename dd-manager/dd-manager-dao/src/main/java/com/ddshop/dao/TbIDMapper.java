package com.ddshop.dao;

import com.ddshop.pojo.po.TbItemDesc;

public interface TbIDMapper {
    public TbItemDesc findByItemId(Long itemId);
}

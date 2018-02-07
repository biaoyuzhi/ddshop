package com.ddshop.service.impl;

import com.ddshop.dao.TbAAMapper;
import com.ddshop.dao.TbItemMapper;
import com.ddshop.dto.BootPage;
import com.ddshop.dto.Result;
import com.ddshop.pojo.po.TbItem;
import com.ddshop.pojo.vo.TbII;
import com.ddshop.service.inter.BootTbItemInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/*
使用bootstrap框架的后端分页查询和全查询
 */
@Service
public class BootTbItemService implements BootTbItemInterface{
    @Autowired
    private TbAAMapper tbaaDao;
    @Autowired
    private TbItemMapper itemDao;
    @Override
    public Result<TbII> listItemsByPage(BootPage page) {
        Result<TbII> result = new Result<>();
        List<TbII> list = tbaaDao.selectByBootPage(page);
        Long count = Long.valueOf(itemDao.countByExample(null));
        result.setRows(list);
        result.setTotal(count);
        return result;
    }

    @Override
    public List<TbItem> listItems() {
        List<TbItem> list = itemDao.selectByExample(null);
        return list;
    }
}

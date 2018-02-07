package com.ddshop.service.inter;

import com.ddshop.dto.BootPage;
import com.ddshop.dto.Result;
import com.ddshop.pojo.po.TbItem;
import com.ddshop.pojo.vo.TbII;

import java.util.List;

public interface BootTbItemInterface {
    Result<TbII> listItemsByPage(BootPage page);

    List<TbItem> listItems();
}

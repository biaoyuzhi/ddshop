package com.ddshop.dao;

import com.ddshop.dto.BootPage;
import com.ddshop.dto.Order;
import com.ddshop.dto.Page;
import com.ddshop.pojo.dto.TbSearchItemCustom;
import com.ddshop.pojo.po.TbItemParam;
import com.ddshop.pojo.vo.ItemSearch;
import com.ddshop.pojo.vo.TbII;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TbAAMapper {
    Long countTbitem(@Param("itemSearch")ItemSearch itemSearch);

    List<TbII> selectByPage(@Param("page") Page page, @Param("order") Order order, @Param("itemSearch")ItemSearch itemSearch);

    List<TbII> selectByBootPage(BootPage page);

    TbItemParam findItemParamData(Long catId);

    List<TbSearchItemCustom> listSearchItems();

    TbSearchItemCustom getItemCustomById(long itemId);
}

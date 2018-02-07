package com.ddshop.service.inter;

import com.ddshop.dto.Order;
import com.ddshop.dto.Page;
import com.ddshop.dto.Result;
import com.ddshop.dto.TreeNode;
import com.ddshop.pojo.po.TbContent;
import com.ddshop.pojo.po.TbItem;
import com.ddshop.pojo.po.TbItemParam;
import com.ddshop.pojo.vo.ItemSearch;
import com.ddshop.pojo.vo.TbII;

import java.util.List;

public interface TbContentInterface {

    public TbContent selectByPrimaryKey(Long id) throws Exception;

    public List<TbItem> listItems();

    Result<TbII> listItemsByPage(Page page, Order order, ItemSearch itemSearch);

    int batchUpdate(List<Long> ids, byte id);

    List<TreeNode> itemCatByParentId(Long parentId);

    Long addItemDescAndCat(TbItem item, String itemDesc);

    TbItemParam findItemParamData(Long catId);

    boolean importItemIntoSolr();
}

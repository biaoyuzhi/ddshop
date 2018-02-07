package com.ddshop.pojo.dto;

import java.util.List;

/**
 * User: DHC
 * Date: 2018/1/24
 * Time: 15:02
 * Version:V1.0
 */
public class TbSearchItemResult {

    private long recordCount;//总记录数
    private int totalPages;//总页数
    private List<TbSearchItemCustom> itemList;//每页显示的数据集合

    public long getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(long recordCount) {
        this.recordCount = recordCount;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public List<TbSearchItemCustom> getItemList() {
        return itemList;
    }

    public void setItemList(List<TbSearchItemCustom> itemList) {
        this.itemList = itemList;
    }
}

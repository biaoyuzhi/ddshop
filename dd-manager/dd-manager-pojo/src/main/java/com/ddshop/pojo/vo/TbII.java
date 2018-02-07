package com.ddshop.pojo.vo;

import com.ddshop.pojo.po.TbItem;

public class TbII extends TbItem{
    private String priceName;
    private String statusName;

    public String getPriceName() {
        return priceName;
    }

    public void setPriceName(String priceName) {
        this.priceName = priceName;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
}

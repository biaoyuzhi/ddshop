package com.ddshop.dto;

public class Page {

    private Integer page;
    private Integer rows;
    public Integer getOffset(){
        return (page-1)*rows;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }
}

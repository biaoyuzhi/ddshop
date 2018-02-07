package com.ddshop.search.web;

import com.ddshop.pojo.dto.TbSearchItemResult;
import com.ddshop.service.inter.SearchService;
import com.ddshop.utils.StrKit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class SearchIndexAction {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SearchService searchService;

    //搜索页面的展示和索引库的搜索操作
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String index(String keyword, @RequestParam(defaultValue = "1")Integer page, Model model){
        try {
            if (StrKit.notBlank(keyword)){
                //调用业务逻辑层的方法进行分页查询
                TbSearchItemResult result = searchService.search(keyword,page,50);
                //用于回显的数据
                model.addAttribute("query",keyword);
                model.addAttribute("totalPages",result.getTotalPages());
                model.addAttribute("recordCount",result.getRecordCount());
                model.addAttribute("itemList",result.getItemList());
                model.addAttribute("page",page);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
        return "search";
    }
}

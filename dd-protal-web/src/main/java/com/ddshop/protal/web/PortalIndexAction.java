package com.ddshop.protal.web;


import com.ddshop.pojo.po.TbContent;
import com.ddshop.service.inter.ContentService;
import com.ddshop.utils.PropKit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * User: DHC
 * Date: 2018/1/19
 * Time: 15:24
 * Version:V1.0
 */
@Controller
public class PortalIndexAction {

    @Autowired
    private ContentService contentService;

    //门户网页上从Redis集群获取轮播图的实现
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String index(Model model){
        Long cid = PropKit.use("picture.properties").getLong("categoryId");
        List<TbContent> list =  contentService.getContentListByCid(cid);
        model.addAttribute("ad1List",list);
        return "index";
    }
}

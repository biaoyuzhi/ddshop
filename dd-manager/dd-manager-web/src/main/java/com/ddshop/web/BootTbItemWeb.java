package com.ddshop.web;

import com.ddshop.dto.BootPage;
import com.ddshop.dto.Result;
import com.ddshop.pojo.vo.TbII;
import com.ddshop.service.inter.BootTbItemInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
/*
使用bootstrap的分页查询
 */
@Controller
public class BootTbItemWeb {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private BootTbItemInterface ser;

   /* @RequestMapping(value = "/{page}",method = RequestMethod.GET)
    public String page(@PathVariable("page") String page){
        return page;
    }
    @RequestMapping(value = "/item/{itemId}",method = RequestMethod.GET)
    @ResponseBody
    TbContent doFind(@PathVariable Long itemId) throws Exception {
        TbContent tbContent = ser.selectByPrimaryKey(itemId);
        return tbContent;
    }*/

    /*@ResponseBody
    @RequestMapping("item/beach/{id}")
    public int beachByIds(@RequestParam("ids[]") List<Long> ids,@PathVariable byte id){
        int i = 0;
        i = ser.batchUpdate(ids,id);
        return i;
    }*/
   /* @ResponseBody
    @RequestMapping("/boot-items2")
    public List<TbItem> listTbItem(){
        List<TbItem> list = ser.listItems();
        return list;
    }*/
    @ResponseBody
    @RequestMapping("/boot-items")
    public Result<TbII> listItems(BootPage page) {
        Result<TbII> result = null;
        try {
            result = ser.listItemsByPage(page);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return result;
    }
}

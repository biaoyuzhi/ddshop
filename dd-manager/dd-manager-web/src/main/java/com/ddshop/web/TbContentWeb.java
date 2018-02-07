package com.ddshop.web;

import com.ddshop.dto.*;
import com.ddshop.pojo.po.TbContent;
import com.ddshop.pojo.po.TbItem;
import com.ddshop.pojo.po.TbItemParam;
import com.ddshop.pojo.vo.ItemSearch;
import com.ddshop.pojo.vo.TbII;
import com.ddshop.service.inter.TbContentInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.jms.*;
import java.util.List;

@Controller
public class TbContentWeb {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private TbContentInterface ser;
    @Autowired
    private JmsTemplate jmsTemplate;
    //通过名称注入，而不是属性注入。ActiveMQ中的目标主题
    @Resource
    private Destination topicDestination;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String indexLayUI() {
        return "index";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "index2";
    }
    //达到前台输入jsp文件名就能返回相应页面的效果
    @RequestMapping(value = "/{page}",method = RequestMethod.GET)
    public String page(@PathVariable("page") String page){
        return page;
    }
    @RequestMapping(value = "/item/{itemId}",method = RequestMethod.GET)
    @ResponseBody
    TbContent doFind(@PathVariable Long itemId) throws Exception {
        TbContent tbContent = ser.selectByPrimaryKey(itemId);
        return tbContent;
    }

    //分页模糊查询
    @ResponseBody
    @RequestMapping("/items")
    public Result<TbII> listItems(Page page, Order order,ItemSearch itemSearch) {
        Result<TbII> result = null;
        try {
            result = ser.listItemsByPage(page,order,itemSearch);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return result;
    }

    //数据上架、下架、删除的状态修改
    @ResponseBody
    @RequestMapping("item/beach/{id}")
    public int beachByIds(@RequestParam("ids[]") List<Long> ids,@PathVariable byte id){
        int i = 0;
        i = ser.batchUpdate(ids,id);
        return i;
    }

    //分类树的展示
    @ResponseBody
    @RequestMapping("itemCat/{parentId}")
    public List<TreeNode> itemCatByParentId(@PathVariable("parentId") Long parentId){
        List<TreeNode> list = ser.itemCatByParentId(parentId);
        return list;
    }

    //添加页面中商品数据的添加保存操作+ActiveMQ的主题发布
    @ResponseBody
    @RequestMapping(value = "/item", method = RequestMethod.POST)
    public int addItemDescAndCat(TbItem item ,String itemDesc,String paramData){
        int i = 0;
        try {
            //希望数据保存进数据库后将该数据的id发送进ActiveMQ队列主题
            final Long itemId = ser.addItemDescAndCat(item,itemDesc);
            if (itemId != null) {
                jmsTemplate.send(topicDestination, new MessageCreator() {
                    @Override
                    public Message createMessage(Session session) throws JMSException {
                        TextMessage message = session.createTextMessage(itemId + "");
                        return message;
                    }
                });
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }

    //类目参数数据的展示
    @ResponseBody
    @RequestMapping(value = "/itemParam",method = RequestMethod.POST)
    public TbItemParam findItemParamData(Long catId){
        TbItemParam tbItemParam = ser.findItemParamData(catId);
        return tbItemParam;
    }

    @ResponseBody
    @RequestMapping("itemParam/{cid}")
    public int insertItemParamData(@PathVariable("cid")Long cid, String paramData){
        System.out.print(paramData);
        return 0;
    }

    //一键导入索引库的实现
    @ResponseBody
    @RequestMapping(value = "item/index",method = RequestMethod.POST)
    public MessageResult itemIntoSolr(){
        boolean bl = ser.importItemIntoSolr();
        MessageResult result = new MessageResult();
        result.setSuccess(false);
        if (bl){
            result.setSuccess(true);
            result.setMessage("恭喜，导入成功！");
        }
        return result;
    }
}

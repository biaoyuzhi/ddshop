package com.ddshop.item.web;

import com.ddshop.dao.TbAAMapper;
import com.ddshop.dao.TbIDMapper;
import com.ddshop.pojo.dto.TbSearchItemCustom;
import com.ddshop.pojo.po.TbItemDesc;
import freemarker.core.ParseException;
import freemarker.template.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfig;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ItemFTLListener implements MessageListener{
    @Autowired
    private TbAAMapper tbAADao;
    @Autowired
    private TbIDMapper tbIDDao;
    @Autowired
    private FreeMarkerConfig freeMarkerConfig;
    @Override
    public void onMessage(Message message) {
        try {
            //获取消息
            TextMessage textMessage = (TextMessage)message;
            String text = textMessage.getText();
            //获取商品ID
            long itemId = Long.parseLong(text);
            //调用DAO层方法通过商品ID查询出该商品
            TbSearchItemCustom item = tbAADao.getItemCustomById(itemId);
            TbItemDesc itemDesc = tbIDDao.findByItemId(itemId);
            //创建一个数据集，把商品数据封装
            Map<String,Object> dataModel = new HashMap<String,Object>();
            dataModel.put("item",item);
            dataModel.put("itemDesc",itemDesc);
            //加载模板
            Configuration configuration = freeMarkerConfig.getConfiguration();
            Template template = configuration.getTemplate("item.ftl");
            //创建一个输出流，指定输出的目录及文件名
            FileWriter out = new FileWriter("E:/ftl/" + itemId + ".html");
            template.process(dataModel,out);
            out.close();
        } catch (JMSException e) {
            e.printStackTrace();
        } catch (MalformedTemplateNameException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (TemplateNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }

    }
}

package com.ddshop.search.web;

import com.ddshop.dao.TbAAMapper;
import com.ddshop.pojo.dto.TbSearchItemCustom;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * User: DHC
 * Date: 2018/1/26
 * Time: 15:00
 * Version:V1.0
 */
public class ItemAddMessageListener implements MessageListener {

    @Autowired
    private TbAAMapper tbAADao;
    @Autowired
    private SolrServer solrServer;

    @Override
    public void onMessage(Message message) {
        try {
            //获取消息
            TextMessage textMessage = (TextMessage)message;
            String text = textMessage.getText();
            //获取商品ID
            long itemId = Long.parseLong(text);
            //调用DAO层方法通过商品ID查询出该商品
            TbSearchItemCustom itemCustom = tbAADao.getItemCustomById(itemId);
            //TbSearchItemCustom--Document
            SolrInputDocument document = new SolrInputDocument();
            document.addField("id",itemCustom.getId());
            document.addField("item_title",itemCustom.getTitle());
            document.addField("item_sell_point",itemCustom.getSellPoint());
            document.addField("item_price",itemCustom.getPrice());
            document.addField("item_image",itemCustom.getImage());
            document.addField("item_category_name",itemCustom.getCatName());
            //写入索引库
            solrServer.add(document);
            //提交
            solrServer.commit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

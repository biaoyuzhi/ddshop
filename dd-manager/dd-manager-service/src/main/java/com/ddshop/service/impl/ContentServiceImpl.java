package com.ddshop.service.impl;

import com.ddshop.dao.TbContentMapper;
import com.ddshop.jedis.JedisClient;
import com.ddshop.pojo.po.TbContent;
import com.ddshop.pojo.po.TbContentExample;
import com.ddshop.service.inter.ContentService;
import com.ddshop.utils.JsonUtils;
import com.ddshop.utils.StrKit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ContentServiceImpl implements ContentService{
    @Autowired
    private JedisClient jedisClient;
    @Autowired
    private TbContentMapper contentDao;
    @Override
    public List<TbContent> getContentListByCid(Long cid) {
        //1 在缓存服务器中进行查询
        try {
            String listJson = jedisClient.hget("CONTENT_LIST", Long.toString(cid));
            if (StrKit.notBlank(listJson)){
                List<TbContent> contentList = JsonUtils.jsonToList(listJson, TbContent.class);
                return contentList;
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        //2 主业务：去mysql中查询
        TbContentExample example = new TbContentExample();
        TbContentExample.Criteria criteria = example.createCriteria();
        criteria.andCategoryIdEqualTo(cid);
        List<TbContent> contentList = contentDao.selectByExample(example);
        //3 将查询到的数据存放到缓存服务器中
        try {
            jedisClient.hset("CONTENT_LIST",Long.toString(cid),JsonUtils.objectToJson(contentList));
        }catch (Exception e) {
            e.printStackTrace();
        }
        return contentList;
    }
}

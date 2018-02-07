package com.ddshop.service.impl;

import com.ddshop.dao.*;
import com.ddshop.dto.Order;
import com.ddshop.dto.Page;
import com.ddshop.dto.Result;
import com.ddshop.dto.TreeNode;
import com.ddshop.pojo.dto.TbSearchItemCustom;
import com.ddshop.pojo.po.*;
import com.ddshop.pojo.vo.ItemSearch;
import com.ddshop.pojo.vo.TbII;
import com.ddshop.service.inter.TbContentInterface;
import com.ddshop.utils.IDUtils;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
@Transactional
public class TbContentService implements TbContentInterface {

    @Autowired
    private TbContentMapper tbcm;
    @Autowired
    private TbItemCatMapper tbItemCatDao;
    @Autowired
    private TbItemMapper itemDao;
    @Autowired
    private TbAAMapper tbaaDao;
    @Autowired
    private TbItemDescMapper tbItemDescDao;
    @Override
    @Transactional(readOnly = true)
    public TbContent selectByPrimaryKey(Long id) throws Exception {
        return tbcm.selectByPrimaryKey(id);
    }
    //    查出所有的TbItems
    @Override
    public List<TbItem> listItems() {
        return  itemDao.selectByExample(null);
    }
//     分页查询TbItems,模糊查询
    @Override
    public Result<TbII> listItemsByPage(Page page, Order order, ItemSearch itemSearch) {
        Result<TbII> result = new Result<>();
        List<TbII> list = tbaaDao.selectByPage(page,order,itemSearch);
        for (int i=0;i<list.size();i++){
            String priceName = list.get(i).getPriceName();
            list.get(i).setPriceName("￥:"+priceName);
        }
        Long count = Long.valueOf(tbaaDao.countTbitem(itemSearch));
        result.setRows(list);
        result.setTotal(count);
        return result;
    }
//      实现状态的修改
    @Override
    public int batchUpdate(List<Long> ids, byte id) {
        int i = 0;
        try {
            TbItem tbItem = new TbItem();
            tbItem.setStatus((byte) id);

            TbItemExample example = new TbItemExample();
            TbItemExample.Criteria criteria = example.createCriteria();
            criteria.andIdIn(ids);

            i = itemDao.updateByExampleSelective(tbItem, example);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }

    @Override
    public List<TreeNode> itemCatByParentId(Long parentId) {
        List<TreeNode> list = new ArrayList<TreeNode>();
            TbItemCatExample example = new TbItemCatExample();
            TbItemCatExample.Criteria criteria = example.createCriteria();
            criteria.andParentIdEqualTo(parentId);
            List<TbItemCat> tbItemCats = tbItemCatDao.selectByExample(example);
            for (TbItemCat cat:tbItemCats) {
                TreeNode tn = new TreeNode();
                tn.setId(cat.getId());
                tn.setText(cat.getName());
                tn.setState(cat.getIsParent()?"closed":"open");
                list.add(tn);
            }
        return list;
    }

    @Override
    public Long addItemDescAndCat(TbItem item, String itemDesc) {
        Long id = null;
        try {
            id = IDUtils.getItemId();
            item.setId(id);
            item.setStatus((byte)1);
            item.setCreated(new Date());
            item.setUpdated(new Date());
            int i = itemDao.insertSelective(item);

            TbItemDesc desc = new TbItemDesc();
            desc.setItemId(id);
            desc.setItemDesc(itemDesc);
            desc.setCreated(new Date());
            desc.setUpdated(new Date());
            i += tbItemDescDao.insert(desc);
            if (i<1){return null;}
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public TbItemParam findItemParamData(Long catId) {
        return tbaaDao.findItemParamData(catId);
    }

    @Autowired
    private SolrServer solrServer;
    @Override
    public boolean importItemIntoSolr() {
        //采集数据
        List<TbSearchItemCustom> list = tbaaDao.listSearchItems();
        //创建索引库
        for (TbSearchItemCustom tbItem:list) {
            SolrInputDocument document = new SolrInputDocument();
            document.addField("id", tbItem.getId());
            document.addField("item_title", tbItem.getTitle());
            document.addField("item_sell_point", tbItem.getSellPoint());
            document.addField("item_price", tbItem.getPrice());
            document.addField("item_image", tbItem.getImage());
            document.addField("item_category_name", tbItem.getCatName());
            try {
                solrServer.add(document);
            } catch (SolrServerException e) {
                e.printStackTrace();
                return false;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
            try {
                solrServer.commit();
            } catch (SolrServerException e) {
                e.printStackTrace();
                return false;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        return true;
    }
}

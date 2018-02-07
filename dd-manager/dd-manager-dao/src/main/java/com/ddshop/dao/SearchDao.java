package com.ddshop.dao;

import com.ddshop.pojo.dto.TbSearchItemCustom;
import com.ddshop.pojo.dto.TbSearchItemResult;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class SearchDao {
    @Autowired
    private SolrServer solrServer;

    public TbSearchItemResult search(SolrQuery query) {
        TbSearchItemResult result = new TbSearchItemResult();
        try {
            //获取查询响应，该响应属性在solr查询中包含
            QueryResponse response = solrServer.query(query);
            SolrDocumentList documentList = response.getResults();
            long numFound = documentList.getNumFound();
            //封装result的第一个属性
            result.setRecordCount(numFound);
            //获取高亮的列表
            Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
            List<TbSearchItemCustom> searchItemCustomsList = new ArrayList<>();
            //遍历documentList得到List<TbSearchItemCustom>
            for (SolrDocument document:documentList) {
                TbSearchItemCustom item = new TbSearchItemCustom();
                item.setId((String) document.get("id"));
                item.setCatName((String) document.get("item_category_name"));
                item.setImage((String) document.get("item_image"));
                item.setPrice((long) document.get("item_price"));
                item.setSellPoint((String) document.get("item_sell_point"));
                //获取高亮列表的值
                Object id = document.get("id");
                Map<String, List<String>> map = highlighting.get(id);
                List<String> list = map.get("item_title");
                String title = "";
                if (list != null&&!list.isEmpty()){
                    title = list.get(0);
                }else {
                    title = (String) document.get("item_title");
                }
                item.setTitle(title);
                searchItemCustomsList.add(item);
            }
            //封装result的第二个属性
            result.setItemList(searchItemCustomsList);
        } catch (SolrServerException e) {
            e.printStackTrace();
        }
        return result;
    }
}

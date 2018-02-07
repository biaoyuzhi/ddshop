package com.ddshop.service.impl;

import com.ddshop.pojo.dto.TbSearchItemResult;
import com.ddshop.dao.SearchDao;
import com.ddshop.service.inter.SearchService;
import org.apache.solr.client.solrj.SolrQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchServiceImpl implements SearchService {
    @Autowired
    private SearchDao searchDao;
    @Override
    public TbSearchItemResult search(String keyword, Integer page, int i) {
        TbSearchItemResult result = null;
        try {
            //创建solr查询对象
            SolrQuery query = new SolrQuery();
            query.setQuery(keyword);
            if (page<=0){page=1;}
            query.setStart((page-1)*i);
            query.setRows(i);
            //设置搜索域df
            query.set("df","item_keywords");
            //设置高亮
            query.setHighlight(true);
            //设置高亮的属性展示段
            query.addHighlightField("item_title");
            query.setHighlightSimplePre("<em style='color:red'>");
            query.setHighlightSimplePost("</em>");
            //通过封装的query条件去DAO层执行查询方法，注意，其实该查询实施并不涉及db数据库的操作，只是习惯性的将操作放到DAO层执行而已。
            result = searchDao.search(query);
            //计算总页数，设置进result，其他属性在DAO层封装
            long count = result.getRecordCount();
            int totalPages = ((int) count + i - 1) / i;
            result.setTotalPages(totalPages);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}

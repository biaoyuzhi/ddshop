package com.ddshop.service.impl;

import com.ddshop.dao.TbUserMapper;
import com.ddshop.dto.MessageResult;
import com.ddshop.jedis.JedisClient;
import com.ddshop.pojo.po.TbUser;
import com.ddshop.pojo.po.TbUserExample;
import com.ddshop.service.inter.LoginService;
import com.ddshop.utils.JsonUtils;
import com.ddshop.utils.StrKit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.List;

@Service
public class LoginServiceImpl implements LoginService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private TbUserMapper tbUserDao;
    @Autowired
    private JedisClient JedisClient;
    @Override
    @Transactional
    public MessageResult userLogin(String username, String password) {
        TbUser user = new TbUser();
        MessageResult result = null;
        try {
            //创建模板
            TbUserExample example = new TbUserExample();
            TbUserExample.Criteria criteria = example.createCriteria();
            criteria.andUsernameEqualTo(username);
            //根据模板找到用户名为username的List<TbUser>
            List<TbUser> list = tbUserDao.selectByExample(example);
            result = new MessageResult();
            if (list==null || list.isEmpty()){
                result.setSuccess(false);
                result.setMessage("用户名不存在！");
                return result;
            }
            user = list.get(0);
            //将前端获取的密码加密，再跟数据库中的密码对比
            String digest = DigestUtils.md5DigestAsHex(password.getBytes());
            if (!digest.equals(user.getPassword())){
                result.setSuccess(false);
                result.setMessage("用户名或密码错误！");
            }
            //使用StrKit工具类生成一个uuid作为令牌
            String token = StrKit.uuid();
            //将密码置空，为了安全。再将对象转成JSON形式存进Redis集群
            user.setPassword(null);
            JedisClient.set("DDTOKEN:", JsonUtils.objectToJson(user));
            JedisClient.expire("DDTOKEN:",1800);
            result.setSuccess(true);
            result.setMessage("登录成功！");
            result.setData(token);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
        return result;
    }
}

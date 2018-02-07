package com.ddshop.service.impl;

import com.ddshop.dto.MessageResult;
import com.ddshop.jedis.JedisClient;
import com.ddshop.pojo.po.TbUser;
import com.ddshop.service.inter.TokenService;
import com.ddshop.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenServiceImpl implements TokenService {
    @Autowired
    private JedisClient jedisClient;
    @Override
    public MessageResult getUserByToken(String token) {
        MessageResult result = new MessageResult();
        String value = jedisClient.get("DDTOKEN:" + token);
        if (value==null){
            result.setSuccess(false);
            result.setMessage("用户失效，请重新登录!");
            return  result;
        }
        jedisClient.expire("DDTOKEN:" + token,1800);
        TbUser user = JsonUtils.jsonToPojo(value,TbUser.class);
        result.setSuccess(true);
        result.setMessage("登录成功！");
        result.setData(user);
        return result;
    }
}

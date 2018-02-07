package com.ddshop.sso.web;

import com.ddshop.dto.MessageResult;
import com.ddshop.jedis.JedisClient;
import com.ddshop.service.inter.TokenService;
import com.ddshop.utils.CookieUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * User: DHC
 * Date: 2018/2/2
 * Time: 15:00
 * Version:V1.0
 */
@Controller
public class TokenAction {

    @Autowired
    private TokenService tokenService;
    @Autowired
    private JedisClient jedisClient;

    @ResponseBody
    @RequestMapping(value = "/login/{token}",method = RequestMethod.GET)
    public Object getUserByToken(@PathVariable("token") String token, String callback, HttpServletRequest request, HttpServletResponse response){
        MessageResult mr = tokenService.getUserByToken(token);
        if(mr.getData()==null){
            CookieUtils.setCookie(request,response,"ddtoken",null);
        }
        //判断是否为jsonp调用,有callback函数说明是jsonp调用，返回jsonp格式。如果是直接的地址访问，则不是jsonp调用，返回普通json格式
        if (StringUtils.isBlank(callback)){
            return mr;
        }else {
            MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(mr);
            mappingJacksonValue.setJsonpFunction(callback);
            return mappingJacksonValue;
        }
    }

    @ResponseBody
    @RequestMapping(value = "/logout/{token}",method = RequestMethod.GET)
    public Object logout(@PathVariable("token") String token,String callback,HttpServletRequest request,HttpServletResponse response){
        MessageResult mr = new MessageResult();
        jedisClient.expire("DDTOKEN:"+token,0);
        CookieUtils.setCookie(request,response,"ddtoken",null);
        mr.setSuccess(false);
        mr.setMessage("退出成功!");
        if (!StringUtils.isBlank(callback)){
           MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(mr);
           mappingJacksonValue.setJsonpFunction(callback);
           return mappingJacksonValue;
        }
        return mr;
    }
}

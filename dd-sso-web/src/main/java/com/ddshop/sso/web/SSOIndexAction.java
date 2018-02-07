package com.ddshop.sso.web;

import com.ddshop.dto.MessageResult;
import com.ddshop.service.inter.LoginService;
import com.ddshop.utils.CookieUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class SSOIndexAction {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private LoginService loginService;
    @Autowired

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String index() {
        return "login";
    }

    @RequestMapping(value = "/user/login",method = RequestMethod.POST)
    @ResponseBody
    public MessageResult login(String username, String password, HttpServletRequest request, HttpServletResponse response){
        MessageResult mr = null;
        try {
            //1 调用业务逻辑层方法进行登录
            mr = loginService.userLogin(username,password);
            if(mr.isSuccess()){
                //登录成功进入这里
                String token = mr.getData().toString();
                //2 如果登录成功 写入cookies
                //存放到redis集群中的是key（TT_TOKEN:22222222）和value（{xxxx:yyy,xxxx}）
                //存放到cookie中是token（uuid）
                CookieUtils.setCookie(request,response,"ddtoken",token);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return mr;
    }

}

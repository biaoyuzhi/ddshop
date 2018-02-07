/*
package com.ddshop.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Assert;
import org.junit.Test;

public class ShiroTest {

    @Test
    public void testShiro(){
        IniSecurityManagerFactory factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("wang", "123");
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            System.err.println(e);
        }
        Assert.assertEquals(true,subject.isAuthenticated());
        subject.logout();
    }

    @Test
    public void testRealm(){
        IniSecurityManagerFactory factory = new IniSecurityManagerFactory("classpath:shiro-realm.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            System.err.println(e);
        }
        Assert.assertEquals(true,subject.isAuthenticated());
        subject.logout();
    }

    @Test
    public void testRole(){
        IniSecurityManagerFactory factory = new IniSecurityManagerFactory("classpath:shiro-role.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");
        try {
            subject.login(token);
            Assert.assertTrue(subject.hasRole("role1"));
        } catch (AuthenticationException e) {
            System.err.println("认证失败");
        }catch (AssertionError e){
            System.err.println("权限出错");
        }
        subject.logout();
    }

    @Test
    public void testJdbcRealm(){
        IniSecurityManagerFactory factory = new IniSecurityManagerFactory("classpath:shiro-jdbc-realm.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");
        try {
            subject.login(token);
            Assert.assertTrue(subject.hasRole("role1"));
        } catch (AuthenticationException e) {
            System.err.println("认证失败!");
        }catch (AssertionError e){
            System.err.println("权限出错!");
        }
        subject.logout();
    }
}
*/

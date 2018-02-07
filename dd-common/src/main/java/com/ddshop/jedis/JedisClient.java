package com.ddshop.jedis;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 用于封装redis常见类型的命令（string，hash，list）
 * User: DHC
 * Date: 2018/1/19
 * Time: 10:05
 * Version:V1.0
 */
public interface JedisClient {
    //String类型常用命令
    String set(String key, String value);
    String get(String key);
    Boolean exists(String key);
    Long expire(String key, int seconds);
    Long ttl(String key);
    Long incr(String key);
    Long del(String key);
    //Hash类型常用命令
    Long hset(String key, String field, String value);
    String hget(String key, String field);
    Long hdel(String key, String... field);
    Boolean hexists(String key, String field);
    List<String> hvals(String key);
    //hmset hmget hkeys后扩展的方法
    Set<String> hkeys(String key);
    String hmset(String key, Map<String, String> map);
    List<String> hmget(String key, String... field);
    //List类型常用命令
    Long lpush(String key, String... value);


}

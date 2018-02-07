package com.ddshop.jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * User: DHC
 * Date: 2018/1/19
 * Time: 14:06
 * Version:V1.0
 */
public class JedisClientPool implements JedisClient {
    private JedisPool jedisPool;

    public JedisPool getJedisPool() {
        return jedisPool;
    }

    public void setJedisPool(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    @Override
    public String set(String key, String value) {
        Jedis jedis = jedisPool.getResource();
        return jedis.set(key, value);
    }

    @Override
    public String get(String key) {
        Jedis jedis = jedisPool.getResource();
        return jedis.get(key);
    }

    @Override
    public Boolean exists(String key) {
        Jedis jedis = jedisPool.getResource();
        return jedis.exists(key);
    }

    @Override
    public Long expire(String key, int seconds) {
        Jedis jedis = jedisPool.getResource();
        return jedis.expire(key,seconds);
    }

    @Override
    public Long ttl(String key) {
        Jedis jedis = jedisPool.getResource();
        return jedis.ttl(key);
    }

    @Override
    public Long incr(String key) {
        Jedis jedis = jedisPool.getResource();
        return jedis.incr(key);
    }

    @Override
    public Long del(String key) {
        Jedis jedis = jedisPool.getResource();
        return jedis.del(key);
    }

    @Override
    public Long hset(String key, String field, String value) {
        Jedis jedis = jedisPool.getResource();
        return jedis.hset(key,field,value);
    }

    @Override
    public String hget(String key, String field) {
        Jedis jedis = jedisPool.getResource();
        return jedis.hget(key,field);
    }

    @Override
    public Long hdel(String key, String... field) {
        Jedis jedis = jedisPool.getResource();
        return jedis.hdel(key,field);
    }

    @Override
    public Boolean hexists(String key, String field) {
        Jedis jedis = jedisPool.getResource();
        return jedis.hexists(key,field);
    }

    @Override
    public List<String> hvals(String key) {
        Jedis jedis = jedisPool.getResource();
        return jedis.hvals(key);
    }

    @Override
    public Set<String> hkeys(String key) {
        Jedis jedis = jedisPool.getResource();
        return jedis.hkeys(key);
    }

    @Override
    public String hmset(String key, Map<String, String> map) {
        Jedis jedis = jedisPool.getResource();
        return jedis.hmset(key,map);
    }

    @Override
    public List<String> hmget(String key, String... field) {
        Jedis jedis = jedisPool.getResource();
        return jedis.hmget(key,field);
    }

    @Override
    public Long lpush(String key, String... value) {
        Jedis jedis = jedisPool.getResource();
        return jedis.lpush(key,value);
    }
}

/*
package com.ddshop.jedis;

import org.junit.Test;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;


public class JedisTest {

    @Test
    public void testJedis1(){
        Set<HostAndPort> nodes = new HashSet<HostAndPort>();
        nodes.add(new HostAndPort("120.79.90.102",9001));
        nodes.add(new HostAndPort("120.79.90.102",9002));
        nodes.add(new HostAndPort("120.79.90.102",9003));
        nodes.add(new HostAndPort("120.79.90.102",9004));
        nodes.add(new HostAndPort("120.79.90.102",9005));
        nodes.add(new HostAndPort("120.79.90.102",9006));
        JedisCluster jedisCluster = new JedisCluster(nodes);
        jedisCluster.set("key2","aaa");
        System.out.print(jedisCluster.get("key2"));
    }
}
*/

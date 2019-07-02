package com.taotao.test.jedis;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.taotao.content.jedis.JedisClient;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

public class TestJedis {

	//原生操作集群版
	@Test
	public void JedisClusterTest() throws IOException{

		Set<HostAndPort> nodes=new HashSet<>();
		nodes.add(new HostAndPort("192.168.25.128", 7001));
		nodes.add(new HostAndPort("192.168.25.128", 7002));
		nodes.add(new HostAndPort("192.168.25.128", 7003));
		nodes.add(new HostAndPort("192.168.25.128", 7004));
		nodes.add(new HostAndPort("192.168.25.128", 7005));
		nodes.add(new HostAndPort("192.168.25.128", 7006));
		JedisPoolConfig poolConfig=new JedisPoolConfig();
		poolConfig.setMaxTotal(100);
		poolConfig.setMaxIdle(50);
		
		JedisCluster jedisCluster=new JedisCluster(nodes, 5000, 5000, 3, "123456", poolConfig);
		
		System.out.println(jedisCluster.set("key1", "123"));;
		System.out.println(jedisCluster.get("key1"));
		System.out.println(jedisCluster.del("key1"));
		jedisCluster.close();
		
	}
	
	//spring管理单机版
	@Test
	public void testDanji() {
		ApplicationContext context=new ClassPathXmlApplicationContext("classpath:spring/applicationContext-redis.xml");
		
		JedisClient jedisClient = context.getBean(JedisClient.class);
		
		jedisClient.set("jedisClienKey", "jedisClienKey");
		System.out.println(jedisClient.get("jedisClienKey"));
		System.out.println(jedisClient.del("jedisClienKey"));
		System.out.println(jedisClient.get("jedisClienKey"));
		
	}
}

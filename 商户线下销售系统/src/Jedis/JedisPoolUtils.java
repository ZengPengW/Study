package Jedis;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import javax.servlet.ServletContext;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisPoolUtils  {

	//�������ӳ�
		private static JedisPoolConfig config;
		private static JedisPool pool;
		
		static{
			config=new JedisPoolConfig();
			config.setMaxTotal(30);
			config.setMaxIdle(2);
			
			pool=new JedisPool(config, "192.168.204.128", 6379);
		}
		
		
		//��ȡ���ӵķ���
		public static Jedis getJedis(){
			return pool.getResource();
		}
		
		
		//�ͷ�����
		public static void closeJedis(Jedis j){
			j.close();
		}
	
}

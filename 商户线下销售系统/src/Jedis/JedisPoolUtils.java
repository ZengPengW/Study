package Jedis;



import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisPoolUtils  {

	//�������ӳ�
		private static JedisPoolConfig config;
		private static JedisPool pool;
		
		static{
			config=new JedisPoolConfig();
			config.setMaxTotal(100);
			config.setMaxIdle(50);
			
			//pool=new JedisPool(config, "192.168.204.128", 6379);
			//pool=new JedisPool(config, "192.168.65.128", 6379);
			//pool=new JedisPool(config, "192.168.65.128", 6379, 5000, "2836922404");
			pool=new JedisPool(config, "192.168.204.128", 6379, 5000, "2836922404");
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

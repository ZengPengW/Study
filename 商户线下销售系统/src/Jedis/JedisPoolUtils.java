package Jedis;



import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisPoolUtils  {

	//创建连接池
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
		
		
		//获取连接的方法
		public static Jedis getJedis(){
			return pool.getResource();
		}
		
		
		//释放连接
		public static void closeJedis(Jedis j){
			j.close();
		}
	
}

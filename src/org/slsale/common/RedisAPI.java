package org.slsale.common;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * redis 缓存工具
 * 
 * @author sa
 *
 */
public class RedisAPI {
	private JedisPool jedisPool;

	public JedisPool getJedisPool() {
		return jedisPool;
	}

	public void setJedisPool(JedisPool jedisPool) {
		this.jedisPool = jedisPool;
	}

	// 设值
	public boolean set(String key, String value) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.set(key, value);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			returnResource(jedisPool, jedis);
		}
		return false;
	}

	// 取值
	public String get(String key) {
		String value = null;
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			value = jedis.get(key);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			returnResource(jedisPool, jedis);
		}
		return value;
	}

	/**
	 * 判断是否存在
	 */
	public boolean exists(String key) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.exists(key);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			returnResource(jedisPool, jedis);
		}
		return false;
	}
	/**
	 * 回收资源
	 */
	public void returnResource(JedisPool jedisPool, Jedis jedis) {
		if (jedis != null) {
			jedisPool.returnResource(jedis);
		}
	}

}

package com.ujuit.quant.firmoffer.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.SerializationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.ujuit.quant.firmoffer.service.RedisService;

import redis.clients.jedis.BinaryClient.LIST_POSITION;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.SortingParams;
import redis.clients.jedis.Tuple;

/**
 * 
 * @author <a href="mailto:odenpan@gmail.com">Oden Pan</a> 2016年6月29日
 *         RedisServiceImpl.java
 * @version 1.0
 **/
@Repository
public class JedisServiceImpl implements RedisService {

	private static final Logger log = LoggerFactory.getLogger(JedisServiceImpl.class);

	@Autowired
	JedisPool jedisPool;

	public void disconnect(Jedis jedis) {
		if (jedis != null)
			jedis.disconnect();
	}

	public void returnResource(Jedis jedis) {
		if (jedis != null) {
			jedis.close();
		}
	}

	public Jedis getResource() {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
		} catch (Exception e) {

			log.error(e.getMessage(), e);
		}
		return jedis;
	}

	/**
	 * 设置单个值
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public String set(String key, String value) {
		String result = null;
		Jedis jedis = getResource();

		try {
			result = jedis.set(key, value);
		} catch (Exception e) {
			log.error(e.getMessage(), e);

		} finally {

			returnResource(jedis);
		}
		return result;
	}

	/**
	 * 获取单个值
	 * 
	 * @param key
	 * @return
	 */
	public String get(String key) {
		String result = null;
		Jedis jedis = getResource();

		try {
			result = jedis.get(key);

		} catch (Exception e) {
			log.error(e.getMessage(), e);

		} finally {
			returnResource(jedis);
		}
		return result;
	}

	public Boolean exists(String key) {
		Boolean result = false;
		Jedis jedis = getResource();

		try {
			result = jedis.exists(key);
		} catch (Exception e) {
			log.error(e.getMessage(), e);

		} finally {
			returnResource(jedis);
		}
		return result;
	}

	public String type(String key) {
		String result = null;
		Jedis jedis = getResource();

		try {
			result = jedis.type(key);

		} catch (Exception e) {
			log.error(e.getMessage(), e);

		} finally {
			returnResource(jedis);
		}
		return result;
	}

	/**
	 * 在某段时间后实现
	 * 
	 * @param key
	 * @param unixTime
	 * @return
	 */
	public Long expire(String key, int seconds) {
		Long result = null;

		Jedis jedis = getResource();
		try {
			result = jedis.expire(key, seconds);

		} catch (Exception e) {
			log.error(e.getMessage(), e);

		} finally {
			returnResource(jedis);
		}
		return result;
	}

	/**
	 * 在某个时间点失效
	 * 
	 * @param key
	 * @param unixTime
	 * @return
	 */
	public Long expireAt(String key, long unixTime) {
		Long result = null;
		Jedis jedis = getResource();
		try {
			result = jedis.expireAt(key, unixTime);

		} catch (Exception e) {
			log.error(e.getMessage(), e);

		} finally {
			returnResource(jedis);
		}
		return result;
	}

	public Long ttl(String key) {
		Long result = null;
		Jedis jedis = getResource();
		try {
			result = jedis.ttl(key);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	public boolean setbit(String key, long offset, boolean value) {

		Jedis jedis = getResource();
		boolean result = false;
		try {
			result = jedis.setbit(key, offset, value);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	public boolean getbit(String key, long offset) {
		boolean result = false;
		Jedis jedis = getResource();
		try {
			result = jedis.getbit(key, offset);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	public long setrange(String key, long offset, String value) {
		long result = 0;
		Jedis jedis = getResource();
		try {
			result = jedis.setrange(key, offset, value);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	public String getrange(String key, long startOffset, long endOffset) {
		String result = null;
		Jedis jedis = getResource();
		try {
			result = jedis.getrange(key, startOffset, endOffset);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	public String getSet(String key, String value) {
		String result = null;
		Jedis jedis = getResource();
		try {
			result = jedis.getSet(key, value);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	public Long setnx(String key, String value) {
		Long result = null;
		Jedis jedis = getResource();

		try {
			result = jedis.setnx(key, value);
		} catch (Exception e) {
			log.error(e.getMessage(), e);

		} finally {
			returnResource(jedis);
		}
		return result;
	}

	public String setex(String key, int seconds, String value) {
		String result = null;
		Jedis jedis = getResource();
		try {
			result = jedis.setex(key, seconds, value);

		} catch (Exception e) {
			log.error(e.getMessage(), e);

		} finally {
			returnResource(jedis);
		}
		return result;
	}

	public Long decrBy(String key, long integer) {
		Long result = null;
		Jedis jedis = getResource();
		try {
			result = jedis.decrBy(key, integer);

		} catch (Exception e) {
			log.error(e.getMessage(), e);

		} finally {
			returnResource(jedis);
		}
		return result;
	}

	public Long decr(String key) {
		Long result = null;
		Jedis jedis = getResource();

		try {
			result = jedis.decr(key);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	public Long incrBy(String key, long integer) {
		Long result = null;
		Jedis jedis = getResource();
		try {
			result = jedis.incrBy(key, integer);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	public Long incr(String key) {
		Long result = null;
		Jedis jedis = getResource();
		try {
			result = jedis.incr(key);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	public Long append(String key, String value) {
		Long result = null;
		Jedis jedis = getResource();
		try {
			result = jedis.append(key, value);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	public String substr(String key, int start, int end) {
		String result = null;
		Jedis jedis = getResource();
		try {
			result = jedis.substr(key, start, end);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	public Long hset(String key, String field, String value) {
		Long result = null;
		Jedis jedis = getResource();
		try {
			result = jedis.hset(key, field, value);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	public String hget(String key, String field) {
		String result = null;
		Jedis jedis = getResource();
		try {
			result = jedis.hget(key, field);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	public Long hsetnx(String key, String field, String value) {
		Long result = null;
		Jedis jedis = getResource();
		try {
			result = jedis.hsetnx(key, field, value);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	public String hmset(String key, Map<String, String> hash) {
		String result = null;
		Jedis jedis = getResource();
		try {
			result = jedis.hmset(key, hash);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	public List<String> hmget(String key, String... fields) {
		List<String> result = null;
		Jedis jedis = getResource();
		try {
			result = jedis.hmget(key, fields);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	public Long hincrBy(String key, String field, long value) {
		Long result = null;
		Jedis jedis = getResource();
		try {
			result = jedis.hincrBy(key, field, value);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	public Boolean hexists(String key, String field) {
		Boolean result = false;
		Jedis jedis = getResource();
		try {
			result = jedis.hexists(key, field);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	public Long del(String key) {
		Long result = null;
		Jedis jedis = getResource();
		try {
			result = jedis.del(key);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	public Long hdel(String key, String... fields) {
		Long result = null;
		Jedis jedis = getResource();
		try {
			result = jedis.hdel(key, fields);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	public Long hlen(String key) {
		Long result = null;
		Jedis jedis = getResource();
		try {
			result = jedis.hlen(key);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	public Set<String> hkeys(String key) {
		Set<String> result = null;
		Jedis jedis = getResource();
		try {
			result = jedis.hkeys(key);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	public List<String> hvals(String key) {
		List<String> result = null;
		Jedis jedis = getResource();
		try {
			result = jedis.hvals(key);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	public Map<String, String> hgetAll(String key) {
		Map<String, String> result = null;
		Jedis jedis = getResource();
		try {
			result = jedis.hgetAll(key);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	// ================list ====== l表示 list或 left, r表示right====================
	public Long rpush(String key, String string) {
		Long result = null;
		Jedis jedis = getResource();
		try {
			result = jedis.rpush(key, string);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	public Long lpush(String key, String string) {
		Long result = null;
		Jedis jedis = getResource();
		try {
			result = jedis.lpush(key, string);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	public Long llen(String key) {
		Long result = null;
		Jedis jedis = getResource();
		try {
			result = jedis.llen(key);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	public List<String> lrange(String key, long start, long end) {
		List<String> result = null;
		Jedis jedis = getResource();
		try {
			result = jedis.lrange(key, start, end);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	public String ltrim(String key, long start, long end) {
		String result = null;
		Jedis jedis = getResource();
		try {
			result = jedis.ltrim(key, start, end);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	public String lindex(String key, long index) {
		String result = null;
		Jedis jedis = getResource();
		try {
			result = jedis.lindex(key, index);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	public String lset(String key, long index, String value) {
		String result = null;
		Jedis jedis = getResource();
		try {
			result = jedis.lset(key, index, value);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	public Long lrem(String key, long count, String value) {
		Long result = null;
		Jedis jedis = getResource();
		try {
			result = jedis.lrem(key, count, value);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	public String lpop(String key) {
		String result = null;
		Jedis jedis = getResource();
		try {
			result = jedis.lpop(key);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	public String rpop(String key) {
		String result = null;
		Jedis jedis = getResource();
		try {
			result = jedis.rpop(key);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	// return 1 add a not exist value ,
	// return 0 add a exist value
	public Long sadd(String key, String member) {
		Long result = null;
		Jedis jedis = getResource();
		try {
			result = jedis.sadd(key, member);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	public Set<String> smembers(String key) {
		Set<String> result = null;
		Jedis jedis = getResource();
		try {
			result = jedis.smembers(key);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	public Long srem(String key, String member) {

		Long result = null;
		Jedis jedis = getResource();
		try {
			result = jedis.srem(key, member);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	public String spop(String key) {
		String result = null;
		Jedis jedis = getResource();
		try {
			result = jedis.spop(key);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	public Long scard(String key) {
		Long result = null;
		Jedis jedis = getResource();
		try {
			result = jedis.scard(key);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	public Boolean sismember(String key, String member) {
		Boolean result = null;
		Jedis jedis = getResource();
		try {
			result = jedis.sismember(key, member);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	public String srandmember(String key) {
		String result = null;
		Jedis jedis = getResource();
		try {
			result = jedis.srandmember(key);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	public Long zadd(String key, double score, String member) {
		Long result = null;
		Jedis jedis = getResource();
		try {
			result = jedis.zadd(key, score, member);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	public Set<String> zrange(String key, int start, int end) {
		Set<String> result = null;
		Jedis jedis = getResource();

		try {
			result = jedis.zrange(key, start, end);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	public Long zrem(String key, String member) {
		Long result = null;
		Jedis jedis = getResource();
		try {
			result = jedis.zrem(key, member);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	public Double zincrby(String key, double score, String member) {
		Double result = null;
		Jedis jedis = getResource();
		try {

			result = jedis.zincrby(key, score, member);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	public Long zrank(String key, String member) {
		Long result = null;
		Jedis jedis = getResource();
		try {

			result = jedis.zrank(key, member);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	public Long zrevrank(String key, String member) {
		Long result = null;
		Jedis jedis = getResource();
		try {

			result = jedis.zrevrank(key, member);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	public Set<String> zrevrange(String key, int start, int end) {
		Set<String> result = null;
		Jedis jedis = getResource();
		try {

			result = jedis.zrevrange(key, start, end);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	public Set<Tuple> zrangeWithScores(String key, int start, int end) {
		Set<Tuple> result = null;
		Jedis jedis = getResource();
		try {

			result = jedis.zrangeWithScores(key, start, end);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	public Set<Tuple> zrevrangeWithScores(String key, int start, int end) {
		Set<Tuple> result = null;
		Jedis jedis = getResource();
		try {

			result = jedis.zrevrangeWithScores(key, start, end);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	public Long zcard(String key) {
		Long result = null;
		Jedis jedis = getResource();
		try {

			result = jedis.zcard(key);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	public Double zscore(String key, String member) {
		Double result = null;
		Jedis jedis = getResource();
		try {

			result = jedis.zscore(key, member);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	public List<String> sort(String key) {
		List<String> result = null;
		Jedis jedis = getResource();
		try {

			result = jedis.sort(key);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	public List<String> sort(String key, SortingParams sortingParameters) {
		List<String> result = null;
		Jedis jedis = getResource();
		try {

			result = jedis.sort(key, sortingParameters);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	public Long zcount(String key, double min, double max) {
		Long result = null;
		Jedis jedis = getResource();
		try {

			result = jedis.zcount(key, min, max);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	public Set<String> zrangeByScore(String key, double min, double max) {
		Set<String> result = null;
		Jedis jedis = getResource();
		try {

			result = jedis.zrangeByScore(key, min, max);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	public Set<String> zrevrangeByScore(String key, double max, double min) {
		Set<String> result = null;
		Jedis jedis = getResource();
		try {

			result = jedis.zrevrangeByScore(key, max, min);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	public Set<String> zrangeByScore(String key, double min, double max, int offset, int count) {
		Set<String> result = null;
		Jedis jedis = getResource();
		try {

			result = jedis.zrangeByScore(key, min, max, offset, count);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	public Set<String> zrevrangeByScore(String key, double max, double min, int offset, int count) {
		Set<String> result = null;
		Jedis jedis = getResource();
		try {

			result = jedis.zrevrangeByScore(key, max, min, offset, count);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	public Set<Tuple> zrangeByScoreWithScores(String key, double min, double max) {
		Set<Tuple> result = null;
		Jedis jedis = getResource();
		try {

			result = jedis.zrangeByScoreWithScores(key, min, max);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	public Set<Tuple> zrevrangeByScoreWithScores(String key, double max, double min) {
		Set<Tuple> result = null;
		Jedis jedis = getResource();
		try {

			result = jedis.zrevrangeByScoreWithScores(key, max, min);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	public Set<Tuple> zrangeByScoreWithScores(String key, double min, double max, int offset, int count) {
		Set<Tuple> result = null;
		Jedis jedis = getResource();
		try {

			result = jedis.zrangeByScoreWithScores(key, min, max, offset, count);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	public Set<Tuple> zrevrangeByScoreWithScores(String key, double max, double min, int offset, int count) {
		Set<Tuple> result = null;
		Jedis jedis = getResource();
		try {

			result = jedis.zrevrangeByScoreWithScores(key, max, min, offset, count);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	public Long zremrangeByRank(String key, int start, int end) {
		Long result = null;
		Jedis jedis = getResource();
		try {

			result = jedis.zremrangeByRank(key, start, end);

		} catch (Exception e) {
			log.error(e.getMessage(), e);

		} finally {
			returnResource(jedis);
		}
		return result;
	}

	public Long zremrangeByScore(String key, double start, double end) {
		Long result = null;
		Jedis jedis = getResource();
		try {

			result = jedis.zremrangeByScore(key, start, end);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	public Long linsert(String key, LIST_POSITION where, String pivot, String value) {
		Long result = null;
		Jedis jedis = getResource();
		try {

			result = jedis.linsert(key, where, pivot, value);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	public void disconnect() {

		disconnect(getResource());
	}

	public String set(byte[] key, byte[] value) {
		String result = null;
		Jedis jedis = getResource();

		try {
			result = jedis.set(key, value);
		} catch (Exception e) {
			log.error(e.getMessage(), e);

		} finally {

			returnResource(jedis);
		}
		return result;
	}

	/**
	 * 获取单个值
	 * 
	 * @param key
	 * @return
	 */
	public byte[] get(byte[] key) {
		byte[] result = null;
		Jedis jedis = getResource();

		try {
			result = jedis.get(key);

		} catch (Exception e) {
			log.error(e.getMessage(), e);

		} finally {
			returnResource(jedis);
		}
		return result;
	}

	@Override
	public <T extends Serializable> String setSeria(String key, T value) {

		byte[] arr = SerializationUtils.serialize(value);

		Jedis jedis = getResource();
		String result = null;
		try {
			result = jedis.set(key.getBytes(), arr);
		} catch (Exception e) {
			log.error(e.getMessage(), e);

		} finally {

			returnResource(jedis);
		}
		return result;

	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends Serializable> T getSeria(String key) {
		Jedis jedis = getResource();
		byte[] arr = null;
		try {
			arr = jedis.get(key.getBytes());

		} catch (Exception e) {
			log.error(e.getMessage(), e);

		} finally {
			returnResource(jedis);
		}
		return (T) SerializationUtils.deserialize(arr);
	}

	@Override
	public <T> List<T> mget(Class<T> clazz, String... key) {
		List<String> result = null;
		Jedis jedis = getResource();
		List<T> list = new ArrayList<T>();

		try {
			result = jedis.mget(key);
			if (result != null)
				for (String text : result) {
					list.add(JSON.parseObject(text, clazz));
				}
		} catch (Exception e) {
			log.error(e.getMessage(), e);

		} finally {
			returnResource(jedis);
		}
		return list;
	}

	@Override
	public <T> T mgetOne(Class<T> clazz, String key) {
		List<String> result = null;
		Jedis jedis = getResource();

		try {
			result = jedis.mget(key);
			if (result != null)
				for (String text : result) {
					return (JSON.parseObject(text, clazz));
				}
		} catch (Exception e) {
			log.error(e.getMessage(), e);

		} finally {
			returnResource(jedis);
		}
		return null;
	}

	@Override
	public <T> String mset(Map<String, T> kv) {

		Jedis jedis = getResource();

		try {
			if (kv.size() == 0)
				return null;
			String[] keysvalues = new String[kv.size() * 2];
			int i = 0;
			for (String key : kv.keySet()) {
				if (StringUtils.isEmpty(key))
					continue;
				keysvalues[i++] = key;
				keysvalues[i++] = JSON.toJSONString(kv.get(key));

			}

			return jedis.mset(keysvalues);

		} catch (Exception e) {
			log.error(e.getMessage(), e);

		} finally {
			returnResource(jedis);
		}

		return null;
	}

	public String[] keys(String pattern) {

		Jedis jedis = getResource();

		try {

			if (pattern == null)
				pattern = "";

			Set<String> set = jedis.keys(pattern);

			String strs[] = new String[set.size()];
			int i = 0;

			for (String string : set) {
				strs[i++] = string;
			}

			return strs;

		} catch (Exception e) {
			log.error(e.getMessage(), e);

		} finally {
			returnResource(jedis);
		}

		return new String[0];
	}

}

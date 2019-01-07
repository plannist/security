package com.sec.prac.common.util;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;



//@Component
public class RedisTemplateUtil {
	
	@Autowired
	JedisConnectionFactory jedisConnectionFactory;
	
	private static final Logger logger = LoggerFactory.getLogger(RedisTemplateUtil.class);
	private RedisTemplate<String, Object> redisTemplate;
	

	
	public RedisTemplateUtil() {
		//redisTemplate = new RedisTemplate<>();
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		//redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<Object>(Object.class));
		redisTemplate.setHashKeySerializer(new StringRedisSerializer());
		//redisTemplate.setHashValueSerializer(new Jackson2JsonRedisSerializer<Object>(Object.class));
		redisTemplate.setConnectionFactory(jedisConnectionFactory);
		logger.debug("@@@@@@@@@@@ redisTemplate 인입: 60초: "+TimeUnit.SECONDS.toMillis(60)+", 30초: "+TimeUnit.SECONDS.toMillis(30));
	}
	
	public RedisTemplate<String, Object> getRedisTemplate() {
		return redisTemplate;
	}

	public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}
	
	
	
}

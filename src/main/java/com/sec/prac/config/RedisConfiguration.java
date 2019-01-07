package com.sec.prac.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;



@Configuration
public class RedisConfiguration {

	private static final Logger log = LoggerFactory.getLogger(RedisConfiguration.class);
	

	
	@Bean(name="jedisConnectionFactory")
	 public JedisConnectionFactory jedisConnectionFactory() {
	   JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
	   jedisConnectionFactory.setHostName("218.55.23.197");
	   jedisConnectionFactory.setPort(6379);
	   jedisConnectionFactory.setTimeout(0);
	   jedisConnectionFactory.setUsePool(true);
	   return jedisConnectionFactory;
	 }

	 @Bean(name="jedisTemplate")
	 public RedisTemplate<String, Object> jedisTemplate() {
		RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
		template.setKeySerializer(new StringRedisSerializer());
		template.setValueSerializer(new StringRedisSerializer());
		template.setConnectionFactory(jedisConnectionFactory());		
		return template;
	 }
	
//	@Bean
//	 public RedisTemplate redisTemplate() {
//	        RedisTemplate<String,Object> template = new RedisTemplate<String,Object>();
//	        template.setConnectionFactory(jedisConnectionFactory());
//	        return template;
//	 }
	 
//	 @Bean
//	 public StringRedisTemplate stringRedisTemplate(JedisConnectionFactory jedisConnectionFactory) {
//	  StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
//	  stringRedisTemplate.setConnectionFactory(jedisConnectionFactory);
//	  return stringRedisTemplate;
//	 }

//	 final JedisPoolConfig poolConfig = buildPoolConfig();
//	
//	 JedisPool jedisPool = new JedisPool(poolConfig, "218.55.23.197");
//	
//
	 @Bean
	 public JedisPoolConfig buildPoolConfig() { 
		JedisPoolConfig poolConfig = new JedisPoolConfig();
//			
//		
			poolConfig.setMaxTotal(128);
			poolConfig.setMaxIdle(128);
			poolConfig.setMinIdle(16);
//			poolConfig.setTestOnBorrow(true);
//			poolConfig.setTestOnReturn(true);
//			poolConfig.setTestWhileIdle(true);
//			poolConfig.setMinEvictableIdleTimeMillis(60);
//			poolConfig.setTimeBetweenEvictionRunsMillis(Duration.standardSeconds(30).getMillis());
//			poolConfig.setNumTestsPerEvictionRun(3);
//			poolConfig.setBlockWhenExhausted(true);
			return poolConfig;
	 }
}
	
	
	









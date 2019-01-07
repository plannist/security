package com.sec.prac.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.catalina.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sec.prac.mapper.UserVO;
import com.sec.prac.service.UserService;

import redis.clients.jedis.Jedis;



/**
 * Handles requests for the application home page.
 */
@Controller
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	UserService userService;
	@Resource(name="jedisTemplate")
	ValueOperations<String, String> valueOps;
	@Resource(name="jedisConnectionFactory")
	JedisConnectionFactory jedisConnectionFactory;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index() {
		//List<Map<String, Object>> list = userService.getUserInfo();
		//logger.debug("list 확인: "+list.size());
		return "index";
	}
	
	@RequestMapping("/user")
	public String user(Principal principal) {
		logger.debug("user 에서 @@@@: "+principal.getName());
		List<UserVO> list = userService.getUserVOInfo();
		logger.debug("userInfo- getByDB:"+list.get(0));
		
		return "user";
	}
	
	@RequestMapping("/admin")
	public String admin() {
		SecurityContext context = SecurityContextHolder.getContext();
		logger.debug("admin 에서 @@@@: "+context.getAuthentication().getName());
		
		return "admin";
	}
	
	@RequestMapping("/jedis")
	public String jedisTest() {
		Jedis jedis = (Jedis) jedisConnectionFactory.getConnection().getNativeConnection();
		Map<String, String> map = new HashMap<String, String>();
		//map.put("a", "a");
		//map.put("b", "b");
		
		//jedis.hmset("abc", map);
		map = jedis.hgetAll("abc");
		
		logger.debug("jedis 에서 확인: "+map);
		
		return "index";
	}
	
	
}

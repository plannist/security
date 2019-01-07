package com.sec.prac.service;

import java.util.List;
import java.util.Map;

import org.apache.catalina.User;

import com.sec.prac.mapper.UserVO;

public interface UserService {
	
	//List<Map<String, Object>> getUserInfo();
	List<UserVO> getUserVOInfo();
}

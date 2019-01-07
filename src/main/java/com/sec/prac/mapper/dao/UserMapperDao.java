package com.sec.prac.mapper.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sec.prac.mapper.UserMapper;
import com.sec.prac.mapper.UserVO;

@Repository
public class UserMapperDao {
	@Autowired
	UserMapper userMapper;
	
	public List<UserVO> getUserVOInfo(){
		return userMapper.getUserVOInfo();
	}
}

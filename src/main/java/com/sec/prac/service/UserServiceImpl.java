package com.sec.prac.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sec.prac.mapper.UserVO;
import com.sec.prac.mapper.dao.UserMapperDao;

@Service
public class UserServiceImpl implements UserService {
	
	
	@Autowired
	UserMapperDao userMapperDao;
	
	@Override
	public List<UserVO> getUserVOInfo() {
		// TODO Auto-generated method stub
		return userMapperDao.getUserVOInfo();
	}

}

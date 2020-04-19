package com.bisaKhadayate.serviceImpl;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bisaKhadayate.bean.User;
import com.bisaKhadayate.interfaces.dao.UserDao;
import com.bisaKhadayate.interfaces.services.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserDao userDao;
	
	@Override
	@Transactional
	public User userLogin(String userName, String password) {
		return userDao.userLogin(userName, password);
	}

	@Override
	public List<Map<String, Object>> searchResult(Character gender,int index ) {
		// TODO Auto-generated method stub
		return userDao.searchResult(gender,true,index);
	}

	@Override
	@Transactional
	public Optional<User> getUser(Integer id) {
		return userDao.findById(id);
	}

	@Override
	@Transactional
	public Integer searchResultCount(Character gender, boolean isActive) {
		// TODO Auto-generated method stub
		return userDao.searchResultCount(gender, isActive);
	}

	
}

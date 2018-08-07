package org.slsale.service.user;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.slsale.dao.user.UserMapper;
import org.slsale.pojo.User;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {
	private Logger logger = Logger.getLogger(UserServiceImpl.class);
	@Resource
	private UserMapper userMapper;

	@Override
	public User getUser(User user) throws Exception {
		return userMapper.getUser(user);
	}

	@Override
	public boolean addUser(User user) throws Exception {
		return userMapper.addUser(user)>0;
	}

	@Override
	public boolean isLoginCodeExist(User user) throws Exception {
		return userMapper.isloginCodeExist(user)>0;
	}

	@Override
	public boolean modifyUser(User user) throws Exception {
		return userMapper.modifyUserById(user) > 0;
	}

	@Override
	public int getUserCount(User user) throws Exception {
		return userMapper.getUserCount(user);
	}

	@Override
	public List<User> getUserList(User user) throws Exception {
		return userMapper.getUserList(user);
	}

	@Override
	public User getUserById(Integer id) throws Exception {
		return userMapper.getUserById(id);
	}

	@Override
	public boolean delPic(User user) throws Exception {
		return userMapper.delPic(user)>0;
	}

	@Override
	public boolean delUserById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return userMapper.delUserById(id)>0;
	}
	
	
	

}

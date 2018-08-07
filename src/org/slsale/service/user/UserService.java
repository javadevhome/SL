package org.slsale.service.user;

import java.util.List;

import org.slsale.pojo.User;

public interface UserService {
	/**
	 * 登录
	 * 
	 * @param userCode
	 * @param password
	 * @return
	 * @throws Exception
	 */
	User getUser(User user) throws Exception;

	/**
	 * 查询loginCode是否存在
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	boolean isLoginCodeExist(User user) throws Exception;

	/**
	 * 注册
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	boolean addUser(User user) throws Exception;

	boolean modifyUser(User user) throws Exception;

	public int getUserCount(User user) throws Exception;// 查询记录数
	/**
	 * 查询用户列表
	 */
	public List<User> getUserList(User user) throws Exception;
	public User getUserById(Integer id)throws Exception;
	public boolean delPic(User user) throws Exception;
	public boolean delUserById(Integer id) throws Exception;
}

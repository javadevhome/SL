package org.slsale.dao.user;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.slsale.pojo.User;

/**
 * 用户接口
 * 
 * @author Administrator
 *
 */
public interface UserMapper {
	/**
	 * 查询用户
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	User getUser(User user) throws Exception;

	/**
	 * 查询用户loginCode是否存在
	 * 
	 * @param loginCode
	 * @return
	 * @throws Exception
	 */
	int isloginCodeExist(User user) throws Exception;

	/**
	 * 修改用户信息
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	int modifyUserById(User user) throws Exception;

	/**
	 * 查询记录数
	 */
	public int getUserCount(User user) throws Exception;

	/**
	 * 查询用户列表
	 */
	public List<User> getUserList(User user) throws Exception;

	// 添加用户
	public int addUser(User user) throws Exception;

	public User getUserById(@Param("id") Integer id) throws Exception;

	// 删除图片
	public int delPic(User user) throws Exception;

	// 删除用户
	public int delUserById(@Param("id") Integer id) throws Exception;
	
	public int modifyUserRole(User user)throws Exception;
	
	

}

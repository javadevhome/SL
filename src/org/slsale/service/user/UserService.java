package org.slsale.service.user;

import java.util.List;

import org.slsale.pojo.User;

public interface UserService {
	/**
	 * ��¼
	 * 
	 * @param userCode
	 * @param password
	 * @return
	 * @throws Exception
	 */
	User getUser(User user) throws Exception;

	/**
	 * ��ѯloginCode�Ƿ����
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	boolean isLoginCodeExist(User user) throws Exception;

	/**
	 * ע��
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	boolean addUser(User user) throws Exception;

	boolean modifyUser(User user) throws Exception;

	public int getUserCount(User user) throws Exception;// ��ѯ��¼��
	/**
	 * ��ѯ�û��б�
	 */
	public List<User> getUserList(User user) throws Exception;
	public User getUserById(Integer id)throws Exception;
	public boolean delPic(User user) throws Exception;
	public boolean delUserById(Integer id) throws Exception;
}

package org.slsale.dao.user;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.slsale.pojo.User;

/**
 * �û��ӿ�
 * 
 * @author Administrator
 *
 */
public interface UserMapper {
	/**
	 * ��ѯ�û�
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	User getUser(User user) throws Exception;

	/**
	 * ��ѯ�û�loginCode�Ƿ����
	 * 
	 * @param loginCode
	 * @return
	 * @throws Exception
	 */
	int isloginCodeExist(User user) throws Exception;

	/**
	 * �޸��û���Ϣ
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	int modifyUserById(User user) throws Exception;

	/**
	 * ��ѯ��¼��
	 */
	public int getUserCount(User user) throws Exception;

	/**
	 * ��ѯ�û��б�
	 */
	public List<User> getUserList(User user) throws Exception;

	// �����û�
	public int addUser(User user) throws Exception;

	public User getUserById(@Param("id") Integer id) throws Exception;

	// ɾ��ͼƬ
	public int delPic(User user) throws Exception;

	// ɾ���û�
	public int delUserById(@Param("id") Integer id) throws Exception;
	
	public int modifyUserRole(User user)throws Exception;
	
	

}
package org.slsale.dao.role;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.slsale.pojo.Role;

/**
 * ��ɫ�ӿ�
 * 
 * @author Administrator
 *
 */
public interface RoleMapper {
	/**
	 * ��ȡ���н�ɫ��Ϣ
	 * 
	 * @return
	 */
	List<Role> getRoleList() throws Exception;

	/**
	 * ��ȡ���н�ɫ��Ϣ
	 * 
	 * @return
	 */
	List<Role> getRoleListAll() throws Exception;

	/**
	 * ����
	 */
	public int modifyRoleById(Role role) throws Exception;

	// ͳ��role����
	public int countRole(Role role) throws Exception;

	// ���Role
	public int addRole(Role role) throws Exception;
	// roleId ��ѯuserLoginCode
	public Role getUserListByRoleId(@Param("roleId") Integer roleId) throws Exception;
	
	//ɾ��
	public int delRole(@Param("roleId") Integer roleId)throws Exception;

}

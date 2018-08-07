package org.slsale.service.role;

import java.util.List;

import org.slsale.pojo.Role;

public interface RoleService {
	/**
	 * ��ȡ���н�ɫ��Ϣ
	 * 
	 * @return
	 */
	public List<Role> getRoleList() throws Exception;

	public List<Role> getRoleListAll() throws Exception;

	// �޸��Ƿ�����
	public boolean hl_modifyRole(Role role) throws Exception;

	// �ж��Ƿ����
	public int isExistsRole(Role role) throws Exception;

	// ���
	public boolean hl_addRole(Role role) throws Exception;
	//��ѯĳ��ɫid���û�loginCode
	public Role hl_getUserListByRoleId(Integer id)throws Exception;
	
	public boolean hl_delRole(Integer roleId)throws Exception;

}

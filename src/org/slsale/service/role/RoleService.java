package org.slsale.service.role;

import java.util.List;

import org.slsale.pojo.Role;

public interface RoleService {
	/**
	 * 获取所有角色信息
	 * 
	 * @return
	 */
	public List<Role> getRoleList() throws Exception;

	public List<Role> getRoleListAll() throws Exception;

	// 修改是否启用
	public boolean hl_modifyRole(Role role) throws Exception;

	// 判断是否存在
	public int isExistsRole(Role role) throws Exception;

	// 添加
	public boolean hl_addRole(Role role) throws Exception;
	//查询某角色id的用户loginCode
	public Role hl_getUserListByRoleId(Integer id)throws Exception;
	
	public boolean hl_delRole(Integer roleId)throws Exception;

}

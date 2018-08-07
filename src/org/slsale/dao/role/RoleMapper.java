package org.slsale.dao.role;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.slsale.pojo.Role;

/**
 * 角色接口
 * 
 * @author Administrator
 *
 */
public interface RoleMapper {
	/**
	 * 获取所有角色信息
	 * 
	 * @return
	 */
	List<Role> getRoleList() throws Exception;

	/**
	 * 获取所有角色信息
	 * 
	 * @return
	 */
	List<Role> getRoleListAll() throws Exception;

	/**
	 * 更新
	 */
	public int modifyRoleById(Role role) throws Exception;

	// 统计role数量
	public int countRole(Role role) throws Exception;

	// 添加Role
	public int addRole(Role role) throws Exception;
	// roleId 查询userLoginCode
	public Role getUserListByRoleId(@Param("roleId") Integer roleId) throws Exception;
	
	//删除
	public int delRole(@Param("roleId") Integer roleId)throws Exception;

}

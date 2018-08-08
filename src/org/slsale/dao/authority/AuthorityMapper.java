package org.slsale.dao.authority;

import org.apache.ibatis.annotations.Param;
import org.slsale.pojo.Authority;

public interface AuthorityMapper {
	/**
	 * 获取authority 对象
	 * @param authority
	 * @return
	 * @throws Exception
	 */
	public Authority getAuthorityByFunctionIdAndRoleId(Authority authority) throws Exception;
	/**
	 * 根据roleId删除权限
	 */
	public int delAuthorityByRoleId(@Param("roleId")Integer roleId)throws Exception;
	/**
	 * 添加权限
	 */
	public int addAuthority(Authority authority)throws Exception;

}

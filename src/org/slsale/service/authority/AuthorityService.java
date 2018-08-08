package org.slsale.service.authority;

import org.slsale.pojo.Authority;

public interface AuthorityService {
	/**
	 * 获取authority 对象
	 * @param authority
	 * @return
	 * @throws Exception
	 */
	public Authority getAuthorityByFunctionIdAndRoleId(Authority authority) throws Exception;
	/**
	 * 修改权限
	 */
	public boolean hl_modifyAuthority(Integer roleId,String[] funIds,String createdBy)throws Exception;

}

package org.slsale.service.authority;

import org.slsale.pojo.Authority;

public interface AuthorityService {
	/**
	 * ��ȡauthority ����
	 * @param authority
	 * @return
	 * @throws Exception
	 */
	public Authority getAuthorityByFunctionIdAndRoleId(Authority authority) throws Exception;
	/**
	 * �޸�Ȩ��
	 */
	public boolean hl_modifyAuthority(Integer roleId,String[] funIds,String createdBy)throws Exception;

}

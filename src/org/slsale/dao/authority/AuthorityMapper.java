package org.slsale.dao.authority;

import org.slsale.pojo.Authority;

public interface AuthorityMapper {
	/**
	 * ��ȡauthority ����
	 * @param authority
	 * @return
	 * @throws Exception
	 */
	public Authority getAuthorityByFunctionIdAndRoleId(Authority authority) throws Exception;

}

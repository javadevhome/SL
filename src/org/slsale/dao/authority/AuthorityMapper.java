package org.slsale.dao.authority;

import org.apache.ibatis.annotations.Param;
import org.slsale.pojo.Authority;

public interface AuthorityMapper {
	/**
	 * ��ȡauthority ����
	 * @param authority
	 * @return
	 * @throws Exception
	 */
	public Authority getAuthorityByFunctionIdAndRoleId(Authority authority) throws Exception;
	/**
	 * ����roleIdɾ��Ȩ��
	 */
	public int delAuthorityByRoleId(@Param("roleId")Integer roleId)throws Exception;
	/**
	 * ���Ȩ��
	 */
	public int addAuthority(Authority authority)throws Exception;

}

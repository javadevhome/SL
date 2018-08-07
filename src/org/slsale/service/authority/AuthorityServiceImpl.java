package org.slsale.service.authority;

import javax.annotation.Resource;

import org.slsale.dao.authority.AuthorityMapper;
import org.slsale.pojo.Authority;
import org.springframework.stereotype.Service;
@Service
public class AuthorityServiceImpl implements AuthorityService {
	@Resource
	private AuthorityMapper authorityMapper;

	@Override
	public Authority getAuthorityByFunctionIdAndRoleId(Authority authority) throws Exception {
		// TODO Auto-generated method stub
		return authorityMapper.getAuthorityByFunctionIdAndRoleId(authority);
	}

}

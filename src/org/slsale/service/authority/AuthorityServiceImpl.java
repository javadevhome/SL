package org.slsale.service.authority;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.slsale.dao.authority.AuthorityMapper;
import org.slsale.dao.function.FunctionMapper;
import org.slsale.pojo.Authority;
import org.slsale.pojo.Function;
import org.springframework.stereotype.Service;

@Service
public class AuthorityServiceImpl implements AuthorityService {
	@Resource
	private AuthorityMapper authorityMapper;
	@Resource
	private FunctionMapper functionMapper;
	private Logger loger = Logger.getLogger(AuthorityServiceImpl.class);

	@Override
	public Authority getAuthorityByFunctionIdAndRoleId(Authority authority) throws Exception {
		// TODO Auto-generated method stub
		return authorityMapper.getAuthorityByFunctionIdAndRoleId(authority);
	}

	@Override
	public boolean hl_modifyAuthority(Integer roleId, String[] funIds, String createdBy) throws Exception {
		authorityMapper.delAuthorityByRoleId(roleId);// 删除
		if (funIds.length > 0) {
			List<Function> funList = functionMapper.getFunctionListByFunIdArray(funIds);
			if (funList.size() > 0) {
				for (Function function : funList) {
					loger.info("查询得到的functionID有："+function.getId());
					Authority authority = new Authority();
					authority.setCreatedBy(createdBy);
					authority.setRoleId(roleId);
					authority.setCreationTime(new Date());
					authority.setFunctionId(function.getId());
					authorityMapper.addAuthority(authority);
				}
			}
		}
		return true;
	}

}

package org.slsale.service.role;

import java.util.List;

import javax.annotation.Resource;

import org.slsale.dao.role.RoleMapper;
import org.slsale.dao.user.UserMapper;
import org.slsale.pojo.Role;
import org.slsale.pojo.User;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
	@Resource
	private RoleMapper roleMapper;
	@Resource
	private UserMapper userMapper;

	@Override
	public List<Role> getRoleList() throws Exception {
		return roleMapper.getRoleList();

	}

	@Override
	public List<Role> getRoleListAll() throws Exception {
		return roleMapper.getRoleListAll();
	}

	@Override
	public boolean hl_modifyRole(Role role) throws Exception {
		roleMapper.modifyRoleById(role);
		if (role.getRoleName() != null || !"".equals(role.getRoleName())) {
			User user = new User();
			user.setRoleId(role.getId());
			user.setRoleName(role.getRoleName());
			userMapper.modifyUserRole(user);
		}
		return true;

	}

	@Override
	public int isExistsRole(Role role) throws Exception {
		return roleMapper.countRole(role);
	}

	@Override
	public boolean hl_addRole(Role role) throws Exception {
		return roleMapper.addRole(role) > 0;
	}

	@Override
	public Role hl_getUserListByRoleId(Integer id) throws Exception {
		return roleMapper.getUserListByRoleId(id);
	}

	@Override
	public boolean hl_delRole(Integer roleId) throws Exception {
		return roleMapper.delRole(roleId)>0;
	}
	

}

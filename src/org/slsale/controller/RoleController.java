package org.slsale.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.slsale.common.Contains;
import org.slsale.pojo.Role;
import org.slsale.pojo.User;
import org.slsale.service.role.RoleService;
import org.slsale.service.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import net.sf.json.JSONObject;

/**
 * 角色控制器
 * 
 * @author sa
 *
 */
@Controller
public class RoleController extends BaseController{
	private Logger logger = Logger.getLogger(RoleController.class);
	@Resource
	private RoleService roleService;
	@Resource
	private UserService userService;

	@RequestMapping(value = "/backend/rolelist.html")
	public String roleList(HttpSession session, Model model) {
		if (session.getAttribute(Contains.USER_SESSION) == null) {
			return "redirect:/";
		} else {
			// 获取功能列表
			Map<String, Object> map = (Map<String, Object>) session.getAttribute(Contains.SESSION_BASE_MODEL);
			model.addAllAttributes(map);
			// 获取角色列表
			List<Role> roleList = null;
			try {
				roleList = roleService.getRoleListAll();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			model.addAttribute("roleList", roleList);
			return "backend/rolelist";
		}

	}

	// 修改是否启用text/html;charset=UTF-8
	@RequestMapping(value = "/backend/modifyRole.html", produces = { "text/html;charset=UTF-8" })
	@ResponseBody
	public Object isStart(@RequestParam(value = "role", required = false) String role, HttpSession session) {
		String result = "";
		if (role == null || role.equals("")) {
			result = "nodata";
		} else {
			JSONObject jsonObject = JSONObject.fromObject(role);
			Role m_role = (Role) JSONObject.toBean(jsonObject, Role.class);
			m_role.setCreateDate(new Date());
			m_role.setCreatedBy(((User) session.getAttribute(Contains.USER_SESSION)).getLoginCode());
			logger.debug("==============传递进来的roleId:" + m_role.getId());
			// 更新role isStart
			try {
				if (roleService.hl_modifyRole(m_role)) {
					result = "success";
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		logger.debug("=========result:" + result);
		return result;
	}

	/**
	 * 添加角色
	 */
	@RequestMapping(value = "/backend/addRole.html", produces = { "text/html;charset=UTF-8" })
	@ResponseBody
	public Object addRole(@RequestParam(value = "role") String role, HttpSession session) {
		if (role == null || "".equals(role)) {
			return "nodata";
		} else {
			JSONObject jsonObject = JSONObject.fromObject(role);
			Role _role = (Role) JSONObject.toBean(jsonObject, Role.class);
			try {
				if (roleService.isExistsRole(_role) > 0) {
					logger.debug("===============" + roleService.isExistsRole(_role));
					logger.info("======================角色重复！");
					return "rename";
				} else {
					_role.setCreateDate(new Date());
					_role.setIsStart(1);
					_role.setCreatedBy(((User) session.getAttribute(Contains.USER_SESSION)).getLoginCode());
					if (roleService.hl_addRole(_role)) {
						logger.info("======================角色添加成功！");
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				return "failed";
			}
		}
		return "success";
	}

	// 删除角色
	@RequestMapping(value = "/backend/delRole.html", produces = { "text/html;charset=UTF-8" })
	@ResponseBody
	public Object delRole(@RequestParam(value = "role") String role) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (role == null || role.equals("")) {
			map.put("result", "nodata");
		} else {
			JSONObject jsonObject = JSONObject.fromObject(role);
			Role _role = (Role) JSONObject.toBean(jsonObject, Role.class);
			List<User> list = null;
			try {
				Role r_role = roleService.hl_getUserListByRoleId(_role.getId());
				if (r_role == null) {// 返回的对象是空的就可以删除
					roleService.hl_delRole(_role.getId());
					map.put("result", "success");
				} else {
					list = r_role.getLoginCodeList();
					logger.info("========list的长度为" + list.size());
					map.put("result", list);
					logger.info(map.get("result"));
					// JSONArray a_list = JSONArray.fromObject(list);//
					// 转为json格式返回
					// logger.info("a_list==========="+a_list.toString());
					// return a_list.toString();
				}
			} catch (Exception e) {
				e.printStackTrace();
				map.put("result", "failed");
			}
		}
		return JSONArray.toJSONString(map);

	}

}

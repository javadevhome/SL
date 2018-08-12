package org.slsale.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.slsale.common.Contains;
import org.slsale.common.RedisAPI;
import org.slsale.pojo.Authority;
import org.slsale.pojo.Function;
import org.slsale.pojo.Menu;
import org.slsale.pojo.Role;
import org.slsale.pojo.RoleFunctions;
import org.slsale.pojo.User;
import org.slsale.service.authority.AuthorityService;
import org.slsale.service.function.FunctionService;
import org.slsale.service.role.RoleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.sf.json.JSONArray;

/**
 * 权限控制器类
 * 
 * @author Administrator
 *
 */

@Controller
public class AuthorityController extends BaseController {
	private Logger logger = Logger.getLogger(AuthorityController.class);
	@Resource
	private RoleService roleService;
	@Resource
	private FunctionService functionService;
	@Resource
	private AuthorityService authorityService;
	@Resource
	private LoginController loginController;
	@Resource
	private RedisAPI redisAPI;

	/**
	 * 进入权限首页
	 * 
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/backend/authoritymanage.html")
	public String authorityMangage(HttpSession session, Model model) {
		Map<String, Object> map = (Map<String, Object>) session.getAttribute(Contains.SESSION_BASE_MODEL);
		if (map == null) {
			return "redirect:/";
		} else {
			model.addAllAttributes(map);
			List<Role> roleList = null;
			try {
				roleList = roleService.getRoleList();
				model.addAttribute(roleList);
			} catch (Exception e) {
				e.printStackTrace();
				roleList = null;
			}
		}
		return "backend/authoritymanage";
	}

	/**
	 * 获取功能列表
	 */
	@RequestMapping(value = "/backend/functions.html", produces = { "text/html;charset=UTF-8" })
	@ResponseBody
	public Object getFunctionsList() {
		String result = "";
		// 通过roleId获取权限列表
		Function function = new Function();
		function.setId(0);
		List<RoleFunctions> roleFuncList = new ArrayList<RoleFunctions>();
		try {
			List<Function> funcList = functionService.getFunctionList(function);
			if (funcList != null) {
				for (Function _function : funcList) {
					logger.info("================" + _function.getFunctionName());
					RoleFunctions roleFunc = new RoleFunctions();
					roleFunc.setMainFunction(_function);
					roleFunc.setSubFunctionList(functionService.getFunctionList(_function));// 根据functionid获取function列表
					roleFuncList.add(roleFunc);
				}
			}
			JSONArray jsonArray = JSONArray.fromObject(roleFuncList);
			result = jsonArray.toString();
			logger.debug("==================获取List<RoleFunctions>的值是：" + result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 获取默认勾选转态
	 * 
	 * @param fid
	 * @param rid
	 * @return
	 */
	@RequestMapping(value = "/backend/getAuthorityDefault.html", produces = { "text/html;charset=UTF-8" })
	@ResponseBody
	public Object getAthorityDefault(@RequestParam(value = "fid") Integer fid,
			@RequestParam(value = "rid") Integer rid) {
		String result = "nodata";
		Authority athority = new Authority();
		athority.setFunctionId(fid);
		athority.setRoleId(rid);

		try {
			Authority _athority = authorityService.getAuthorityByFunctionIdAndRoleId(athority);
			if (_athority != null) {
				result = "success";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@RequestMapping(value = "/backend/modifyAuthority.html", produces = { "text/html;charset=UTF-8" })
	@ResponseBody
	public Object modifyAuthority(@RequestParam(value = "rid", required = false) String roleId,
			@RequestParam(value = "funId", required = false) String funIds, HttpSession session) {
		User user = (User) session.getAttribute(Contains.USER_SESSION);
		if (roleId == null || roleId.equals("")) {
			return "nodata";
		} else {
			logger.info("=====================所选roleId" + roleId);
			String funId[] = funIds.split(",");
			logger.debug("=====funId[]的长度：" + funId.length);
			/*
			 * for (String string : funId) { logger.info("传递进来的funid有" +
			 * string); }
			 */
			// 修改权限：先删除所选的RoleId下的所有权限，在增加所勾选的funId,roleId,到Authority表
			try {
				authorityService.hl_modifyAuthority(Integer.parseInt(roleId), funId, user.getLoginCode());
				// redis中保存的menuList需重新赋值
				List<Menu> mList = loginController.getFunctionBycurrentUser(Integer.parseInt(roleId));
				JSONArray jsonArray = JSONArray.fromObject(mList);// 转化成json格式
				redisAPI.set("menuList" + roleId, jsonArray.toString());

				// get all role url by roleId to redis自定义拦截器时使用
				List<Function> funUrlList = functionService.getFunctionUrlListByRoleId(Integer.parseInt(roleId));
				if (funUrlList != null || funUrlList.size() >= 0) {
					StringBuffer sBuffer = new StringBuffer();
					for (Function function : funUrlList) {
						sBuffer.append(function.getFuncUrl());
					}
					// 将获取到的URL 列表保存到redis中
					redisAPI.set("role" + Integer.parseInt(roleId) + "urlList", sBuffer.toString());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			return "success";
		}

	}

}

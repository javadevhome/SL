package org.slsale.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
import org.slsale.pojo.User;
import org.slsale.service.function.FunctionService;
import org.slsale.service.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
public class LoginController extends BaseController {
	private Logger logger = Logger.getLogger(LoginController.class);
	@Resource
	private UserService userService;
	@Resource
	private FunctionService functionService;
	@Resource
	private RedisAPI redisAPI;

	@RequestMapping(value = "/index.html")
	public String index() {
		return "index";
	}

	@RequestMapping(value = "doregister.html", method = RequestMethod.POST)
	public String doregist(User user) {
		boolean flag = false;
		try {
			flag = userService.addUser(user);
			if (flag) {
				return "success";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "register";
	}

	// 处理注册
	@RequestMapping(value = "/register.html")
	public String register(@ModelAttribute("user") User user) {
		return "register";
	}

	@RequestMapping(value = "/dologin.html", method = RequestMethod.POST)
	@ResponseBody
	public Object dologin(@RequestParam("user") String user, HttpSession session) {
		logger.debug("=========进入登录验证");
		if (user == null || user.equals("")) {
			return "nodata";
		} else {
			JSONObject userObj = JSONObject.fromObject(user);
			User _user = (User) JSONObject.toBean(userObj, User.class);
			try {
				if (!userService.isLoginCodeExist(_user)) {
					return "nologinCode";
				} else {
					User userobje = userService.getUser(_user);
					if (userobje != null) {
						session.setAttribute(Contains.USER_SESSION, userobje);
						User newUser = new User();
						newUser.setId(userobje.getId());
						newUser.setLastLoginTime(new Date());
						userService.modifyUser(newUser);
						logger.debug("=========loginCode:" + userobje.getLoginCode() + "password:"
								+ userobje.getPassword() + "====" + userobje.getId());
						newUser = null;
						return "success";
					} else {
						return "pwerror";
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				return "faile";
			}
		}
	}

	// 登录
	@RequestMapping(value = "/main.html")
	public ModelAndView success(HttpSession session) {
		User user = this.getCurrentUser();
		List<Menu> mList = null;
		if (user != null) {
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("user", user);
			// 1、若Redis中的key为空，则从数据库中获取数据

			if (!redisAPI.exists("menuList" + user.getRoleId())) {
				mList = getFunctionBycurrentUser(user.getRoleId());
				if (null != mList) {
					JSONArray jsonArray = JSONArray.fromObject(mList);
					String jsonString = jsonArray.toString();
					logger.debug("=============" + jsonString);
					model.put("mList", jsonString);
					redisAPI.set("menuList" + user.getRoleId(), jsonString);// 设置到Redis中
				}
			} else {// 2.若Redis中有key，则从Redis中去数据
				String redisVlaue = redisAPI.get("menuList" + user.getRoleId());
				if (null != redisVlaue) {
					model.put("mList", redisVlaue);
					logger.debug("+++++++++++++++++++++++++Redis中的数据：" + redisVlaue);
				} else {
					return new ModelAndView("redirect:/");
				}
			}
			session.setAttribute(Contains.SESSION_BASE_MODEL, model);
			return new ModelAndView("main", model);
		}
		return new ModelAndView("redirect:/");
	}

	/**
	 * 注销
	 */
	@RequestMapping(value = "/logout.html")
	public String logout(HttpSession session) {
		session.removeAttribute(Contains.USER_SESSION);
		session.invalidate();
		this.setCurrentUser(null);
		return "redirect:/";

	}

	/**
	 * 根据当前用户角色id获取菜单列表
	 */
	protected List<Menu> getFunctionBycurrentUser(int roleId) {
		List<Menu> mList = new ArrayList<Menu>();
		Authority authority = new Authority();
		authority.setRoleId(roleId);
		try {
			List<Function> functionList = functionService.getMainFunctionList(authority);
			if (null != functionList) {
				for (Function function : functionList) {
					Menu menu = new Menu();
					menu.setMainMenu(function);
					function.setRoleId(roleId);
					List<Function> subFunctionList = functionService.getSubFunctionList(function);
					if (null != subFunctionList) {
						menu.setSubMenus(subFunctionList);
					}
					mList.add(menu);

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return mList;
	}

}

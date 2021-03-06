package org.slsale.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.log4j.Logger;
import org.slsale.common.Contains;
import org.slsale.common.JsonDateValueProcessor;
import org.slsale.common.PageSupport;
import org.slsale.common.SQLTools;
import org.slsale.pojo.DataDictionary;
import org.slsale.pojo.Role;
import org.slsale.pojo.User;
import org.slsale.service.datadictionary.DataDictionaryService;
import org.slsale.service.role.RoleService;
import org.slsale.service.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.mysql.jdbc.StringUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * 用户控制器
 * 
 * @author Administrator
 *
 */
@Controller
public class UserController extends BaseController {
	private Logger logger = Logger.getLogger(UserController.class);

	@Resource
	private UserService userService;
	@Resource
	private RoleService roleService;
	@Resource
	private DataDictionaryService dataDictionaryService;

	/**
	 * 修改密码
	 */
	@RequestMapping(value = "/backend/modifyPwd.html")
	@ResponseBody
	public Object modifyPwd(@RequestParam(value = "userJson") String userJson) {
		if (userJson == null || "".equals(userJson)) {
			return "nodata";
		} else {
			User sessionUser = this.getCurrentUser();
			// 转化json格式为javaBean
			JSONObject jsonObject = JSONObject.fromObject(userJson);
			User user = (User) JSONObject.toBean(jsonObject, User.class);
			user.setLoginCode(sessionUser.getLoginCode());
			user.setId(sessionUser.getId());
			try {
				if (userService.getUser(user) != null) {
					user.setPassword(user.getPassword2());
					user.setPassword2(null);
					userService.modifyUser(user);
					logger.debug("=====================密码修改成功！");
				} else {
					return "oldpwdwrong";
				}
			} catch (Exception e) {
				e.printStackTrace();
				return "failed";
			}
		}
		return "success";
	}

	/**
	 * 用户管理页面
	 */
	@RequestMapping(value = "/backend/userlist.html")
	public String userList(HttpSession seesion, Model model,
			@RequestParam(value = "s_loginCode", required = false) String s_loginCode,
			@RequestParam(value = "s_referCode", required = false) String s_referCode,
			@RequestParam(value = "s_roleId", required = false) String s_roleId,
			@RequestParam(value = "s_isStart", required = false) String s_isStart,
			@RequestParam(value = "currentpage", required = false) String currentpage) {
		Map<String, Object> map = (Map<String, Object>) seesion.getAttribute(Contains.SESSION_BASE_MODEL);
		if (map != null) {
			model.addAllAttributes(map);
			logger.debug("=============" + map);
		}
		List<Role> roleList = null;
		try {
			roleList = roleService.getRoleList();
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		if (roleList != null) {
			model.addAttribute("roleList", roleList);
		}

		User user = new User();
		if (s_loginCode != null) {
			user.setLoginCode("%" + SQLTools.transfer(s_loginCode) + "%");
		}
		if (s_referCode != null) {
			user.setReferCode("%" + SQLTools.transfer(s_referCode) + "%");
		}
		if (!StringUtils.isNullOrEmpty(s_roleId)) {
			user.setRoleId(Integer.parseInt(s_roleId));
		} else {
			user.setRoleId(null);
		}
		if (!StringUtils.isNullOrEmpty(s_isStart)) {
			user.setIsStart(Integer.parseInt(s_isStart));
			logger.info("==================s_isStart:" + Integer.parseInt(s_isStart));
		} else {
			user.setIsStart(null);
		}
		// 获取CARD_TYPE 类型
		DataDictionary dataDictionary = new DataDictionary();
		dataDictionary.setTypeCode("CARD_TYPE");
		try {
			List<DataDictionary> cardTypeList = dataDictionaryService.getDataList(dataDictionary);
			model.addAttribute("cardTypeList", cardTypeList);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		// 封装page对象
		PageSupport page = new PageSupport();
		page.setPageSize(3);
		try {
			page.setTotalCount(userService.getUserCount(user));
		} catch (Exception e) {
			e.printStackTrace();
			page.setPageCount(0);
		}
		
		if (page.getTotalCount() > 0) {
			
			if (currentpage != null) {
				page.setCurrentPageNo(Integer.valueOf(currentpage));
			} else {
				page.setCurrentPageNo(1);
			}
			if (page.getCurrentPageNo() <= 0) {
				page.setCurrentPageNo(1);
			}
			// 如果用户输入的值大于总页数，那么显示总页数的值
			if (page.getCurrentPageNo() > page.getPageCount()) {
				page.setCurrentPageNo(page.getPageCount());
			}
			user.setStarNum((page.getCurrentPageNo() - 1) * page.getPageSize());
			user.setPageSize(page.getPageSize());
			List<User> userList = null;
			try {
				userList = userService.getUserList(user);// 查询用户列表
			} catch (Exception e) {
				e.printStackTrace();
				if (page == null) {
					userList = null;
					page = new PageSupport();
					page.setItems(null);
				}
			}
			page.setItems(userList);
		} else {
			page.setItems(null);
		}
		logger.debug("=======总页数：" + page.getPageCount());
		logger.debug("=======总记录数：" + page.getTotalCount());
		logger.debug("=======后几页数：" + page.getNextPages());
		model.addAttribute("page", page);
		model.addAttribute("s_loginCode", s_loginCode);
		model.addAttribute("s_referCode", s_referCode);
		model.addAttribute("s_roleId", s_roleId);
		model.addAttribute("s_isStart", s_isStart);
		return "backend/userlist";
	}

	/**
	 * 获取用户角色列表
	 */
	@RequestMapping(value = "/backend/loadUserTypeList.html", produces = { "text/html;charset=UTF-8" })
	@ResponseBody
	public Object getUserTypeList(@RequestParam(value = "s_role", required = false) String s_role) {
		List<DataDictionary> list = null;
		if (s_role != null || !"".equals(s_role)) {
			DataDictionary dataDictionary = new DataDictionary();
			dataDictionary.setTypeCode("USER_TYPE");
			try {
				list = dataDictionaryService.getDataList(dataDictionary);
				JSONArray ja = JSONArray.fromObject(list);
				logger.info("dataList==========" + ja);
				return ja.toString();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	// 判断用户名唯一验证
	@RequestMapping(value = "/backend/logincodeisexit.html", produces = { "text/html;charset=UTF-8" })
	@ResponseBody
	public Object IsExistUserCode(@RequestParam(value = "loginCode", required = false) String loginCode,
			@RequestParam(value = "id", required = false) String id) {
		User user = new User();
		user.setLoginCode(loginCode);
		if (!id.equals("-1")) {
			user.setId(Integer.parseInt(id));
			logger.debug("验证loginCode ======id:为" + id);// 修改时需要用上
		}
		try {
			if (userService.isLoginCodeExist(user)) {
				return "repeat";
			} else {
				return "only";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "failed";
		}

	}

	// 处理文件上传
	@RequestMapping(value = "/backend/upload.html", produces = { "text/html;charset=UTF-8" })
	@ResponseBody
	public Object uploadFile(HttpServletRequest req,
			@RequestParam(value = "a_fileInputID", required = false) MultipartFile a_idCard,
			@RequestParam(value = "a_fileInputBank", required = false) MultipartFile a_bankCard,
			@RequestParam(value = "m_fileInputID", required = false) MultipartFile m_idCard,
			@RequestParam(value = "m_fileInputBank", required = false) MultipartFile m_bankCard) {

		String path = req.getSession().getServletContext().getRealPath("/statics" + File.separator + "uploadFiles");
		logger.debug("上下文路径=====" + path);
		int fileSize = 50000;
		DataDictionary dataDictionary = new DataDictionary();
		dataDictionary.setTypeCode("PERSONALFILE_SIZE");
		List<DataDictionary> dataList = null;
		try {
			dataList = dataDictionaryService.getDataList(dataDictionary);
			if (dataList.size() == 1) {
				fileSize = Integer.valueOf(dataList.get(0).getValueName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (a_idCard != null) {
			// 获取源文件名
			String fileOldName = a_idCard.getOriginalFilename();
			String suffix = FilenameUtils.getExtension(fileOldName);
			if (a_idCard.getSize() > fileSize) {
				return "1";
			} else if (suffix.equalsIgnoreCase("jpg") || suffix.equalsIgnoreCase("png")
					|| suffix.equalsIgnoreCase("jpeg") || suffix.equalsIgnoreCase("pneg")) {
				// 重置文件名
				String fileNewName = System.currentTimeMillis() + RandomUtils.nextInt(1000000) + "_idCard.jpg";
				File target = new File(path, fileNewName);
				if (!target.exists()) {
					if (target.mkdirs()) {
						logger.debug("创建的路径为：" + target.getAbsolutePath());
					}
				}
				try {
					a_idCard.transferTo(target);
				} catch (IOException e) {
					e.printStackTrace();
				}
				String url = req.getContextPath() + "/statics/uploadFiles/" + fileNewName;
				logger.info("文件路径是：" + url);
				return url;
			} else {
				return "2";
			}
		}

		if (a_bankCard != null) {
			// 获取源文件名
			String fileOldName = a_bankCard.getOriginalFilename();
			String suffix = FilenameUtils.getExtension(fileOldName);
			if (a_bankCard.getSize() > fileSize) {
				return "1";
			} else if (suffix.equalsIgnoreCase("jpg") || suffix.equalsIgnoreCase("png")
					|| suffix.equalsIgnoreCase("jpeg") || suffix.equalsIgnoreCase("pneg")) {
				// 重置文件名
				String fileNewName = System.currentTimeMillis() + RandomUtils.nextInt(1000000) + "_bankCard.jpg";
				File target = new File(path, fileNewName);
				if (!target.exists()) {
					if (target.mkdirs()) {
						logger.debug("创建的路径为：" + target.getAbsolutePath());
					}
				}
				try {
					a_bankCard.transferTo(target);
				} catch (IOException e) {
					e.printStackTrace();
				}
				String url = req.getContextPath() + "/statics/uploadFiles/" + fileNewName;
				logger.info("文件路径是：" + url);
				return url;
			} else {
				return "2";
			}
		}

		if (m_idCard != null) {
			// 获取源文件名
			String fileOldName = m_idCard.getOriginalFilename();
			String suffix = FilenameUtils.getExtension(fileOldName);
			if (m_idCard.getSize() > fileSize) {
				return "1";
			} else if (suffix.equalsIgnoreCase("jpg") || suffix.equalsIgnoreCase("png")
					|| suffix.equalsIgnoreCase("jpeg") || suffix.equalsIgnoreCase("pneg")) {
				// 重置文件名
				String fileNewName = System.currentTimeMillis() + RandomUtils.nextInt(1000000) + "_idCard.jpg";
				File target = new File(path, fileNewName);
				if (!target.exists()) {
					if (target.mkdirs()) {
						logger.debug("创建的路径为：" + target.getAbsolutePath());
					}
				}
				try {
					m_idCard.transferTo(target);
				} catch (IOException e) {
					e.printStackTrace();
				}
				String url = req.getContextPath() + "/statics/uploadFiles/" + fileNewName;
				logger.info("文件路径是：" + url);
				return url;
			} else {
				return "2";
			}
		}

		if (m_bankCard != null) {
			// 获取源文件名
			String fileOldName = m_bankCard.getOriginalFilename();
			String suffix = FilenameUtils.getExtension(fileOldName);
			if (m_bankCard.getSize() > fileSize) {
				return "1";
			} else if (suffix.equalsIgnoreCase("jpg") || suffix.equalsIgnoreCase("png")
					|| suffix.equalsIgnoreCase("jpeg") || suffix.equalsIgnoreCase("pneg")) {
				// 重置文件名
				String fileNewName = System.currentTimeMillis() + RandomUtils.nextInt(1000000) + "_bankCard.jpg";
				File target = new File(path, fileNewName);
				if (!target.exists()) {
					if (target.mkdirs()) {
						logger.debug("创建的路径为：" + target.getAbsolutePath());
					}
				}
				try {
					m_bankCard.transferTo(target);
				} catch (IOException e) {
					e.printStackTrace();
				}
				String url = req.getContextPath() + "/statics/uploadFiles/" + fileNewName;
				logger.info("文件路径是：" + url);
				return url;
			} else {
				return "2";
			}
		}
		return null;
	}

	// 添加用户
	@RequestMapping(value = "/backend/adduser.html")
	public String addUser(@ModelAttribute("addUser") User addUser, HttpSession session,
			@RequestParam(value = "currentpage") String currentpage) {
		if (session.getAttribute(Contains.USER_SESSION) == null) {
			return "redirect:/";
		} else {
			addUser.setCreateTime(new Date());
			addUser.setPassword(addUser.getIdCard().substring(addUser.getIdCard().length() - 6));
			addUser.setPassword2(addUser.getIdCard().substring(addUser.getIdCard().length() - 6));
			addUser.setReferId(((User) session.getAttribute(Contains.USER_SESSION)).getId());
			addUser.setReferCode(((User) session.getAttribute(Contains.USER_SESSION)).getLoginCode());
			addUser.setLastUpdateTime(new Date());
			try {
				userService.addUser(addUser);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "redirect:/backend/userlist.html";
		}

	}

	// 查看用戶
	@RequestMapping(value = "/backend/getuser.html", produces = { "text/html;charset=UTF-8" })
	@ResponseBody
	public Object getUser(@RequestParam(value = "id", required = false) String id) {
		logger.info("传递进来的id=====" + id);
		if (id == null || "".equals(id)) {
			return "nodata";
		} else {
			try {
				User _user = userService.getUserById(Integer.parseInt(id));
				JsonConfig jsonConfig = new JsonConfig();// 设置json日期格式
				jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
				JSONObject userJosn = JSONObject.fromObject(_user, jsonConfig);
				logger.info("根據id得到的json对象：" + userJosn.toString());
				return userJosn.toString();
			} catch (Exception e) {
				e.printStackTrace();
				return "failed";
			}
		}
	}

	// 修改用户
	@RequestMapping(value = "/backend/modifyuser.html")
	public String modifyUser(@ModelAttribute("user") User user,
			@RequestParam(value = "currentPage") String currentPage) {
		logger.debug("当前页码==============" + currentPage);
		user.setLastUpdateTime(new Date());
		try {
			userService.modifyUser(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/backend/userlist.html?currentpage=" + currentPage;
	}

	// 删除附件
	@RequestMapping(value = "/backend/delpic.html", produces = { "text/html;charset=UTF-8" })
	@ResponseBody
	public Object delUserPic(@RequestParam(value = "id", required = false) String id,
			@RequestParam(value = "picpath", required = false) String picpath, HttpServletRequest req) {
		String result = "";
		if (picpath == null || picpath.equals("")) {
			result = "success";
		} else {
			String[] path = picpath.split("/");
			String targetPath = req.getSession().getServletContext()
					.getRealPath("/" + path[1] + File.separator + path[2] + File.separator + path[3]);
			logger.info("服务器中的文件路径" + targetPath);
			File file = new File(targetPath);
			if (file.exists()) {
				if (file.delete()) {
					if (id.equals("0")) {
						logger.debug("===============图片删除成功！");
						result = "success";
					} else {// 修改
						User user = new User();
						user.setId(Integer.parseInt(id));
						if (targetPath.indexOf("_idCard.jpg") != -1) {
							user.setIdCardPicPath(targetPath);
							logger.debug("===============身份证删除成功！");
						} else if (targetPath.indexOf("_bankCard.jpg") != -1) {
							logger.debug("===============银行卡删除成功！");
							user.setBankPicPath(targetPath);
						}
						logger.debug("修改后的路径====" + targetPath);
						try {
							if (userService.delPic(user)) {
								result = "success";
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
		return result;
	}

	/**
	 * 删除用户，除注册会员以外其他的不能删除
	 */
	@RequestMapping(value = "/backend/deluser.html", produces = { "text/html;charset=UTF-8" })
	@ResponseBody
	public Object delUser(@RequestParam(value = "delId") String delId,
			@RequestParam(value = "delIdCardPicPath") String delIdCardPicPath,
			@RequestParam(value = "delBankPicPath") String delBankPicPath,
			@RequestParam(value = "delUserType") String delUserType, HttpServletRequest req) {
		String result = "failed";
		if (!delUserType.equals("1")) {
			result = "noallow";
		} else {
			if (this.delUserPic(delId, delIdCardPicPath, req).equals("success")
					&& this.delUserPic(delId, delBankPicPath, req).equals("success"))
				try {
					if (userService.delUserById(Integer.parseInt(delId))) {
						result = "success";
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
		return result;
	}

}

package org.slsale.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.slsale.common.Contains;
import org.slsale.pojo.Authority;
import org.slsale.pojo.Function;
import org.slsale.pojo.Role;
import org.slsale.pojo.RoleFunctions;
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
 * Ȩ�޿�������
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
	/**
	 * ����Ȩ����ҳ
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
	 * ��ȡ�����б�
	 */
	@RequestMapping(value = "/backend/functions.html", produces = { "text/html;charset=UTF-8" })
	@ResponseBody
	public Object getFunctionsList() {
		String result = "";
		// ͨ��roleId��ȡȨ���б�
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
					roleFunc.setSubFunctionList(functionService.getFunctionList(_function));// ����functionid��ȡfunction�б�
					roleFuncList.add(roleFunc);
				}
			}
			JSONArray jsonArray = JSONArray.fromObject(roleFuncList);
			result = jsonArray.toString();
			logger.debug("==================��ȡList<RoleFunctions>��ֵ�ǣ�" + result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * Ĭ�Ϲ�ѡת̬
	 * @param fid
	 * @param rid
	 * @return
	 */
	@RequestMapping(value="/backend/getAuhorityDefault.html",produces={"text/html;charset=UTF-8"})
	@ResponseBody
	public Object getAthorityDefault(@RequestParam(value="fid")Integer fid,@RequestParam(value="rid")Integer rid){
		String result ="nodata";
		Authority athority = new Authority();
		athority.setFunctionId(fid);
		athority.setRoleId(rid);
		
		try {
			Authority _athority = authorityService.getAuthorityByFunctionIdAndRoleId(athority);
			if(_athority!=null){
				result = "success";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}
	

}
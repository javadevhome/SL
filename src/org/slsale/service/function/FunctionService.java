package org.slsale.service.function;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.slsale.pojo.Authority;
import org.slsale.pojo.Function;

public interface FunctionService {

	/**
	 * ����roleId��ȡ�������б�
	 */
	public List<Function> getMainFunctionList(Authority authority) throws Exception;

	/**
	 * ����function���е�parentId��ȡ�ӹ����б�
	 */
	public List<Function> getSubFunctionList(Function function) throws Exception;

	/**
	 * ����function���е�parentId��ȡ�������б�
	 */
	public List<Function> getFunctionList(Function function) throws Exception;

	/**
	 * ��ȡurl�б�
	 * 
	 * @param roleId
	 * @return
	 */
	public List<Function> getFunctionUrlListByRoleId(@Param("roleId") Integer roleId)throws Exception;
}

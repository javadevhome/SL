package org.slsale.service.function;

import java.util.List;

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
	public List<Function> getFunctionList(Function function)throws Exception;
}
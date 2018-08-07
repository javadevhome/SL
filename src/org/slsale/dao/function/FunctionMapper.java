package org.slsale.dao.function;

import java.util.List;

import org.slsale.pojo.Authority;
import org.slsale.pojo.Function;

/**
 * ���ܽӿ�
 * 
 * @author Administrator
 *
 */
public interface FunctionMapper {
	/**
	 * ����roleId��ȡ�������б�
	 */
	public List<Function> getMainFunctionList(Authority authority) throws Exception;

	/**
	 * ����function���е�parentId��ȡ�ӹ����б�
	 */
	public List<Function> getSubFunctionList(Function function) throws Exception;
	
	/**
	 * ͨ��roleId��ȡ�����б�
	 */
	public List<Function> getFunctionList(Function function)throws Exception;

}

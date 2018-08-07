package org.slsale.service.function;

import java.util.List;

import org.slsale.pojo.Authority;
import org.slsale.pojo.Function;

public interface FunctionService {

	/**
	 * 根据roleId获取主功能列表
	 */
	public List<Function> getMainFunctionList(Authority authority) throws Exception;

	/**
	 * 根据function表中的parentId获取子功能列表
	 */
	public List<Function> getSubFunctionList(Function function) throws Exception;
	/**
	 * 根据function表中的parentId获取主功能列表
	 */
	public List<Function> getFunctionList(Function function)throws Exception;
}

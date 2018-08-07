package org.slsale.pojo;

import java.util.List;

import org.slsale.pojo.Function;

/**
 * RoleFunctions
 * 
 * @author bdqn_hl
 * @date 2014-2-21
 */
public class RoleFunctions {

	private Function mainFunction;
	private List<Function> subFunctionList;

	public Function getMainFunction() {
		return mainFunction;
	}

	public void setMainFunction(Function mainFunction) {
		this.mainFunction = mainFunction;
	}

	public List<Function> getSubFunctionList() {
		return subFunctionList;
	}

	public void setSubFunctionList(List<Function> subFunctionList) {
		this.subFunctionList = subFunctionList;
	}

}

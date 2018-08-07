package org.slsale.service.function;

import java.util.List;

import javax.annotation.Resource;

import org.slsale.dao.function.FunctionMapper;
import org.slsale.pojo.Authority;
import org.slsale.pojo.Function;
import org.springframework.stereotype.Service;


@Service("functionService")
public class FunctionServiceImpl implements FunctionService {
	@Resource
	private FunctionMapper functionMapper;

	@Override
	public List<Function> getMainFunctionList(Authority authority) throws Exception {
		return functionMapper.getMainFunctionList(authority);
	}

	@Override
	public List<Function> getSubFunctionList(Function function) throws Exception {
		return functionMapper.getSubFunctionList(function);
	}
	@Override
	public List<Function> getFunctionList(Function function) throws Exception {
		
		return functionMapper.getFunctionList(function);
	}

}

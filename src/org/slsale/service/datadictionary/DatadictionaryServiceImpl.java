package org.slsale.service.datadictionary;

import java.util.List;

import javax.annotation.Resource;

import org.slsale.dao.dictionary.DataDictionaryMapper;
import org.slsale.pojo.DataDictionary;
import org.springframework.stereotype.Service;
@Service
public class DatadictionaryServiceImpl implements DataDictionaryService {
	@Resource
	private DataDictionaryMapper dataDictionaryMapper;
	@Override
	public List<DataDictionary> getDataList(DataDictionary dataDictionary) throws Exception {
		return dataDictionaryMapper.getDataList(dataDictionary);
	}

}

package org.slsale.service.datadictionary;

import java.util.List;

import org.slsale.pojo.DataDictionary;

public interface DataDictionaryService {
	// ��ȡ�����ֵ��б�
	public List<DataDictionary> getDataList(DataDictionary dataDictionary) throws Exception;
}

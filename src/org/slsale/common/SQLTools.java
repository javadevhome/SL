package org.slsale.common;

/**
 * sql ��ֹע�빥��������
 * 
 * @author Administrator
 *
 */
public class SQLTools {
	public static String transfer(String keyword) {
		if (keyword.contains("%") || keyword.contains("_")) {
			keyword = keyword.replaceAll("\\\\", "\\\\\\\\").
					replaceAll("\\%", "\\\\%").replaceAll("\\_", "\\\\_");
		}
		return keyword;

	}

}

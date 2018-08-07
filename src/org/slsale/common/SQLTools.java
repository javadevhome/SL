package org.slsale.common;

/**
 * sql 防止注入攻击工具类
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

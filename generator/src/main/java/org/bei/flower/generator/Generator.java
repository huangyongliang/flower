package org.bei.flower.generator;




import org.bei.flower.generator.utils.MybatisGeneratorUtil;
import org.bei.flower.generator.utils.PropertiesFileUtil;

import java.util.HashMap;
import java.util.Map;


/**
 * 
 * 代码生成类
 * @author yubei
 * @date 2017-12-05
 */
public class Generator {

	private static String DATABASE = "loancore";
	private static String TABLE_PREFIX = "asset_repay_pla";
	private static String PACKAGE_NAME = "com.hsjry.asset.dal";
	private static String JDBC_DRIVER = PropertiesFileUtil.getInstance("generator").get("generator.jdbc.driver");
	private static String JDBC_URL = PropertiesFileUtil.getInstance("generator").get("generator.jdbc.url");
	private static String JDBC_USERNAME = PropertiesFileUtil.getInstance("generator").get("generator.jdbc.username");
	private static String JDBC_PASSWORD = PropertiesFileUtil.getInstance("generator").get("generator.jdbc.password");
	// 需要insert后返回主键的表配置，key:表名,value:主键名
	private static Map<String, String> LAST_INSERT_ID_TABLES = new HashMap<String, String>();
	static {
		//LAST_INSERT_ID_TABLES.put("id", "id");
	}

	/**
	 * 自动代码生成
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		MybatisGeneratorUtil.generator(JDBC_DRIVER, JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD, DATABASE, TABLE_PREFIX, PACKAGE_NAME, LAST_INSERT_ID_TABLES);
	}

}

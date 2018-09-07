package org.bei.flower.generator.utils;

import org.apache.commons.lang.ObjectUtils;
import org.apache.velocity.VelocityContext;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 代码生成类
 *
 * @author yubei
 * @date 2017-12-05
 */
public class MybatisGeneratorUtil {
    // 基本路径
    private static String bath_path = "/"+MybatisGeneratorUtil.class.getResource("/").getPath().replaceFirst("/", "")
            .replace("/target/classes/", "") + "/src/main/resources";

    // generatorConfig模板路径
    private static String generatorConfig_vm = bath_path + "/templates/generator.vm";

    /**
     * 根据模板生成generatorConfig.xml文件
     *
     * @param jdbc_driver   驱动路径
     * @param jdbc_url      链接
     * @param jdbc_username 帐号
     * @param jdbc_password 密码
     * @param database      数据库
     * @param table_prefix  表前缀
     * @param package_name  包名
     */
    public static void generator(String jdbc_driver, String jdbc_url, String jdbc_username, String jdbc_password,
                                 String database, String table_prefix, String package_name,
                                 Map<String, String> last_insert_id_tables) throws Exception {
        String generatorConfig_xml = "/"+MybatisGeneratorUtil.class.getResource("/").getPath().replaceFirst("/", "")
                .replace("/target/classes/", "") + "/src/main/resources/generatorConfig.xml";
        String targetProject = "";
        String basePath = "/"+MybatisGeneratorUtil.class.getResource("/").getPath().replace("/target/classes/", "")
                .replace(targetProject, "").replaceFirst("/", "");

        targetProject = basePath + targetProject;
        String sql = "SELECT table_name FROM INFORMATION_SCHEMA.TABLES WHERE table_schema = '" + database
                + "' AND table_name LIKE '" + table_prefix + "_%';";

        System.out.println("========== 开始生成generatorConfig.xml文件 ==========");
        List<Map<String, Object>> tables = new ArrayList<Map<String, Object>>();
        try {
            VelocityContext context = new VelocityContext();
            Map<String, Object> table;

            // 查询定制前缀项目的所有表
            JdbcUtil jdbcUtil = new JdbcUtil(jdbc_driver, jdbc_url, jdbc_username, jdbc_password);
            List<Map> result = jdbcUtil.selectByParams(sql, null);
            System.out.println(result);
            for (Map map : result) {
                table = new HashMap<String, Object>();
                table.put("table_name", map.get("TABLE_NAME"));
                table.put("model_name", StringUtil.lineToHump(ObjectUtils.toString(map.get("TABLE_NAME"))));
                tables.add(table);
            }
            jdbcUtil.release();

            context.put("tables", tables);
            context.put("entityPackageName", package_name + ".dao.model");
            context.put("entityPath", (package_name + ".dao.model").replaceAll("\\.", "/"));
            context.put("mapperXMLPackageName", package_name + ".dao.mapper");


            context.put("targetProject", targetProject);

            context.put("driverClass",jdbc_driver);
            context.put("connectionURL",jdbc_url);
            context.put("userId",jdbc_username);
            context.put("password",jdbc_password);

            VelocityUtil.generate(generatorConfig_vm, generatorConfig_xml, context);
        } catch (Exception e) {
            // 删除旧代码
            deleteDir(new File(targetProject + "/src/main/java/" + package_name.replaceAll("\\.", "/") + "/dao/model"));
            deleteDir(
                    new File(targetProject + "/src/main/java/" + package_name.replaceAll("\\.", "/") + "/dao/mapper"));
            e.printStackTrace();
        }
        System.out.println("========== 结束生成generatorConfig.xml文件 ==========");

        System.out.println("========== 开始运行MybatisGenerator ==========");
        List<String> warnings = new ArrayList<String>();
        File configFile = new File(generatorConfig_xml);
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(true);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
        for (String warning : warnings) {
            System.out.println("warning:"+warning);
        }
        System.out.println("========== 结束运行MybatisGenerator ==========");

    }

    // 递归删除非空文件夹
    public static void deleteDir(File dir) {
        if (dir.isDirectory()) {
            File[] files = dir.listFiles();
            for (int i = 0; i < files.length; i++) {
                deleteDir(files[i]);
            }
        }
        dir.delete();
    }

}

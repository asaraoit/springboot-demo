package com.sst;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ResourceBundle;

public class DbGenerator {


    public static void main(String[] args) {
        generator();
    }

    private static void generator() {
        // 代码生成器
        ResourceBundle rb = ResourceBundle.getBundle("application-dev");
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/src/main/java"); // 输出目录
        gc.setFileOverride(true);// 是否覆盖文件
//        gc.setActiveRecord(true);// 开启 activeRecord 模式
//        gc.setEnableCache(false);// XML 二级缓存
        gc.setAuthor("IAskWind");
        gc.setXmlName("%sMapper").setMapperName("%sDao");// 自定义文件命名，注意 %s 会自动填充表实体属性！
        gc.setServiceName("%sService").setServiceImplName("%sServiceImpl");
        gc.setControllerName("%sController");
        gc.setOpen(false); //是否打开输出目录
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(rb.getString("spring.datasource.url"));
        // dsc.setSchemaName("public");
        dsc.setDriverName(rb.getString("spring.datasource.driver-class-name"));
        dsc.setUsername(rb.getString("spring.datasource.username"));
        dsc.setPassword(rb.getString("spring.datasource.password"));
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setEntity("model");
        pc.setService("service");
        pc.setServiceImpl("service.impl");
        pc.setMapper("dao");
        pc.setXml("xml");
        pc.setController("controller");
        pc.setParent("com.sst");
        mpg.setPackageInfo(pc);

        TemplateConfig tc = new TemplateConfig();
        tc.setController(null);//不生成Controller实体
        tc.setXml(null);
        tc.setService(null);
        tc.setServiceImpl(null);
        tc.setMapper(null);
        mpg.setTemplate(tc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setRestControllerStyle(true);//生成 @RestController 控制器
        strategy.setEntityLombokModel(true);//是否为lombok模型（默认 false）
        strategy.setExclude("sys_role","sys_role_user","sys_user");
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }
}

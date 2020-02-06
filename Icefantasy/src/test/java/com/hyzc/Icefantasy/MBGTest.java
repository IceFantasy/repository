package com.hyzc.Icefantasy;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.api.ShellCallback;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;


public class MBGTest {
	
	public static void main(String[] args) throws Exception {
		
		List<String> warnings = new ArrayList<>();
		boolean overwrite = true;
		File configFile = new File("mbg.xml");// io api 熟悉这些方法 ， file后缀 
		ConfigurationParser cp = new ConfigurationParser(warnings);
		Configuration config = cp.parseConfiguration(configFile);
		ShellCallback callback = new DefaultShellCallback(overwrite);
		MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
		myBatisGenerator.generate(null);
	}

}

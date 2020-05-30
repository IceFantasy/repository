package com.filemaker.icefantasy.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.Map;

import org.springframework.util.ResourceUtils;

import com.alibaba.fastjson.JSONArray;

import net.sf.json.JSONObject;

public class HMSJSONUtil {

	/**
	 * json string 转换为 map 对象
	 * 
	 * @param jsonObj
	 * @return
	 */
	public static Map<Object, Object> jsonToMap(Object jsonObj) {
		JSONObject jsonObject = JSONObject.fromObject(jsonObj);
		Map<Object, Object> map = jsonObject;
		return map;
	}

	/**
	 * json string 转换为 list 对象
	 * 
	 * @param jsonObj
	 * @return
	 */
	public static <T> List<T> jsonToList(String jsonString, Class<T> clazz) {
		@SuppressWarnings("unchecked")
		List<T> ts = JSONArray.parseArray(jsonString, clazz);
		return ts;
	}
	
	/**
	 * 获取类路径下 filemaker-confing.json 数据库 filemaker 配置信息
	 * @return 计会芳 ： 王淳诚 25887311267@qq.com 2020年5月27日 上午9:13:55
	 */
	public static JSONObject getConfingJson() {
		String path = "";
		try {
			path = ResourceUtils.getFile("classpath:filemaker-confing.json").getPath();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		JSONObject congfigJson = null;
		try {
			String congfigJsonStr = readJsonFile(path);
			congfigJson = JSONObject.fromObject(congfigJsonStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return congfigJson;
	}
	
	/**
	 * 按行读取文件 
	 * @param path
	 * @return
	 * @throws Exception
	 * 计会芳 ： 王淳诚  25887311267@qq.com 
	 * 2020年5月27日  上午9:35:50
	 */
	public String ReadFile(String path) throws Exception {
		File file = new File(path);
		BufferedReader reader = null;
		String laststr = "";
		try {
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			int line = 0;
			// 一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null) {
				// 显示行号
				laststr = laststr + tempString;
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
		return laststr;
	}
	
	/**
     * 读取json文件
     * @param fileName json文件名
     * @return 返回json字符串
     */
    public static String readJsonFile(String path) {
    	File jsonFile = new File(path);
        String jsonStr = "";
        try {
            //File jsonFile = new File(fileName);
            FileReader fileReader = new FileReader(jsonFile);
            Reader reader = new InputStreamReader(new FileInputStream(jsonFile), "utf-8");
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            fileReader.close();
            reader.close();
            jsonStr = sb.toString();
            return jsonStr;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    
	
}

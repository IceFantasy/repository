package com.filemaker.icefantasy.dao.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.web.multipart.MultipartFile;

import com.filemaker.icefantasy.util.CommUtils;
import com.filemaker.icefantasy.util.HMSJSONUtil;
import com.github.pagehelper.util.StringUtil;

import net.sf.json.JSONObject;

public class FileMakerRequestUtil {
	
	private static String databaseUrl;
	
	static { // {}在创建对象时加载 ,static在启动是加载注意类加载顺序  
		JSONObject confingJson = HMSJSONUtil.getConfingJson();// 获取配置文件
		JSONObject databasesJson = confingJson.getJSONObject("databases").getJSONObject("0");// 第0号数据库的配置
		databaseUrl = databasesJson.getString("databaseUrl");
	}
	
	/**
	 * 根据 layouts 添加数据
	 * @param token
	 * @param layouts
	 * @param addData
	 * @return
	 * 计会芳 ： 王淳诚  25887311267@qq.com 
	 * 2020年5月28日  上午9:44:51
	 */
	public static String insertPost(String token, String layouts, String addData) {
		
		HttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(databaseUrl + "/layouts/" + layouts + "/records");
		
		httpPost.setHeader("Content-Type", "application/json");
		httpPost.setHeader("Authorization", "Bearer " + token);
		
		StringEntity entity = new StringEntity(addData, ContentType.APPLICATION_JSON);
		httpPost.setEntity(entity);
		HttpResponse response;
		StringBuilder builder = null;
		try {
			response = httpClient.execute(httpPost);
			System.out.println("添加数据结果 " + response);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				InputStream inputStream = response.getEntity().getContent();
				builder = new StringBuilder();
				byte[] b = new byte[1024];
				if (-1 != inputStream.read(b)) {
					builder.append(new String(b));
					return builder.toString();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "上传数据失败";
	}
	
	public static String selectGet(String token, String layouts, String recordId) {
		
		String result = "{\"msg\": \"没有查询到任何数据，请检查入参 layouts : "+layouts+", recordId : "+recordId+"\""
				+ ",\"state\": \"200\"}";
		
		String url = databaseUrl + "/layouts/" + layouts + "/records/";
		if (StringUtil.isEmpty(layouts)) { // 当没有layouts参数不存在不进行查找
			return result;
		}else if (!StringUtil.isEmpty(recordId)) {
			return sendGet(token, url + recordId);
		}else {
			return sendGet(token, url);
		}
	}
	// 发送 get 请求查询数据
	private static String sendGet(String token, String url) {
		JSONObject jsonObject = null;
		CloseableHttpClient client = null;
		CloseableHttpResponse response = null;
		
		client = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url);
		httpGet.setHeader("Content-Type", "application/json");
		httpGet.setHeader("Authorization", "Bearer " + token);
		
		StringBuilder builder = null;
		
		try {
			response = client.execute(httpGet);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity httpEntity = response.getEntity();
				String respStr = EntityUtils.toString(httpEntity,"utf-8");
				return respStr;
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				response.close();
				client.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * 添加修改数据的方法
	 * @param token
	 * @param layouts
	 * @param updateData
	 * @param recordId 
	 * @return
	 * lenovo ： 王淳诚  25887311267@qq.com 
	 * 2020年5月30日  上午10:10:44
	 */
	public static String updatetPost(String token, String layouts,// 
			String recordId, String updateData) {// 
		
		String result = "{\"msg\": \"No data found, please check the input parameter\""
				+ ",\"state\": \"200\""
				+ ",\"param\": {\"layouts\": \""+layouts+"\",\"recordId\":\""+recordId+"\""
						+ ",\"updateData\":\""+updateData+"\"}}";
		
		boolean isNotPost = StringUtil.isEmpty(token) ||  StringUtil.isEmpty(layouts) // 
				||  StringUtil.isEmpty(recordId) ||  StringUtil.isEmpty(updateData);
		if (isNotPost) { // 不能进行查询
			return result;
		}
		
		String url = databaseUrl + "/layouts/" + layouts + "/records/";
		HttpClient httpClient = HttpClients.createDefault();
		HttpPatch httpPatch = new HttpPatch(url + recordId);
		httpPatch.setHeader("Content-Type", "application/json");
		httpPatch.setHeader("Authorization", "Bearer " + token);
		
		StringEntity entity = new StringEntity(updateData, ContentType.APPLICATION_JSON);
		httpPatch.setEntity(entity);
		StringBuilder builder = null;
		try {
			HttpResponse response = httpClient.execute(httpPatch);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				InputStream inputStream = response.getEntity().getContent();
				builder = new StringBuilder();
				byte[] b = new byte[1024];
				if (-1 != inputStream.read(b)) {
					builder.append(new String(b));
				}
//				System.out.println(builder.toString()); //
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (builder == null) {
			return null;
		}
		return builder.toString();
	}
	
	/**
	 * 根据布局和recordid进行删除数据
	 * @param token
	 * @param layouts
	 * @param recordId
	 * @return
	 * lenovo ： 王淳诚  25887311267@qq.com 
	 * 2020年5月30日  上午10:36:28
	 */
	public static String deleteGet(String token, String layouts, String recordId) {
		
		String result = "{\"msg\": \"Failed to delete layout "+layouts+" recordId "+recordId+" data, please check the input parameter\""
				+ ",\"state\": \"100\"}";
		
		boolean isNotDelete =  StringUtil.isEmpty(token) ||  StringUtil.isEmpty(layouts) // 
				||  StringUtil.isEmpty(recordId);
		if (isNotDelete) {
			return result;
		}
		
		String url = databaseUrl + "/layouts/" + layouts + "/records/";
		HttpClient httpClient = HttpClients.createDefault();
		HttpDelete httpDelete = new HttpDelete(url + recordId);
		httpDelete.setHeader("Content-Type", "application/json");
		httpDelete.setHeader("Authorization", "Bearer " + token);
		
		StringBuilder builder = null;
		try {
			HttpResponse response = httpClient.execute(httpDelete);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				InputStream inputStream = response.getEntity().getContent();
				builder = new StringBuilder();
				byte[] b = new byte[1024];
				if (-1 != inputStream.read(b)) {
					builder.append(new String(b));
				}
//				System.out.println(builder.toString()); //
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (UnsupportedOperationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (builder == null || builder.toString() == "") {
			return null;
		}
		return builder.toString();
	}
	
	/**
	 * 通过 File对象或文件路径进行上传
	 * @param token
	 * @param layouts
	 * @param recordId
	 * @param field
	 * @param file
	 * @param filePath
	 * @return
	 * lenovo ： 王淳诚  25887311267@qq.com 
	 * 2020年5月30日  上午11:22:08
	 */
	public static String fileUploadPost(String token,//
			String layouts, String recordId, String field,//
			MultipartFile file,//
			String filePath) {//
		
		String result = "{\"msg\": \"Upload failed, please check the file path or file size\""
				+ ",\"state\": \"100\"}";
		
		boolean isNotFileUPload = (file == null &&  StringUtil.isEmpty(filePath));
		if (isNotFileUPload) {
			return result;
		}
		File uploadfile = null;
		if (file != null) { // 通过文件对象进行上传
			uploadfile = CommUtils.mulipartTofile(file);
		}else if (!StringUtil.isEmpty(filePath)) { // 通过路径进行上传
			uploadfile = new File(filePath);// new File("C:/springCity/files/fileupload/fangzi.jpg");
		}
			
		// 2. 进行上传
		String url = databaseUrl + "/layouts/" + layouts + "/records/" //
				+ recordId + "/containers/"+ field +"/1";
		HttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
//		httpPost.setHeader("Content-Type", "multipart/form-data");
		httpPost.setHeader("Authorization", "Bearer " + token);
		
		// 添加body form-data 参数 key:upload value:fileObj
		MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.addBinaryBody("upload", uploadfile);
        HttpEntity multipart = builder.build();
        httpPost.setEntity(multipart);
		try {
			HttpResponse response = httpClient.execute(httpPost);
			System.out.println("图片上传：" + response);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				InputStream inputStream = response.getEntity().getContent();
				StringBuilder builderStr = new StringBuilder();
				byte[] b = new byte[1024];
				if (-1 != inputStream.read(b)) {
					builderStr.append(new String(b));
				}
				return builderStr.toString();
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (UnsupportedOperationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// https://aliapi.goodluckpacking.com/fmi/data/v1/databases/test/layouts/fuzeren/?_offset=96&_limit=6
	public static String findGet(String token, String layouts, String complexParam) {
		String result = "{\"msg\": \"没有查询到任何数据，请检查入参   token : "+token+" , layouts : "+layouts+", complexParam : "+complexParam+"\""
				+ ",\"state\": \"100\"}";
		String url = databaseUrl + "/layouts/" + layouts + "/?" + complexParam;
		boolean isNotFind = StringUtil.isEmpty(token) || StringUtil.isEmpty(layouts) 
				|| StringUtil.isEmpty(complexParam);
		if (isNotFind) {
			return result;
		}else {
			return sendGet(token, url);
		}
	}

}

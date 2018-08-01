package net.riking.libraryapi.controller;

import net.riking.libraryapi.utils.HttpUtil;
import net.sf.json.JSONObject;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;



@SuppressWarnings("unused")
public class request {
	
	private static final String appid="wxe58b1ca70750cae2";
	private static final String appsecret="7e2ef524b202a65dc042a4360469f75e";
	
	
	public static String requestopenid (String logincode) {
		
        
        //code="071kYm2h1YNJFy0L2d2h1SKn2h1kYm24";
        //授权（必填）
        String grant_type = "authorization_code";
        //URL
        String requestUrl = "https://api.weixin.qq.com/sns/jscode2session";
        //请求参数
        String params = "appid=" + appid + "&secret=" + appsecret + "&js_code=" + logincode + "&grant_type=" + grant_type;
        //发送请求
        String data = HttpUtil.get(requestUrl, params);
        System.out.println(data);
        //解析相应内容（转换成json对象）
        JSONObject  json = JSONObject.fromObject(data);
        //用户的唯一标识（openid）
        String Openid =String.valueOf(json.get("openid"));
        //获取对话标识（session_key）
        //String session_key =String.valueOf(json.get("session_key"));

        return Openid;
		
	}
	
	
//	public static void main(String[] args) {
//		String a=requestopenid("021q9qos1iVtyn06KGns1X0vos1q9qoO");
//		System.out.println(a);
//	}
	
	
	

}

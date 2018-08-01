package net.riking.libraryapi.utils;


import java.util.Date;
import java.text.SimpleDateFormat;


public class TimesUtil {
	
	public static String gettime(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
		String time=df.format(new Date());
		return time;
	}
	
}

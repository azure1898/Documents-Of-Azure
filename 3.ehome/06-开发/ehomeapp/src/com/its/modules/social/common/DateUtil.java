package com.its.modules.social.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description：日期转换工具类
 * @Author：刘浩浩 @Date：2017年8月4日
 */
public class DateUtil {

	/**
	 * @Description：获取与当前时间的距离
	 * @Author：刘浩浩
	 * @Date：2017年8月4日
	 * @param date
	 * @return
	 */
	public static String getDaysBeforeNow(Date date) {
	 	Date sysDate = new Date();
		String result = "";
		SimpleDateFormat sdf = null;
			
		long from = date.getTime();  
		long to = sysDate.getTime();  
		int minutes = (int) ((to - from)/(1000 * 60));
		//分钟级别比较
		if(minutes<1){
			return "刚刚";
		}else if(1<=minutes && minutes<60){
			return minutes + "分钟前";
		}else if(60<=minutes && minutes<24*60){
			return minutes/60 + "小时前";
		}else if(24*60<=minutes && minutes<48*60){
			sdf = new SimpleDateFormat("HH:mm"); 
			result = sdf.format(date); 
			return "昨天 " + result;
		}else{
			sdf = new SimpleDateFormat("MM-dd HH:mm"); 
			result = sdf.format(date); 
			return result;
		}
	}
	
	 public static void main(String[] args) throws ParseException {  
		/* SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//如2016-08-10 20:40  
		 long from = simpleFormat.parse("2016-05-01 13:05:00").getTime();  
		 long to = simpleFormat.parse("2016-05-01 14:06:04").getTime();  
		 int minutes = (int) ((to - from)/(1000 * 60));  
		 
		 System.out.println(minutes);
		 System.out.println(121/60);*/
	        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	        Date date5 = df.parse("2017-08-17 17:52:00"); 
	        Date date2 = df.parse("2017-08-17 17:5:00");   
	       Date date0 = df.parse("2017-08-17 09:56:00"); 
	       Date date = df.parse("2017-08-16 14:43:00"); 
	        Date date1 = df.parse("2017-08-14 14:43:00");   
	      
	        System.out.println(getDaysBeforeNow(date5));
	       System.out.println(getDaysBeforeNow(date0));
	       System.out.println(getDaysBeforeNow(date));
	        System.out.println(getDaysBeforeNow(date1));
	    }  
	 
}

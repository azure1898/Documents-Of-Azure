package com.its.modules.social.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
		long sysTime = Long.parseLong(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
		long ymdhms = Long.parseLong(new SimpleDateFormat("yyyyMMddHHmmss").format(date));
		String strYear = "年前";
		String strMonth = "月前";
		String strDay = "天前";
		String strHour = "小时前";
		String strMinute = "分钟前";
		try {
			if (ymdhms == 0) {
				return "";
			}
			long between = (sysTime / 10000000000L) - (ymdhms / 10000000000L);
			if (between > 0) {
				return between + strYear;
			}
			between = (sysTime / 100000000L) - (ymdhms / 100000000L);
			if (between > 0) {
				return between + strMonth;
			}
			between = (sysTime / 1000000L) - (ymdhms / 1000000L);
			if (between > 0) {
				return between + strDay;
			}
			between = (sysTime / 10000) - (ymdhms / 10000);
			if (between > 0) {
				return between + strHour;
			}
			between = (sysTime / 100) - (ymdhms / 100);
			if (between > 0) {
				return between + strMinute;
			}
			return "1" + strMinute;
		} catch (Exception e) {
			return "";
		}
	}
	
	 public static void main(String[] args) throws ParseException {  
	        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");  
	        Date date = df.parse("2017-08-04");  
	        Calendar cal = Calendar.getInstance();  
	        cal.add(Calendar.DATE, -7);  
	        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); //上周一  
	        System.out.println(df.format(cal.getTime()));  
	        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY); //周末  
	        System.out.println(df.format(cal.getTime()));  
	          
	        
	        Calendar cal1 = Calendar.getInstance();  
	        //n为推迟的周数，1本周，-1向前推迟一周，2下周，依次类推  
	        int n = 2;  
	        String monday;  
	        cal1.add(Calendar.DATE, n*7);  
	        //想周几，这里就传几Calendar.MONDAY（TUESDAY...）  
	        cal1.set(Calendar.DAY_OF_WEEK,Calendar.MONDAY);  
	        monday = new SimpleDateFormat("yyyy-MM-dd").format(cal1.getTime());  
	        System.out.println(monday);  
	    }  
}

package com.its.modules.app.common;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.time.DateFormatUtils;

import com.its.common.utils.DateUtils;
import com.its.common.utils.StringUtils;

public class AppUtils {

	public static final boolean DEBUG_MODEL = true;

	/**
	 * 生成订单号（不包含流水号） 生成规则：楼盘（001）+模块（01）+年（17）+月（06）+日（01）+流水号（0001）
	 * 
	 * @param VillageInfoID
	 * @param ModuleID
	 * @return
	 */
	public static String generateOrderNoPrefix(String villageInfoID, String moduleID) {
		StringBuffer no = new StringBuffer();
		if (StringUtils.isNotBlank(villageInfoID)) {
			no.append(villageInfoID);
		}
		if (StringUtils.isNotBlank(moduleID)) {
			no.append(moduleID);
		}
		SimpleDateFormat format = new SimpleDateFormat("yyMMdd");
		no.append(format.format(new Date()));
		return no.toString();
	}

	/**
	 * 获得今日的起始时间
	 * 
	 * @return
	 * @throws ParseException
	 */
	public static String getTodayBegin() {
		return DateFormatUtils.format(new Date(), "yyyy-MM-dd 00:00:00");
	}

	/**
	 * 返回：MM/dd
	 * 
	 * @param date
	 *            yyyy-MM-dd
	 * @return
	 */
	public static String formatDateString(String date) {
		try {
			return new SimpleDateFormat("MM/dd").format(new SimpleDateFormat("yy-MM-dd").parse(date));
		} catch (ParseException e) {
			return "";
		}
	}

	/**
	 * 获取星期（今日、明天、后天、周一...周日）
	 * 
	 * @param date
	 *            格式：yyyy-MM-dd
	 * @return
	 */
	public static String formatDateWeek(String date) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date d = null;
		String result = "";
		try {
			d = df.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Date now = new Date();
		if (df.format(now).equals(date)) {
			result = "今天";
		} else {
			Calendar cal = Calendar.getInstance();
			cal.setTime(now);
			cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) + 1);
			Date tomorrow = cal.getTime();
			if (df.format(tomorrow).equals(date)) {
				result = "明天";
			} else {
				cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) + 1);
				Date afterTomorrow = cal.getTime();
				if (df.format(afterTomorrow).equals(date)) {
					result = "后天";
				} else {
					result = DateFormatUtils.format(d, "E").replaceAll("星期", "周");
				}
			}
		}
		return result;
	}

	/**
	 * 根据传入的时间获得当前时间所在周的第一天和第七天日期
	 * 
	 * @param tm
	 *            时间
	 * @param firstday
	 *            周日作为第一天是0，周一作为第一天是1。
	 * @return
	 */
	public static List<Date> getWeek(Date tm, int firstday) {
		Calendar c = Calendar.getInstance();
		c.setTime(tm);
		if (c.get(Calendar.DAY_OF_WEEK) == 1) {
			c.add(Calendar.DATE, -1);
		}
		List<Date> list = new ArrayList<Date>();
		Calendar cf = Calendar.getInstance();
		cf.setTime(c.getTime());
		cf.set(Calendar.DAY_OF_WEEK, cf.getFirstDayOfWeek());
		cf.add(Calendar.DATE, firstday);
		Calendar ce = Calendar.getInstance();
		ce.setTime(c.getTime());
		ce.set(Calendar.DAY_OF_WEEK, cf.getFirstDayOfWeek() + 6);
		ce.add(Calendar.DATE, firstday);
		list.add(cf.getTime());
		list.add(ce.getTime());
		return list;
	}

	/**
	 * 利用正则表达式判断字符串是否是数字
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str) {
		if (StringUtils.isBlank(str)) {
			return false;
		}
		Pattern pattern = Pattern.compile("[0-9|.]*");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}
	/**
	 * 两个日期相差天数
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int dateSpan(Date date1, Date date2) {
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);
		int day1 = cal1.get(Calendar.DAY_OF_YEAR);
		int day2 = cal2.get(Calendar.DAY_OF_YEAR);

		int year1 = cal1.get(Calendar.YEAR);
		int year2 = cal2.get(Calendar.YEAR);
		if (year1 != year2) // 同一年
		{
			int timeDistance = 0;
			for (int i = year1; i < year2; i++) {
				if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0) // 闰年
				{
					timeDistance += 366;
				} else // 不是闰年
				{
					timeDistance += 365;
				}
			}
			return timeDistance + (day2 - day1);
		} else // 不同年
		{
			return day2 - day1;
		}
	}

	public static void main(String[] args) {
		// System.out.println(AppUtils.formatDateWeek("2017-07-13"));
		// Calendar cal = Calendar.getInstance();
		// cal.setTime(new Date());
		// cal.add(Calendar.DAY_OF_YEAR, -7);
		// List<Date> week = getWeek(cal.getTime(), 1);
		// List<Date> week = getWeek(new Date(), 1);
		// System.out.println(week.get(0));
		// System.out.println(week.get(1));
		String d1 = "2017-7-26";
		String d2 = "2017-7-27";
		System.out.println(dateSpan(DateUtils.parseDate(d1), DateUtils.parseDate(d2)));
	}

}

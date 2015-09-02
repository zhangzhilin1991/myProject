package com.caihua.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @Author jiwenbin
 * @Date 2014-4-15上午09:54:32
 * @Version 1.0
 */

public class TimeUtil {
	
	/**
	 * 时间字符串转换
	 * @param time
	 * @return
	 */
	public static String Str2Date(String time){
		return time.substring(0,4)+"-"+
		time.substring(4,6)+"-"+
		time.substring(6,8)+" "+
		time.substring(8,10)+":"+
		time.substring(10,12)+":"+
		time.substring(12,14);
	}
	
	/**
	 * 比较两个日期
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean cmpDate(String date1,String date2){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
		try {
			Date d1 = sdf.parse(date1);
			Date d2 = sdf.parse(date2);
			return d1.before(d2);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 获取当前时间
	 * @return
	 */
	public static String getCurrentTime(){
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return simpleDateFormat.format(new Date());
	}

	/**
	 * 获取当前时间
	 * @return
	 */
	public static String getCurrentTimeString(){
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyyMMddHHmmss");
		return simpleDateFormat.format(new Date());
	}

	/**
	 * 
	 * @param time
	 * @return
	 */
	public static String convertTime(String time){
		if (time==null || time=="") {
			return "";
		}
		Date date=StringToDate(time,"yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy年MM月dd日HH时mm分");
		return simpleDateFormat.format(date);
	}

	public static String convertTime(String time,boolean bSeconds){
		if (time==null || time=="") {
			return "";
		}
		Date date = new Date(Long.valueOf(time));
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		return format.format(date);
	}

	public static String getNow(){
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy年MM月dd日");
		return simpleDateFormat.format(new Date());
	}

	/**
	 * 获取当前时间豪秒数
	 * @return
	 */
	public static String getCurrentTimeBySeconds(){
		return String.valueOf(new Date().getTime());
	}

	/**
	 * 按照日期格式转换日期
	 * @param dateStr
	 * @param formatStr
	 * @return
	 */
	public static Date StringToDate(String dateStr,String formatStr){
		SimpleDateFormat dd=new SimpleDateFormat(formatStr);
		Date date=null;
		try {
			date = dd.parse(dateStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}
}

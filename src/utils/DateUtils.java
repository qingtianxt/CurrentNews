package utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	/**
	 * 获取
	 * @return
	 */
	public static String getDate(){

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = sdf.format(new Date());
		return date;
	}
	/**
	* 获取日期
	* @return
	*/
	public static String getDateStr() {
	Date date = new Date();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	return sdf.format(date);
	}
	/**
	* 获取时间
	* @return
	*/
	public static String getTimeStr() {
	Date date = new Date();
	SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
	return sdf.format(date);
	}
}

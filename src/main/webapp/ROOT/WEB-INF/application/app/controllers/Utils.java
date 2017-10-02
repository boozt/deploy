package controllers;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

public class Utils {

	public static int AUTOCOMPLETE_MAX = 15;
	public static SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	public static SimpleDateFormat sdfH = new SimpleDateFormat("dd-MM-yyyy");//dd-MM-yyyy HH
	public static SimpleDateFormat sdfHms = new SimpleDateFormat("dd-MM-yyyy");//dd-MM-yyyy HH:mm:ss
	
	public static String format(SimpleDateFormat isdf, Date date) {
//		isdf.setTimeZone(TimeZone.getTimeZone("America/New_York"));
//		TimeZone.setDefault(TimeZone.getTimeZone("America/New_York"));
		String ret= isdf.format(date);
//		TimeZone.setDefault(TimeZone.getTimeZone("Europe/Istanbul"));
//		isdf.setTimeZone(TimeZone.getTimeZone("Europe/Istanbul"));
		return ret;
	}
	
	public static Date parse(SimpleDateFormat isdf, String date) throws ParseException  {
		Date ret= isdf.parse(date);
		return ret;
	}
	
	public static String getFirstWeekdayOfMonthEng(int diff) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, diff);
		cal.set(Calendar.DATE, 1);
		DateFormatSymbols dfs = new DateFormatSymbols();
		String ret = dfs.getWeekdays()[cal.get(Calendar.DAY_OF_WEEK)];
		return ret;
	}

	public static String getMonthYearTr(int diff) {		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, diff);
		DateFormatSymbols dfs = new DateFormatSymbols();
		String ret = dfs.getMonths()[cal.get(Calendar.MONTH)] + " "
				+ cal.get(Calendar.YEAR);
		return ret;
	}

	public static Date getFirstDayOfCurrentMonth(int diff) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, diff);
		cal.set(Calendar.DATE, 1);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		Date ret = cal.getTime();
		return ret;
	}

	public static Date getToday() {
		Calendar cal = Calendar.getInstance();
		Date ret = cal.getTime();
		return ret;
	}

	public static Date getToday(int diff) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR, diff);
		Date ret = cal.getTime();
		return ret;
	}
	
	public static String getTodayStr(int diff) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR, diff);
		Date ret = cal.getTime();
		return new SimpleDateFormat("dd-MM-yyyy").format(ret);
	}

	public static int getCurrentMonthLength(int diff) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, diff);
		int ret = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		return ret;
	}

	public static Date getLastDayOfCurrentMonth(int diff) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, diff);
		cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		Date ret = cal.getTime();
		return ret;
	}

	public static int getDOM(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int ret = cal.get(Calendar.DAY_OF_MONTH);
		return ret;
	}

	public static int getDOMForCurrentMonthStart(Date date, int diff) {
		Calendar calDate = Calendar.getInstance();
		calDate.setTime(date);

		Calendar calFirstday = Calendar.getInstance();
		calFirstday.add(Calendar.MONTH, diff);
		calFirstday.set(Calendar.DATE, 1);
		calFirstday.set(Calendar.MINUTE, 0);
		calFirstday.set(Calendar.SECOND, 0);

		int ret = 1;
		if (calDate.compareTo(calFirstday) > 0) {
			ret = calDate.get(Calendar.DAY_OF_MONTH);
		}
		return ret;
	}

	public static int getDOMForCurrentMonthEnd(Date date, int diff) {
		Calendar calDate = Calendar.getInstance();
		calDate.setTime(date);

		Calendar calLastday = Calendar.getInstance();
		calLastday.add(Calendar.MONTH, diff);
		calLastday.set(Calendar.DATE,
				calLastday.getActualMaximum(Calendar.DAY_OF_MONTH));
		calLastday.set(Calendar.MINUTE, 0);
		calLastday.set(Calendar.SECOND, 0);

		int ret = 0;
		if (calDate.compareTo(calLastday) >= 0)
			ret = calLastday.getActualMaximum(Calendar.DAY_OF_MONTH);
		else
			ret = calDate.get(Calendar.DAY_OF_MONTH);

		return ret;
	}

	public static int daysBetween(String d1, String d2) {
		try {
		    Calendar cal1 = new GregorianCalendar();
			Calendar cal2 = new GregorianCalendar();

			Date date = sdf.parse(d1);
			cal1.setTime(date);
			date = sdf.parse(d2);
			cal2.setTime(date);

			int ret = (int) ((cal2.getTime().getTime() - cal1.getTime()
					.getTime()) / (1000 * 60 * 60 * 24));
			return ret;
		} catch (Throwable e) {
			return 0;
		}

	}
	
	public static int daysBetweenToday(String d1) {
		try {
			Calendar cal1 = new GregorianCalendar();
			Calendar cal2 = new GregorianCalendar();

			cal1.setTime(getToday());
			Date date = sdf.parse(d1);
			cal2.setTime(date);

			int ret = (int) ((cal2.getTime().getTime() - cal1.getTime()
					.getTime()) / (1000 * 60 * 60 * 24)) ;
			if (ret>=0) ret++;
			return ret;
		} catch (Throwable e) {
			return 0;
		}

	}

	public static int daysBetween(Date d1, Date d2) {
		int ret = (int) ((d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
		return ret;
	}

	public static void main(String[] args) {
		Calendar cal = Calendar.getInstance();
		DateFormat f = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	//	setToDefault();
		System.out.println(f.format(cal.getTime()));
	}
}

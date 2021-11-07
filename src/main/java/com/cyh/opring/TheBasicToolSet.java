package com.cyh.opring;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TheBasicToolSet {
	public static String getDate(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String format = dateFormat.format(date);
		return format;
	}
}

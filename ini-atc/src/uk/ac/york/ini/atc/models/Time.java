package uk.ac.york.ini.atc.models;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Time {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
    	Calendar cal = Calendar.getInstance();
    	long oldTime = cal.getTimeInMillis();
    	System.out.print(oldTime);
	}

}

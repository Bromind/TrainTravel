package Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class Journey {
	private final Calendar departureDate;
	private static Scanner scanner = new Scanner(System.in);


	public Journey(Calendar departureDate) {

		this.departureDate = departureDate;
	}

	public Journey() {
		System.out.println("Departure Date : ");
		departureDate = getCalendar();

	}

	public Calendar departureDate() {
		return departureDate;
	}

	public String toString() {
		return "Departure on : " + departureDate.get(Calendar.DAY_OF_MONTH)
				+ "/" + departureDate.get(Calendar.MONTH) + "/"
				+ departureDate.get(Calendar.YEAR) + " à : "
				+ departureDate.get(Calendar.HOUR_OF_DAY) + "h"
				+ departureDate.get(Calendar.MINUTE);
	}

	private static Calendar getCalendar() {
		int month = getMonth();
		int year = getYear();
		int dayOfMonth = getDayOfMonth(month, year);

		Calendar calendar = new GregorianCalendar(year, month, dayOfMonth);

		return calendar;
	}

	private static int getMonth() {
		int month = -1;
		while (month < 0 || month >= 12) {
			System.out.println("Enter the month (1 - 12) : ");
			month = scanner.nextInt() - 1;
			if (month < 0 || month >= 12)
				System.out.println("The value is not correct");
		}

		return month;
	}

	private static int getYear() {
		int year;

		System.out.println("Enter the year of the journey : ");
		year = scanner.nextInt();

		return year;
	}

	private static int getDayOfMonth(int currentMonth, int currentYear) {
		int toReturnDay = -1;
		Calendar cal;
		cal = GregorianCalendar.getInstance();
		cal.set(Calendar.MONTH, currentMonth);
		cal.set(Calendar.YEAR, currentYear);

		boolean correct = false;
		while (!correct) {
			System.out.println("Enter the day of the month : ");
			toReturnDay = scanner.nextInt();
			if (toReturnDay > 0 && toReturnDay <= cal.getActualMaximum(Calendar.DAY_OF_MONTH)) {
				correct = true;
			} else {
				System.out.println("The day is not correct");
			}
		}
		return toReturnDay;
	}
}

package run;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

import userInterface.UIInputWidget;

public class UICalendarWidget extends UIInputWidget<Calendar>{

	private int year, month, dayOfMonth, hourOfDay, minute;


	public UICalendarWidget() {
		super("--- Ajout d'un horaire ---");
	}

	@Override
	public Calendar getInput() {

		month = getMonth();
		year = getYear();
		dayOfMonth = getDayOfMonth(month, year);
		hourOfDay = getHour();
		minute = getMinute();

		Calendar calendar = new GregorianCalendar(year, month, dayOfMonth, hourOfDay, minute);

		return calendar;
	}

	private int getMinute(){
		Scanner scanner = new Scanner(System.in);
		int minute = -1;
		boolean correct = false;
		while(!correct){
			System.out.println("Entrez une minute : ");
			minute = scanner.nextInt();
			if(minute >= 0 && minute < 60){
				correct = true;
			} else {
				System.out.println("Entrez une minute comprise entre 0 et 59");
			}
		}
		scanner.close();
		return minute;
	}

	private int getHour() {
		Scanner scanner = new Scanner(System.in);
		int hour = -1;
		
		boolean correct = false;
		while(!correct){
			System.out.println("Entrez une heure : ");
			hour = scanner.nextInt();
			if(hour >=0 && hour < 24){
				correct = true;
			} else {
				System.out.println("Entrez une heure comprise entre 0 et 23");
			}
		}

		scanner.close();
		return hour;
	}

	private int getDayOfMonth(int currentMonth, int currentYear) {
		int toReturnDay = -1;
		Scanner scanner = new Scanner(System.in);
		int maximumDay;
		Calendar cal;
		cal = GregorianCalendar.getInstance();
		cal.set(Calendar.MONTH, currentMonth);
		cal.set(Calendar.YEAR, currentYear);
		maximumDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

		boolean correct = false;
		while(!correct){
			System.out.println("Entrez le jour du mois : ");
			toReturnDay = scanner.nextInt();
			if(toReturnDay > 0 && toReturnDay <= maximumDay){
				correct = true;
			}else{
				System.out.println("Le jour n'est pas correct");
			}
		}
		
		scanner.close();
		return  toReturnDay;
	}

	private int getYear() {
		int year;
		Scanner scanner = new Scanner(System.in);

		System.out.println("Entrez l'année du trajet : ");
		year = scanner.nextInt();

		scanner.close();
		return  year;
	}

	private int getMonth() {
		Scanner scanner = new Scanner(System.in);
		int month = -1;
		while(month < 0 | month >= 12){
			System.out.println("Choisissez un mois de l'année : ");
			for(int i = 0 ; i < Month.values().length ; i++){
				System.out.println((i+1) + ". " + Month.values()[i].frenchName);
			}
			month = scanner.nextInt();
			if(month < 0 | month >= 12)
				System.out.println("Le mois doit être compris entre 1 et 12 (valeurs comprises)");
		}

		scanner.close();
		return month;
	}
}

package travel;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import trainTravel.TrainTravel;

public class Journey {
	private final static Scanner scanner = new Scanner(System.in);

	private final Train train;
	private final Station stationFrom, stationTo;
	private final Calendar departureDate, arrivalDate;

	/**
	 * The difference between departureDate and arrivalDate in minutes.
	 */
	// private final int timeOfTravel;

	public Journey(Station stationFrom, Station stationTo, Calendar departureDate, Calendar arrivalDate, Train kindOfTrain)
			throws IllegalArgumentException {
		if (departureDate.compareTo(arrivalDate) > 0)
			throw new IllegalArgumentException("The departure date must be before the arrival date");
		this.stationFrom = stationFrom;
		this.stationTo = stationTo;
		this.departureDate = departureDate;
		this.arrivalDate = arrivalDate;
		this.train = kindOfTrain;
		// timeOfTravel = departureDate.compareTo(arrivalDate)/60000;
	}

	public Journey() {
		int dT = 1;
		Calendar aDate = new GregorianCalendar(2000, 0, 0, 0, 0), dDate = aDate;
		System.out.println("--- Nouveau trajet ---");
		System.out.println("Entrez la gare de départ : ");
		stationFrom = new Station(scanner.nextLine());
		System.out.println("Entrez la gare d'arrivée : ");
		stationTo = new Station(scanner.nextLine());
		while (dT > 0) {
			System.out.println("Date de départ : ");
			dDate = getCalendar();
			System.out.println("Date d'arrivée : ");
			aDate = getCalendar();
			dT = dDate.compareTo(aDate);
		}
		departureDate = dDate;
		arrivalDate = aDate;
		System.out.println("Entrez le train : ");
		train = getTrain();

	}

	public Journey(String s) throws IllegalArgumentException{
		String trainName = s.substring(0, s.indexOf(" & "));
		s = s.substring(s.indexOf(" & ") + 3);
		int trainNumber = Integer.parseInt(s.substring(0, s.indexOf(" & ")));
		s = s.substring(s.indexOf(" & ") + 3);
		int departureDayOfMonth = Integer.parseInt(s.substring(0, s.indexOf("/")));
		s = s.substring(s.indexOf('/')+1);
		int departureMonth = Integer.parseInt(s.substring(0, s.indexOf("/")));
		s = s.substring(s.indexOf('/')+1);
		int departureYear = Integer.parseInt(s.substring(0, s.indexOf(" & ")));
		s = s.substring(s.indexOf(" & ") + 3);
		String departureStationName = s.substring(0, s.indexOf(" & "));
		s = s.substring(s.indexOf(" & ") + 3);
		int departureHour = Integer.parseInt(s.substring(0, s.indexOf('h')));
		s = s.substring(s.indexOf('h') + 1);
		int departureMinute = Integer.parseInt(s.substring(0, s.indexOf(" & ")));
		s = s.substring(s.indexOf(" & ") + 3);
		int arrivalDayOfMonth = Integer.parseInt(s.substring(0, s.indexOf("/")));
		s = s.substring(s.indexOf('/') + 1);
		int arrivalMonth = Integer.parseInt(s.substring(0, s.indexOf("/")));
		s = s.substring(s.indexOf('/') + 1);
		int arrivalYear = Integer.parseInt(s.substring(0, s.indexOf(" & ")));
		s = s.substring(s.indexOf(" & ") + 3);
		String arrivalStationName = s.substring(0, s.indexOf(" & "));
		s = s.substring(s.indexOf(" & ") + 3);
		int arrivalHour = Integer.parseInt(s.substring(0, s.indexOf('h')));
		s = s.substring(s.indexOf('h') + 1);
		int arrivalMinute = Integer.parseInt(s.substring(0, s.indexOf(" \\")));

		TrainKind kind = null;
		for(int i = 0 ; i < TrainKind.values().length ; i++){
			if(TrainKind.values()[i].getName().compareTo(trainName) == 0){
				kind = TrainKind.values()[i];
				break;
			}
		}
		if (kind == null)
			kind = TrainKind.GENERIC;
		train = new Train(trainNumber, kind);
		stationFrom = new Station(departureStationName);
		stationTo = new Station(arrivalStationName);
		departureDate = new GregorianCalendar(departureYear, departureMonth, departureDayOfMonth, departureHour, departureMinute);
		arrivalDate = new GregorianCalendar(arrivalYear, arrivalMonth, arrivalDayOfMonth, arrivalHour, arrivalMinute);
	}

	/*
	 * public int daysOfTravel(){ return hourOfTravel()/24; }
	 * 
	 * public int hourOfTravel(){ return timeOfTravel/60; }
	 * 
	 * public int timeOfTravel(){ return timeOfTravel; }
	 */

	public Journey editJourney(){
		List<String> stringList = new LinkedList<String>();
		stringList.add("La gare de départ");
		stringList.add("La gare d'arrivée");
		stringList.add("La date de départ");
		stringList.add("La date d'arrivée");
		stringList.add("Le train");
		int indexToEdit = stringList.indexOf(TrainTravel.chooseFromList("Quel champs éditer ?", stringList));
		switch (indexToEdit) {
		case 0:
			System.out.println("Entrez le nom de la gare de départ : ");
			return new Journey(new Station(scanner.nextLine()), stationTo, departureDate, arrivalDate, train);
		case 1:
			System.out.println("Entrez le nom de la gare d'arrivée : ");
			return new Journey(stationFrom, new Station(scanner.nextLine()), departureDate, arrivalDate, train);
		case 2:
			Calendar dDate;
			do{
				dDate = getCalendar();
				if(dDate.compareTo(arrivalDate) > 0)
					System.out.println("La date de départ doit être avant la date d'arrivée");
			}while (dDate.compareTo(arrivalDate) > 0);
			return new Journey(stationFrom, stationTo, dDate, arrivalDate, train);
		case 3:
			Calendar aDate;
			do{
				aDate = getCalendar();
				if(aDate.compareTo(departureDate) < 0)
					System.out.println("La date d'arrivée doit être après la date de départ");
			}while(aDate.compareTo(departureDate) < 0);
			return new Journey(stationFrom, stationTo, departureDate, aDate, train);
		case 4:
			return new Journey(stationFrom, stationTo, departureDate, arrivalDate, getTrain());

		default:
			System.out.println("Entrez un index valide");
			break;
		}

		return null;
	}

	public Station from() {
		return stationFrom;
	}
	

	public Station to() {
		return stationTo;
	}

	public Train train() {
		return train;
	}

	public int trainNumber() {
		return train.getTrainNumber();
	}

	public String trainName() {
		return train.getTrainName();
	}

	public Calendar departureDate() {
		return departureDate;
	}

	public Calendar arrivalDate() {
		return arrivalDate;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("De : ");
		sb.append(from());
		sb.append(" À : ");
		sb.append(to());
		sb.append("\nTrain : ");
		sb.append(train);
		sb.append("\nDépart le : ");
		if(departureDate.get(Calendar.DAY_OF_MONTH) < 10)
			sb.append(0);
		sb.append(departureDate.get(Calendar.DAY_OF_MONTH) + "/");
		if((departureDate.get(Calendar.MONTH) + 1) < 10)
			sb.append(0);
		sb.append((departureDate.get(Calendar.MONTH) + 1) + "/"
				+ departureDate.get(Calendar.YEAR) + " à : ");
		if(departureDate.get(Calendar.HOUR_OF_DAY) < 10)
			sb.append(0);
		sb.append(departureDate.get(Calendar.HOUR_OF_DAY) + "h");
		if(departureDate.get(Calendar.MINUTE) < 10)
			sb.append(0);
		sb.append(departureDate.get(Calendar.MINUTE) + "\nArrivée le : ");
		if(arrivalDate.get(Calendar.DAY_OF_MONTH) < 10)
			sb.append(0);
		sb.append(arrivalDate.get(Calendar.DAY_OF_MONTH) + "/");
		if((arrivalDate.get(Calendar.MONTH)+1) < 10)
			sb.append(0);
		sb.append((arrivalDate.get(Calendar.MONTH) + 1) + "/" + arrivalDate.get(Calendar.YEAR) + " à : ");
		if(arrivalDate.get(Calendar.HOUR_OF_DAY) < 10)
			sb.append(0);
		sb.append(arrivalDate.get(Calendar.HOUR_OF_DAY) + "h");
		if(arrivalDate.get(Calendar.MINUTE) < 10)
			sb.append(0);
		sb.append(arrivalDate.get(Calendar.MINUTE) + "\n");
		return sb.toString();
	}

	public String laTeXArrayLine() {

		StringBuilder sb = new StringBuilder();
		sb.append(train.getTrainName() + " & " + train.getTrainNumber() + " & ");
		if(departureDate.get(Calendar.DAY_OF_MONTH) < 10)
			sb.append(0);
		sb.append(departureDate.get(Calendar.DAY_OF_MONTH) + "/");
		if((departureDate.get(Calendar.MONTH) + 1) < 10)
			sb.append(0);
		sb.append((departureDate.get(Calendar.MONTH) + 1) + "/"
				+ departureDate.get(Calendar.YEAR) + " & ");
		sb.append(from() + " & ");
		if(departureDate.get(Calendar.HOUR_OF_DAY) < 10)
			sb.append(0);
		sb.append(departureDate.get(Calendar.HOUR_OF_DAY) + "h");
		if(departureDate.get(Calendar.MINUTE) < 10)
			sb.append(0);
		sb.append(departureDate.get(Calendar.MINUTE) + " & ");
		if(arrivalDate.get(Calendar.DAY_OF_MONTH) < 10)
			sb.append(0);
		sb.append(arrivalDate.get(Calendar.DAY_OF_MONTH) + "/");
		if((arrivalDate.get(Calendar.MONTH)+1) < 10)
			sb.append(0);
		sb.append((arrivalDate.get(Calendar.MONTH) + 1) + "/" + arrivalDate.get(Calendar.YEAR) + " & " + to() + " & ");
		if(arrivalDate.get(Calendar.HOUR_OF_DAY) < 10)
			sb.append(0);
		sb.append(arrivalDate.get(Calendar.HOUR_OF_DAY) + "h");
		if(arrivalDate.get(Calendar.MINUTE) < 10)
			sb.append(0);
		sb.append(arrivalDate.get(Calendar.MINUTE) + " \\\\\n\\hline\n");

		return sb.toString();
	}

	private static Train getTrain() {
		int nTrain;
		System.out.println("Entrez le numéro de train : ");
		nTrain = scanner.nextInt();
		List<TrainKind> trainList = new LinkedList<TrainKind>();
		for (int i = 0; i < TrainKind.values().length; i++) {
			trainList.add(TrainKind.values()[i]);
		}
		TrainKind kind = TrainTravel.chooseFromList("Choisissez un type de train", trainList);

		Train train = new Train(nTrain, kind);
		return train;
	}

	private static int getYear() {
		int year;

		System.out.println("Entrez l'année du trajet : ");
		year = scanner.nextInt();

		return year;
	}

	private static Calendar getCalendar() {
		int month = getMonth();
		int year = getYear();
		int dayOfMonth = getDayOfMonth(month, year);
		int hourOfDay = getHour();
		int minute = getMinute();

		Calendar calendar = new GregorianCalendar(year, month, dayOfMonth,
				hourOfDay, minute);

		return calendar;
	}

	private static int getMinute() {
		int minute = -1;
		boolean correct = false;
		while (!correct) {
			System.out.println("Entrez une minute : ");
			try {
				minute = scanner.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("Entrez un nombre.");
				scanner.nextLine();
			}
			if (minute >= 0 && minute < 60) {
				correct = true;
			} else {
				System.out.println("Entrez une minute comprise entre 0 et 59");
			}
		}
		return minute;
	}

	private static int getHour() {
		int hour = -1;

		boolean correct = false;
		while(!correct){
			System.out.println("Entrez une heure : ");
			try{
				hour = scanner.nextInt();
			} catch (InputMismatchException e){
				System.out.println("Entrez un chiffre");
				scanner.nextLine();
			}
			if(hour >=0 && hour < 24){
				correct = true;
			} else {
				System.out.println("Entrez une heure comprise entre 0 et 23");
			}
		}

		return hour;
	}

	private static int getDayOfMonth(int currentMonth, int currentYear) {
		int toReturnDay = -1;
		int maximumDay;
		Calendar cal;
		cal = GregorianCalendar.getInstance();
		cal.set(Calendar.MONTH, currentMonth);
		cal.set(Calendar.YEAR, currentYear);
		maximumDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

		boolean correct = false;
		while (!correct) {
			System.out.println("Entrez le jour du mois : ");
			try {
				toReturnDay = scanner.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("Entrez un nombre");
				scanner.nextLine();
			}
			if (toReturnDay > 0 && toReturnDay <= maximumDay) {
				correct = true;
			} else {
				System.out.println("Le jour n'est pas correct");
			}
		}

		return toReturnDay;
	}

	private static int getMonth() {
		int month = -1;
		while (month < 0 | month >= 12) {
			System.out.println("Choisissez un mois de l'année : ");
			for (int i = 0; i < Month.values().length; i++) {
				System.out.println((i + 1) + ". "
						+ Month.values()[i].frenchName);
			}
			try {
				month = scanner.nextInt() - 1;
			} catch (InputMismatchException e) {
				System.out.println("Entrez un nombre");
				scanner.nextLine();
			}
			if (month < 0 | month >= 12)
				System.out
				.println("Le mois doit être compris entre 1 et 12 (valeurs comprises)");
		}

		return month;
	}

	public enum Month {
		JANUARY("Janvier", 0),
		FEBRUARY("Février", 1),
		MARCH("Mars", 2),
		APRIL("Avril", 3),
		MAY("Mai", 4),
		JUNE("Juin", 5),
		JULY("Juillet", 6),
		AUGUST("Août", 7),
		SEPTEMBER("Septembre", 8),
		OCTOBER("Octobre", 9),
		NOVEMBER("Novembre", 10),
		DECEMBER("Decembre", 11);
		String frenchName;
		int value;

		private Month(String frenchName, int value) {
			this.frenchName = frenchName;
			this.value = value;
		}

		public int getValue() {
			return value;
		}

	}

}

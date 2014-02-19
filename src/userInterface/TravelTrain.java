package userInterface;


import java.util.GregorianCalendar;

import travel.*;



public class TravelTrain {
	
	
	public static void main(String[] args) {
		
		Travel travel = new Travel();
		
		Step s1 = new Step();
		Step s2 = new Step();
		
		s1.addJourney(new Journey(new Station("Geneva"), new Station("Lausanne"), new GregorianCalendar(2013, 3, 4, 18, 0), new GregorianCalendar(2013, 3, 4, 18, 40), new Train(8000, TrainKind.LYRIA)));
		s1.addJourney(new Journey(new Station("Lausanne"), new Station("Montreux"), new GregorianCalendar(2013, 3, 4, 19, 00), new GregorianCalendar(2013, 3, 4, 19, 10), new Train(8010, TrainKind.TER)));
		
		s2.addJourney(new Journey(new Station("Montreux"), new Station("Sion"), new GregorianCalendar(2013, 3, 5, 8, 45), new GregorianCalendar(2013, 3, 5, 10, 12), new Train(8021, TrainKind.IR)));
		
		travel.addStep(s1);
		travel.addStep(s2);
		
		travel.writeLaTeXFile();
	}
}

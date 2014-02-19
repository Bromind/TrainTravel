package run;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import travel.Journey;
import travel.Station;
import travel.Train;
import travel.TrainKind;
import userInterface.UIInputWidget;
import userInterface.UISelectFromList;

public class NewJourneyWidget extends UIInputWidget<Journey>{

	public NewJourneyWidget() {
		super("Ajout d'un trajet");
	}

	@Override
	public Journey getInput() {
		Station stationFrom, stationTo;
		Calendar departureDate, arrivalDate;
		Train kindOfTrain;
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Entrez la gare de départ : ");
		stationFrom = new Station(scanner.next());
		System.out.println("Entrez la gare d'arrivée : ");
		stationTo = new Station(scanner.next());
		System.out.println("Entrez la date de départ : ");
		departureDate = new UICalendarWidget().getInput();
		System.out.println("Entrez la date d'arrivée : ");
		arrivalDate = new UICalendarWidget().getInput();
		System.out.println("Entrez le train : ");
		kindOfTrain = new UIKindOfTrainInputWidget().getInput();
		
		Journey journey = new Journey(stationFrom, stationTo, departureDate, arrivalDate, kindOfTrain);
		
		scanner.close();
		return journey;
	}
	
	private class UIKindOfTrainInputWidget extends UIInputWidget<Train>{

		public UIKindOfTrainInputWidget() {
			super("--- Choisissez un train ---");
		}

		@Override
		public Train getInput() {
			Scanner scanner = new Scanner(System.in);
			int nTrain;
			System.out.println("Entrez le numéro de train : ");
			nTrain = scanner.nextInt();
			List<TrainKind> trainList = new LinkedList<TrainKind>();
			for(int i = 0 ; i < TrainKind.values().length ; i++){
				trainList.add(TrainKind.values()[i]);
			}
			TrainKind kind = new UISelectFromList<TrainKind>("--- Choisir le train ---", "Choisissez le train dans la liste", trainList).getInput();
			
			Train train = new Train(nTrain, kind);
			scanner.close();
			return train;
		}
		
	}
	
}

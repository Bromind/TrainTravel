package run;

import java.util.Scanner;

import travel.Step;
import userInterface.UIInputWidget;

public class NewStepWidget extends UIInputWidget<Step>{

	public NewStepWidget(){
		super("Ajout d'une étape");
	}
	
	public NewStepWidget(String title) {
		super(title);
	}

	@Override
	public Step getInput() {
		int nJourney;
		Step step = new Step();
		Scanner scanner = new Scanner(System.in);
		System.out.println("--- Nouvelle étape ---");
		System.out.println("Entrez le nombre de trajets que vous voulez ajouter : ");
		nJourney = scanner.nextInt();
		for(int i = 0 ; i < nJourney ; i++){
			step.addJourney(new NewJourneyWidget().getInput());
		}
		scanner.close();
		return step;
	}
}

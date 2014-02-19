package run;

import java.util.Scanner;

import userInterface.UIInputWidget;
import travel.*;

public class NewTravelWidget extends UIInputWidget<Travel>{

	public NewTravelWidget(){
		super("--- Ajouter un Voyage ---");
	}

	@Override
	public Travel getInput() {
		int nStep;
		Travel travel = new Travel();
		Scanner scanner = new Scanner(System.in);
		System.out.println("=== Ajout d'un voyage ===");
		System.out.println("Entrez le nombre d'étapes que vous voulez ajouter : ");
		nStep = scanner.nextInt();
		for(int i = 0 ; i < nStep ; i++){
			travel.addStep(new NewStepWidget().getInput());
		}
		scanner.close();
		return travel;
	}

}

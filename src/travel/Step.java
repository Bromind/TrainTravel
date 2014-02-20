package travel;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import trainTravel.TrainTravel;


public class Step {
	
	private List<Journey> step;
	Scanner scanner = new Scanner(System.in);
	
	private String name = null;
	private Station from, to;

	
	public Step(){


		int nJourney;
		
		step = new LinkedList<Journey>();
		
		System.out.println("=== Nouvelle étape ===");
		System.out.println("Entrez le nombre de trajets que vous voulez ajouter : ");
		nJourney = scanner.nextInt();
		for(int i = 0 ; i < nJourney ; i++){
			step.add(new Journey());
		}
		manageFromTo();
	}
	
	public Step(Journey journey){
		step = new LinkedList<Journey>();
		step.add(journey);
		manageFromTo();
	}
	
	public Step(List<Journey> journeyList){
		step = new LinkedList<Journey>(journeyList);
		manageFromTo();
	}
	
	public Step(String s){
		step = new LinkedList<Journey>();
		// There are 3 "\\hline\n" before the first Journey-line. I remove them.
		for(int i = 0 ; i < 3 ; i++){
			s = s.substring(s.indexOf("\\hline")+7);
		}
		while(s.indexOf("\\hline") != -1){
			step.add(new Journey(s.substring(0, s.indexOf("\\hline"))));
			s = s.substring(s.indexOf("\\hline")+7);
		}
		manageFromTo();
		
	}
	
	public void addJourney(Journey journey){
		step.add(journey);
	}
	
	public Step editStep(){
		int indexToEdit = step.indexOf(TrainTravel.chooseFromList("Quel trajet éditer ?", step));
		step.set(indexToEdit, step.get(indexToEdit).editJourney());
		manageFromTo();
		return new Step(step);
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append(" === Étape : === \n");
		for(Journey j : step){
			sb.append(j);
			sb.append("\n");
		}
		return sb.toString();
	}
	
	private String getStepName(){
		StringBuilder sb = new StringBuilder("\n\\paragraph{");
		for(int i = 0 ; i < step.size() ; i++){
			if((i == 0) || (step.get(i).from().compareTo(step.get(i - 1).to()) != 0))
				sb.append(step.get(i).from() + " - ");
			sb.append(step.get(i).to());
			if(i < step.size()-1)
				sb.append(" - ");
		}
		sb.append("\\newline}\n");
		return sb.toString();
	}
	
	public String laTeXArray(){
		
		StringBuilder sb = new StringBuilder(getStepName() + "\\begin{tabular}{||c|c||c|c|c||c|c|c||}\n\\hline \nTrain & numéro de Train & Départ le & De & à & Arrivée le & à & à \\\\\n\\hline\n\\hline\n");
		for(int i = 0 ; i < step.size() ; i++){
			sb.append(step.get(i).laTeXArrayLine());
		}
		sb.append("\\end{tabular}");
		return sb.toString();
	}


	private void manageFromTo(){
	  from = step.get(0).from();
	  to = step.get(step.size() - 1).to();
	}

	/**
	 * @return Origin of the step.
	 */
	public Station from(){
	  return from;
	}


	/**
	 * @return Destination of the step.
	 */
	public Station to(){
	  return to;
	}

	/**
	 * @return Short name of the step ("Origin - Destination").
	 */
	public String shortName(){
	  return from + " - " + to;
	}
	
}

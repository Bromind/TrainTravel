package travel;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import trainTravel.TrainTravel;



public class Travel {
	
	private final String OUTPUTFILENAME = "voyage.tex";
	private final List<Step> travel;
	Scanner scanner = new Scanner(System.in);

	private Station from, to;


	/**
	 * @deprecated Use "Travel(false);" instead
	 */
	public Travel(){
	  this(false);
	}

	/**
	 * @param empty true to construct an empty Travel
	 */
	public Travel(boolean empty){
	  travel = new LinkedList<Step>();
	  if(!empty){
	    System.out.println("\n\n========================\n==== Nouveau Voyage ====\n========================\n\n");
	    System.out.println("Entrez le nombre d'étape du voyage : ");
	    int nStep = scanner.nextInt();
	    for(int i = 0 ; i < nStep ; i++){
	      travel.add(new Step());
	    }
	  }
	}
	
	public Travel(Step s){
		travel = new LinkedList<Step>();
		addStep(s);
	}
	
	public Travel(List<Step> stepList){
		travel = new LinkedList<Step>(stepList);
	}
	
	public Travel(String file){
		travel = new LinkedList<Step>();
		
		List<String> stepString = new LinkedList<String>();

		while(file.indexOf("\\end{tabular}") != -1){
			int beginIndex = file.indexOf("\\begin{tabular}");
			int endIndex = file.indexOf("\\end{tabular}");
			stepString.add(file.substring(beginIndex, endIndex));
			file = file.substring(endIndex + 13);
		}
		for(int i = 0 ; i < stepString.size() ; i++){
			travel.add(new Step(stepString.get(i)));
		}
	}

	public Step getStep(int i){
	  return travel.get(i);
	}
	
	public Travel editTravel(){
		System.out.println("Que voulez vous faire ?\n\n1. Éditer une étape\n2. Ajouter une étape\n3. Supprimer une étape\n");
		int choice = scanner.nextInt();
		switch (choice) {
		case 1:
			return editTravelStep();
		case 2:
			return addStep(new Step());
		case 3:
			int index = travel.indexOf(TrainTravel.chooseFromList("Quel étape supprimer :", travel));
			return removeStep(index);
		default:
			System.out.println("Entrée erronée");
			return this;
		}
	}
	
	public Travel editTravelStep(){
		int indexToEdit = travel.indexOf(TrainTravel.chooseFromList("Quelle étape éditer ?", travel));
		travel.set(indexToEdit, travel.get(indexToEdit).editStep());
		return new Travel(travel);
	}
	
	public Travel addStep(Step s) {
		travel.add(s);
		return new Travel(travel);
	}
	
	public Travel removeStep(int index){
		travel.remove(index);
		return new Travel(travel);
	}
	
	public void writeLaTeXFile(){
		writeLaTeXFile(OUTPUTFILENAME);
	}
	
	public void writeLaTeXFile(String fileDir){
		writeLaTeXFile(fileDir, "", "", "");
	}
	
	public void writeLaTeXFile(String fileDir, String title){
		writeLaTeXFile(fileDir, title, "", "");
	}
	
	public void writeLaTeXFile(String fileDir, String title, String authorFamilyName){
		writeLaTeXFile(fileDir, title, authorFamilyName, "");
	}
	
	public void writeLaTeXFile(String fileDir, String title, String authorFamilyName, String authorFirstName) {
		PrintStream out = null;
		try {
			out = new PrintStream(fileDir);
			
			out.println("\\documentclass[a4paper,10pt]{article}\n\\usepackage[utf8]{inputenc}\n\\usepackage[francais]{babel}\n\\usepackage[landscape]{geometry}\n\n\\author{" + authorFirstName + " \\textsc{" + authorFamilyName + "}}\n\\title{" + title + "}\n\n\\begin{document}\n\\maketitle\n");
			for(Step s : travel){
				out.println(s.laTeXArray());
			}
			out.println("\\end{document}");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
	}

	public int stepNumber(){
	  return travel.size();
	}
	
	/**
	 * @return A short name of the travel ("Origin - Destination")
	 */
	public String shortName(){
	  if(travel.isEmpty())
	    return "Empty Travel";
	  return from + " - " + to;
	}

	private void manageFromTo(){
	  from = travel.get(0).from();
	  to = travel.get(travel.size() - 1).to();
	}

	public String toString(){
		String sb = new String();
		sb += "==== Voyage ====\n\n";
		for(Step s : travel){
			sb += s.toString() + "\n";
		}
		return sb.toString();
	}
	
}

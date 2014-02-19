package run;

import java.util.Scanner;

import travel.Travel;
import userInterface.UIPromptWidget;

public class ExportTravelWidget extends UIPromptWidget{
	private final Travel travel;

	public ExportTravelWidget(String title, Travel travel) {
		super(title);
		this.travel = travel;
	}

	@Override
	public void prompt() {
		Scanner scanner = new Scanner(System.in);
		String fileDir;
		System.out.println("Entrez l'adresse et le nom du fichier : ");
		fileDir = scanner.next();
		if(fileDir.equals("")){
			travel.writeLaTeXFile();
		}else{
			travel.writeLaTeXFile(fileDir);
		}
		scanner.close();
	}

}

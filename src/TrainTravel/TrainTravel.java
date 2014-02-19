package TrainTravel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import travel.Travel;

public class TrainTravel {
	private static final int 
			ERROR_CODE = -1,
			ADD_TRAVEL = 0,
			EDIT_TRAVEL = 1, 
			EXIT = 2,
			HELP = 3,
			BAD_CHOICE = 4,
			MAIN_MENU = 5,
			WRITE_LATEX = 6,
			IMPORT_FILE = 7,
			ABOUT = 8;
	
	private static final Scanner scanner = new Scanner(System.in);
	private final static List<Travel> travelList = new LinkedList<Travel>();
	private static String fileName, title, authorFamilyName, authorFirstName;

	public static void main(String[] args) {
		if(args.length >= 1){
			fileName = args[0];
		}else{
			fileName = "Voyage";
		}
		if(args.length >= 2){
			title = args[1];
		} else {
			title = "";
		}
		if(args.length >= 3){
			authorFamilyName = args[2];
		} else {
			authorFamilyName = "";
		}
		if(args.length >= 4){
			authorFirstName = args[3];
		} else {
			authorFirstName = "";
		}
		
		System.out.println("               @@@@@@@@\n            @@@@@@@@\n          @@@\n         @@\n        @         _______       _______________________________\n        _         /  ---- \\     /                               \\\n       / \\        [ |   | |     |   ----       ----       ----  |\n       | |  __    [ |   | |     |  |   |      |   |      |   |  |\n     __| |__||___/   ---- |     |  |   |      |   |      |   |  |\n    /                      \\    |   ----       ----       ----  |\n   (~~~~~~~~~~~~~~    __   ==^==|  __                       __  |\n   /\\________________/  \\__/ ^ _|_/  \\_____________________/  \\_|_\n _//__()===()===()===\\__/         \\__/                     \\__/  \n===================================================================");
		//travelList.add(new Travel(new Step(new Journey(new Station("Geneve"), new Station("Lausanne"), new GregorianCalendar(2013, 1, 1, 7, 0), new GregorianCalendar(2013, 1, 1, 7, 30), new Train(802, TrainKind.IC)))));
		int nextAction = 5;
		while(nextAction != EXIT){
			switch (nextAction) {
			case ERROR_CODE : 
				System.out.println("Program exits with error code " + ERROR_CODE);
				nextAction = EXIT;
				break;
			case MAIN_MENU : 
				int returned = mainMenu();
				if(returned == BAD_CHOICE){
					System.out.println("You've made a bad choice");
				} else {
					nextAction = returned;
				}
				break;
			case ADD_TRAVEL : 
				nextAction = addTravel();
				break;
			case EDIT_TRAVEL : 
				nextAction = editTravel();
				break;
				
			case WRITE_LATEX : 
				nextAction = printLaTeXFile();
				break;
			case HELP :
				nextAction = help();
				break;
			case IMPORT_FILE :
				Travel toAdd = importFile();
				if(toAdd != null)
					travelList.add(toAdd);
				nextAction = MAIN_MENU;
				break;
			case ABOUT :
				nextAction = about();
				break;

			default:
				nextAction = EXIT;
				break;
			}
		}
	}
	
	private static int about(){
		System.out.println("TrainTravel\n   v 0.1   \n\nMonday, August, 5th\n(c) Martin Vassor\nGNU/GPL v3.0");
		return MAIN_MENU;
	}
	
	private static int help(){
		System.out.println("    Help    The Beatles\n    ----    -----------\n\nHelp, I need somebody,\nHelp, not just anybody,\nHelp, you know I need someone, help.\n\nWhen I was younger, so much younger than today,\nI never needed anybody's help in any way.\nBut now these days are gone, I'm not so self assured,\nNow I find I've changed my mind and opened up the doors.\n\nHelp me if you can, I'm feeling down\nAnd I do appreciate you being round.\nHelp me, get my feet back on the ground,\nWon't you please, please help me.\n\nAnd now my life has changed in oh so many ways,\nMy independence seems to vanish in the haze.\nBut every now and then I feel so insecure,\nI know that I just need you like I've never done before.\n\nHelp me if you can, I'm feeling down\nAnd I do appreciate you being round.\nHelp me, get my feet back on the ground,\nWon't you please, please help me.\n\nWhen I was younger, so much younger than today,\nI never needed anybody's help in any way.\nBut now these daya are gone, I'm not so self assured,\nNow I find I've changed my mind and opened up the doors.\n\nHelp me if you can, I'm feeling down\nAnd I do appreciate you being round.\nHelp me, get my feet back on the ground,\nWon't you please, please help me, help me, help me, oh.\n\n");
		return MAIN_MENU;
	}
	
	private static int addTravel(){
		travelList.add(new Travel());
		return MAIN_MENU;
	}
	
	private static int editTravel(){
		System.out.println("Que voulez-vous faire ?\n1. Éditer un voyage\n2. Supprimer un voyage");
		int choice = scanner.nextInt();
		switch (choice) {
		case 1:
			int indexToEdit = travelList.indexOf(chooseFromList("Choose which travel to edit : ", travelList));
			travelList.set(indexToEdit, travelList.get(indexToEdit).editTravel());
			break;
		case 2:
			travelList.remove(chooseFromList("Choose which travel to remove : ", travelList));
			break;

		default:
			break;
		}

		return MAIN_MENU;
	}
	
	private  static int printLaTeXFile(){
		if(travelList.size() > 1){
			for(int i = 0 ; i < travelList.size() ; i++){
				String fileN = fileName + (i+1) + ".tex"; 
				travelList.get(i).writeLaTeXFile(fileN, title, authorFamilyName, authorFirstName);
			}
		} else {
			travelList.get(0).writeLaTeXFile(fileName + ".tex", title, authorFamilyName, authorFirstName);
		}
		System.out.println("\nFichier créé\n\n");
		return MAIN_MENU;
	}
	
	private static int mainMenu(){
		System.out.println("=================\n|| TrainTravel ||\n=================\n\n");
		for(Travel t : travelList){
			System.out.println(t);
		}
		System.out.println("a. Add a travel");
		if(travelList.size() > 0)
			System.out.println("e. Edit a travel");
		System.out.println("h. Help");
		System.out.println("i. Import File");
		if(travelList.size() > 0)
			System.out.println("w. Write LaTeX file");
		System.out.println("x. Exit Program");
		System.out.println("z. About");
		System.out.println("\nType a letter corresponding to your choice : ");
		char choice = scanner.next().charAt(0);
		switch (choice) {
		case 'a' :
		case 'A' :
			return ADD_TRAVEL;
		case 'e' :
		case 'E' :
			if(travelList.size() > 0)
				return EDIT_TRAVEL;
			return BAD_CHOICE;
		case 'h' :
		case 'H' :
			return HELP;
		case 'i' :
		case 'I' :
			return IMPORT_FILE;
		case 'w' :
		case 'W' :
			if(travelList.size() > 0 )	
				return WRITE_LATEX;
			return BAD_CHOICE;
		case 'x' :
		case 'X' :
			return EXIT;
		case 'z' :
		case 'Z' :
			return ABOUT;
		default:
			return BAD_CHOICE;
		}
	}

	public static <E> E chooseFromList(String toPrint, List<E> list){
		int index = -1;
		boolean correct = false;
		while(!correct){
			System.out.println(toPrint);
			for(int i = 0 ; i < list.size() ; i++){
				System.out.println((i+1) + ". " + list.get(i));
			}
			index = scanner.nextInt()-1;
			if(index >=0 && index < list.size()){
				correct = true;
			} else {
				System.out.println("Entrez une valeur valide.");
			}
		}
	
		return list.get(index);
	}

	private static Travel importFile(){
		System.out.println("Entrez l'adresse du fichier à ouvrir : ");
		String fileName = scanner.next();
		return importFile(fileName);
	}
	
	private static Travel importFile(String fileName){
		StringBuilder sb = new StringBuilder();
		Travel toReturn = null;

		try {
			System.out.println("Import du fichier " + fileName + " en cours...");
			InputStream in = new FileInputStream(fileName);
			int previousInt = 0;
			int nextInt = 0;
			do{
				previousInt = nextInt;
				nextInt = in.read();
				if(nextInt != -1)
					sb.append((char)nextInt);
				if(nextInt == 169 && previousInt == 195){
					sb.deleteCharAt(sb.length()-1);
					sb.setCharAt(sb.length()-1, 'é');
				}
			}while(nextInt != -1);
			in.close();
			String file = sb.toString();
			toReturn = new Travel(file);
		} catch (FileNotFoundException e) {
			System.out.println("Le fichier entré n'existe pas.");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return toReturn;
	}

}

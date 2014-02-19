package userInterface;

import java.util.Scanner;

public class UISimpleInput extends UIInputWidget<String>{

	private final String toPrint;

	public UISimpleInput(String title){
		super(title);
		toPrint = "";
	}
	
	public UISimpleInput(String title, String toPrint){
		super(title);
		this.toPrint = toPrint;
	}

	

	@Override
	public String getInput() {
		Scanner scanner = new Scanner(System.in);
		System.out.println(toPrint);
		String input = scanner.next();
		scanner.close();
		return input;
	}



}

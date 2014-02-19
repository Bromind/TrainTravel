package userInterface;

import java.util.List;
import java.util.Scanner;

public class UISelectFromList<E> extends UIInputWidget<E>{

	private final List<E> list;
	private final String toPrint;
	
	public UISelectFromList(String title, String toPrint, List<E> list) {
		super(title);
		this.toPrint = toPrint;
		this.list = list;
	}
	
	@Override
	public E getInput(){
		Scanner scanner = new Scanner(System.in);
		int index = -1;
		boolean correct = false;
		while(!correct){
			System.out.println(toPrint);
			for(int i = 0 ; i < list.size() ; i++){
				System.out.println((i+1) + ". " + list.get(i));
			}
			index = scanner.nextInt();
			if(index >=0 && index < list.size()){
				correct = true;
			} else {
				System.out.println("Entrez une valeur valide.");
			}
		}
		
		scanner.close();
		return list.get(index);
	}

}

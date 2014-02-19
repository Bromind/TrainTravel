package userInterface;


import java.util.Scanner;


public class UITitleWidgetMenu<E extends UIWidget> extends UIMenu<E>{


	public UITitleWidgetMenu(){
		super("Menu");
	}

	public UITitleWidgetMenu(String title) {
		super(title);
	}

	public void addWidget(E widget){
		super.addWidget(widget);
	}

	@Override
	public E nextWidget() {
		boolean stop = false;
		int index = -1;
		Scanner scanner = new Scanner(System.in);


		StringBuilder sb = new StringBuilder();

		for(int i = 0 ; i < getWidgetTitle().length() + 4 ; i++){
			sb.append("=");
		}
		sb.append("\n| " + getWidgetTitle() + " |\n");
		for(int i = 0 ; i < getWidgetTitle().length() + 4 ; i++){
			sb.append("=");
		}
		sb.append("\n");

		for(int i = 0 ; i < super.menuSize() ; i++){
			sb.append((i+1) + ". " + super.getWidgetAtIndex(i).getWidgetTitle() + "\n");
		}

		while(!stop){
			System.out.println(sb.toString());
			index = scanner.nextInt() - 1;
			if(index >= 0 && index < super.menuSize())
				stop = true;
		}
		scanner.close();
		return super.getWidgetAtIndex(index);
	}


}

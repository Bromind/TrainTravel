package userInterface;

public abstract class UIPanel extends UIWidget{

	
	public UIPanel(String title) {
		super(title);
		System.out.println("===== Welcome to TrainTravel =====");
	}

	public abstract Object run();

}

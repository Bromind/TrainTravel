package userInterface;

public abstract class UIInputWidget<E> extends UIWidget{

	public UIInputWidget(String title) {
		super(title);
	}
	
	public Object run(){
		return getInput();
	}

	public abstract E getInput();
	
}

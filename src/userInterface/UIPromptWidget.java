package userInterface;

public abstract class UIPromptWidget extends UIWidget{

	public UIPromptWidget(String title) {
		super(title);
	}
	
	public Object run(){
		prompt();
		return null;
	}
	
	public abstract void prompt();
	
}

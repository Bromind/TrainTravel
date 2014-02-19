package userInterface;

public abstract class UILinkWidget<E extends UIWidget> extends UIWidget{

	public UILinkWidget(String title) {
		super(title);
	}
	
	public Object run(){
		return nextWidget();
	}
	
	public abstract E nextWidget();

}

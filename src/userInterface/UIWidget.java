package userInterface;


public abstract class UIWidget{
	
	private final String widgetTitle;
	
	
	public UIWidget(String title) {
		widgetTitle = title;
	}
	
	public String getWidgetTitle(){
		return widgetTitle;
	}
	
	public Object run(){
		System.out.println(widgetTitle);
		return null;
	}
}

package userInterface;

import java.util.LinkedList;
import java.util.List;

public abstract class UIMenu<E extends UIWidget> extends UILinkWidget<E>{

	private final List<E> content;

	
	public UIMenu(){
		super("Menu");
		content = new LinkedList<E>();
	}
	
	public UIMenu(String title) {
		super(title);
		content = new LinkedList<E>();

	}
	

	public E getWidgetAtIndex(int index){
		return content.get(index);
	}

	public void addWidget(E widget) {
		content.add(widget);
	}
	
	public int menuSize(){
		return content.size();
	}

}

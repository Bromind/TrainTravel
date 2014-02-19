package userInterface;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class UIList<E> extends UIPromptWidget{

	private final List<E> list;
	
	public UIList(String title, List<E> list){
		super(title);
		this.list = list;
	}
	
	public UIList(String title) {
		super(title);
		list = new LinkedList<>();
	}
	
	public int size(){
		return list.size();
	}
	
	public boolean isEmpty(){
		return list.isEmpty();
	}
	
	public boolean contains(Object o){
		return list.contains(o);
	}
	
	public void add(E e){
		list.add(e);
	}
	
	public E remove(int index){
		E e = list.get(index);
		list.remove(index);
		return e;
	}
	
	public Iterator<E> iterator(){
		return list.iterator();
	}

	public E get(int index){
		return list.get(index);
	}

	@Override
	public void prompt() {
		// TODO Auto-generated method stub
		
	}
	
}

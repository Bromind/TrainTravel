package travel;

public class Station {
	private final String name;
	
	public Station(String name) {
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	public String toString(){
		return name;
	}
	
	public int compareTo(Station anotherStation){
		return anotherStation.name.compareTo(this.name);
	}
	
}

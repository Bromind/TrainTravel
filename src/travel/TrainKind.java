package travel;

public enum TrainKind {
	IC("InterCité"),
	IR("InterRegio"),
	TER("TER"), 
	TGV("TGV"), 
	LYRIA("TGV Lyria"),
	GENERIC("Train Générique (Non répertorié)");
	private final String name;
	
	TrainKind(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
}

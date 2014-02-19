package travel;


public class Train {
	private final TrainKind train;
	private final int number;
	
	public Train(int number, TrainKind kind){
		this.train = kind;
		this.number = number;
	}
	
	public String getTrainName(){
		return train.getName();
	}
	
	public int getTrainNumber(){
		return number;
	}
	
	public String toString(){
		return train.getName() + " n° : "+ number;
	}
}

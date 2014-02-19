package run;

public enum Month {
	JANUARY("Janvier", 0), 
	FEBRUARY("Février", 1), 
	MARCH("Mars", 2),
	APRIL("Avril", 3),
	MAY("Mai", 4), 
	JUNE("Juin", 5),
	JULY("Juillet", 6), 
	AUGUST("Août", 7),
	SEPTEMBER("Septembre", 8), 
	OCTOBER("Octobre", 9), 
	NOVEMBER("Novembre", 10), 
	DECEMBER("Decembre", 11);
	String frenchName;
	int value;

	private Month(String frenchName, int value) {
		this.frenchName = frenchName;
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}
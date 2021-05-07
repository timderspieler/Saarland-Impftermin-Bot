package de.timsander.main;

public enum City {
	
	LEBACH_NIGHT("//*[@id=\"logged-in-area\"]/div/div[2]/div[1]/button[5]"),
	LEBACH("//*[@id=\"logged-in-area\"]/div/div[2]/div[1]/button[4]"),
	NEUNKIRCHEN("//*[@id=\"logged-in-area\"]/div/div[2]/div[1]/button[3]"),
	SAARLOUIS("//*[@id=\"logged-in-area\"]/div/div[2]/div[1]/button[2]"),
	SAARBRUCKEN("//*[@id=\"logged-in-area\"]/div/div[2]/div[1]/button[1]");
	
	private String path;
	
	private City(String path) {
		this.path = path;
	}
	
	public String getPath() { return path; }

}

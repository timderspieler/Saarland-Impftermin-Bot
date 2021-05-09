package de.timsander.main;

public enum City {
	
	LEBACH_NIGHT("//*[@id=\"logged-in-area\"]/div/div[2]/div[1]/button[5]", "Lebach (Nacht)", "BioNTech / Moderna"),
	LEBACH("//*[@id=\"logged-in-area\"]/div/div[2]/div[1]/button[4]", "Lebach", "Moderna"),
	NEUNKIRCHEN("//*[@id=\"logged-in-area\"]/div/div[2]/div[1]/button[3]", "Neunkirchen", "BioNTech"),
	SAARLOUIS("//*[@id=\"logged-in-area\"]/div/div[2]/div[1]/button[2]", "Saarlouis", "BioNTech"),
	SAARBRUCKEN("//*[@id=\"logged-in-area\"]/div/div[2]/div[1]/button[1]", "Saarbrücken", "BioNTech");
	
	private String path;
	private String name;
	private String vaccination_type;
	
	private City(String path, String name, String vaccination_type) {
		this.path = path;
		this.name = name;
		this.vaccination_type = vaccination_type;
	}
	
	public String getPath() { return path; }
	public String getVaccinationType() { return vaccination_type; }
	public String getName() { return name; }
	
	public static City getByName(String name) {
		
		for (City all : values()) {
			
			if (all.getName().equalsIgnoreCase(name)) {
				return all;
			}
			
		}
		
		return null;
	}

}

package cartes;

public enum ValeurCarte {

	Deux (2),
	Trois (3),
	Quatre (4),
	Cinq (5),
	Six (6),
	Sept(7),
	Huit (8),
	Neuf (9),
	Dix (10),
	Valet (11),
	Dame (12),
	Roi (13),
	As(14);
	
	private int name;

	  //Constructeur
	ValeurCarte(int name){
		this.name = name;
	}
	
	public int getValue(){
		return this.name;
	}

	public String toString(){
		return String.valueOf(name);
	}
}

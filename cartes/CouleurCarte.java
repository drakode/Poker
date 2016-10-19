package cartes;

public enum CouleurCarte {
	Coeur ("Coeur"),
	Pique ("Pique"),
	Trefle ("Trefle"),
	Carreau ("Carreau");
	
	private String name = "";

	  //Constructeur
	CouleurCarte(String name){
		this.name = name;
	}

	public String toString(){
		return name;
	}
}

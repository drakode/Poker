package cartes;

public class Carte implements Comparable<Carte>{
	protected CouleurCarte couleur;
	protected ValeurCarte valeur;
	
	public Carte(CouleurCarte couleur, ValeurCarte valeur){
		this.valeur = valeur;
		this.couleur = couleur;
	}
	
	public int compare(Carte carteToCompare){
		if(carteToCompare.valeur.getValue() < this.valeur.getValue())
			return 1;
		else if(carteToCompare.valeur.getValue() > this.valeur.getValue())
			return -1;
		else
			return 0;
	}
	
	public CouleurCarte getCouleur(){
		return this.couleur;
	}
	
	public ValeurCarte getValeur(){
		return this.valeur;
	}
	
	public String toString(){
		return this.valeur.name() + " " + this.couleur;
	}

	@Override
	public int compareTo(Carte toCompare) {
		if(this.valeur.getValue() > toCompare.valeur.getValue())
			return 1;
		else if(this.valeur.getValue() < toCompare.valeur.getValue())
			return -1;
		else
			return 0;
	}
}

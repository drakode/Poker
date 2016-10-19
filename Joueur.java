import java.util.ArrayList;

import cartes.Carte;
import cartes.Main;

public class Joueur {

	protected boolean isDealer;
	protected boolean isFolded;
	protected String name;
	protected int nbrJetons;
	protected Carte[] cartes;
	protected Main main;
	protected int currentMise;
	
	public Joueur(String name){
		this.name = name;
		this.nbrJetons = 1000;
		this.isDealer = false;
		//this.cartes = new Carte[2];
	}
	
	public void setDealer(boolean deal){
		this.isDealer = deal;
	}
	
	public void resetCards(){
		this.cartes = new Carte[2];
	}
	
	public void recevoirCarte(Carte c){
		if(cartes[0] == null)
			cartes[0] = c;
		else
			cartes[1] = c;
	}
	
	public void setMain(Carte[] flop, Carte turn, Carte river){
		ArrayList<Carte> possibilites = new ArrayList<Carte>();
		possibilites.add(this.cartes[0]);
		possibilites.add(this.cartes[1]);
		possibilites.add(flop[0]);
		possibilites.add(flop[1]);
		possibilites.add(flop[2]);
		possibilites.add(turn);
		possibilites.add(river);
		for(int i = 0; i < 6; i++){
			for(int j = i; j < 7; j++){
				if(i != j){
					@SuppressWarnings("unchecked")
					ArrayList<Carte> test = (ArrayList<Carte>) possibilites.clone();
					test.remove(j);
					test.remove(i);
					@SuppressWarnings("unchecked")
					Main m = Main.getMain((ArrayList<Carte>) test.clone());
					
					if(this.main == null)
						this.main = m;
					else
						if(this.main.compareTo(m) == -1)
							this.main = m;
				}
			}
		}
	}
	
	public Main getMain(){
		return this.main;
	}
	
	public int getNbrJetons(){
		return nbrJetons;
	}
	
	public int getMise(){
		return this.currentMise;
	}
	
	public int miser(int mise){
		if(nbrJetons >= mise){
			this.currentMise = currentMise + mise;
			nbrJetons = nbrJetons - mise;
			return mise;
		}else{
			this.currentMise = nbrJetons;
			nbrJetons = 0;
			return currentMise;
		}
	}
	
	public void resetMise(){
		this.currentMise = 0;
	}
	
	public void gagner(int gain){
		this.nbrJetons += gain;
	}
	
	public void setFolded(boolean folded){
		this.isFolded = folded;
	}
	
	public boolean isFolded(){
		return this.isFolded;
	}
	
	public String toString(){
		return name + " : " + cartes[0] + " / " + cartes[1] + " /// nbr jetons : " + nbrJetons + " /// dealer : " + isDealer +"\n";
	}
}
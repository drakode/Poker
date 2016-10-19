import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cartes.Carte;
import cartes.Combinaison;
import cartes.ValeurCarte;

public class MainOld {

	protected Carte[] cartes;
	
	public MainOld(Carte[] cartes){
		this.cartes = cartes;
	}
	
	public int getPoints(){
		return 10;
	}
	
	public int compare(){
		//comparaison des cartes de la main (cas de valeurs "paires" equival. par ex)
		return 0;
	}
	
	public Combinaison getCombinaison(Carte[] cartes){
		List<Carte> listCartes = Arrays.asList(cartes);
		ArrayList<ValeurCarte> listValeurs = new ArrayList<ValeurCarte>();
		for(Carte c : listCartes){
			listValeurs.add(c.getValeur());
		}
		if(isCouleur(cartes) && isFlush(cartes) && listCartes.contains(cartes)){
			
		}
		
		if(isFlush(cartes))
			return Combinaison.Quinte;
		
		if(isCouleur(cartes))
			return Combinaison.Couleur;
		
		return null;
	}
	
	public boolean isCouleur(Carte[] cartes){
		if(cartes[0].getCouleur().equals(cartes[1].getCouleur()))
			if(cartes[1].getCouleur().equals(cartes[2].getCouleur()))
				if(cartes[2].getCouleur().equals(cartes[3].getCouleur()))
					if(cartes[3].getCouleur().equals(cartes[4].getCouleur()))
						return true;
		
		return false;
	}
	
	private boolean isFlush(Carte[] cartes){
		List<Carte> liste = Arrays.asList(cartes);
		Collections.sort(liste);
	
		ArrayList<ValeurCarte> valeursCartes = new ArrayList<ValeurCarte>();
		for(Carte c : liste)
			valeursCartes.add(c.getValeur());
		
		ArrayList<ValeurCarte> list = new ArrayList<ValeurCarte>() ;
	    Set<ValeurCarte> set = new HashSet<ValeurCarte>() ;
	    set.addAll(list) ;
	    ArrayList<ValeurCarte> distinctList = new ArrayList<ValeurCarte>(set) ;
		
		for(int i = 0; i < distinctList.size() - 5; i++){
			for(int j = 0; j < 5; j++){
				ValeurCarte vc = distinctList.get(i+j);
				ValeurCarte vc2 = distinctList.get(i+j+1);
				
				if(!(vc.getValue() == vc2.getValue()+1))
					return false;
			}
		}
		
		//if(!(liste.get(0).getValeur().getValue().equals(ValeurCarte.Deux))
			
		//if(((liste.contains(o))))
		
		return true;
	}
}

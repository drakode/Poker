package cartes;

import java.util.ArrayList;
import java.util.Collections;

public abstract class Main implements Comparable<Main>{

	public static Main getMain(ArrayList<Carte> listeCartes){
		ArrayList<Integer> cartes = new ArrayList<Integer>();
		for(Carte c : listeCartes){
			cartes.add(c.getValeur().getValue());
		}
		Collections.sort(cartes);
		if(isSuite(cartes) && isColour(listeCartes) && cartes.get(4) == 14)
			return new QuinteFlushRoyale();
		else if(isSuite(cartes) && isColour(listeCartes))
			return new QuinteFlush(cartes.get(4));
		else if(isFour(cartes)){
			for(int i = 2; i < 15; i++){
				if(Collections.frequency(cartes, i) == 4){
					ArrayList<Integer> toRemove = new ArrayList<Integer>();
					toRemove.add(i);
					cartes.removeAll(toRemove);
					return new Carre(i, cartes.get(0));
				}
			}
		}else if(isFull(cartes)){
			for(int i = 2; i < 15; i++){
				for(int j = 3; j < 15; j++){
					if(i != j){
						if(Collections.frequency(cartes, i) == 2 && Collections.frequency(cartes, j) == 3){
							return new Full(j, i);
						}else if(Collections.frequency(cartes, i) == 3 && Collections.frequency(cartes, j) == 2){
							return new Full(i, j);
						}
					}
				}
			}
		}else if(isColour(listeCartes)){
			return new Couleur(cartes);
		}else if(isSuite(cartes)){
			return new Suite(cartes.get(4));
		}else if(isTriple(cartes)){
			for(int i = 2; i < 15; i++){
				if(Collections.frequency(cartes, i) == 3){
					ArrayList<Integer> toRemove = new ArrayList<Integer>();
					toRemove.add(i);
					cartes.removeAll(toRemove);
					return new Triple(i, cartes);
				}
			}
		}else if(isDeuxDeux(cartes)){
			for(int i = 2; i < 15; i++){
				for(int j = 3; j < 15; j++){
					if(i != j){
						if(Collections.frequency(cartes, i) == 2 && Collections.frequency(cartes, j) == 2){
							ArrayList<Integer> toRemove = new ArrayList<Integer>();
							toRemove.add(i);
							toRemove.add(j);
							cartes.removeAll(toRemove);
							if(i > j)
								return new TwoPairs(i, j, cartes.get(0));
							else if(j > i)
								return new TwoPairs(j, i, cartes.get(0));
						}
					}
				}
			}
		}else if(isPaire(cartes)){
			for(int i = 2; i < 15; i++){
				if(Collections.frequency(cartes, i) == 2){
					ArrayList<Integer> toRemove = new ArrayList<Integer>();
					toRemove.add(i);
					cartes.removeAll(toRemove);
					return new Paire(i, cartes);
				}
			}
		}
		return new HighCard(cartes);
	}
	
	public static boolean isColour(ArrayList<Carte> cartes){
		for(int i = 0; i < cartes.size()-1; i++){
			if(!(cartes.get(i).getCouleur().equals(cartes.get(i+1).getCouleur())))
				return false;
		}
		return true;
	}
	
	
	public static boolean isSuite(ArrayList<Integer> toTest){
		boolean suite = true;
		for(int i = 0; i < toTest.size()-1; i++){
			if(!(toTest.get(i) == toTest.get(i+1)-1))
				suite = false;
		}
		return suite;
	}
	
	public static boolean isTriple(ArrayList<Integer> toTest){
		boolean triple = false;
		
		for(int i = 2; i < 15; i++){
			
			if(Collections.frequency(toTest, i) == 3)
				triple = true;
		}
		
		return triple;
	}
	
	public static boolean isPaire(ArrayList<Integer> toTest){
		boolean paire = false;
		
		for(int i = 2; i < 15; i++){
			
			if(Collections.frequency(toTest, i) == 2)
				paire = true;
		}
		
		return paire;
	}
	
	public static boolean isFour(ArrayList<Integer> toTest){
		boolean four = false;
		
		for(int i = 2; i < 15; i++){
			
			if(Collections.frequency(toTest, i) == 4)
				four = true;
		}
		
		return four;
	}
	
	public static boolean isFull(ArrayList<Integer> toTest){
		boolean full = false;
		
		for(int i = 2; i < 15; i++){
			for(int j = 3; j < 15; j++){
				if(i != j){
					if((Collections.frequency(toTest, i) == 2 && Collections.frequency(toTest, j) == 3) || (Collections.frequency(toTest, i) == 3 && Collections.frequency(toTest, j) == 2))
						full = true;
				}
			}
		}
		
		return full;
	}
	
	public static boolean isDeuxDeux(ArrayList<Integer> toTest){
		boolean deuxPaires = false;
		
		for(int i = 2; i < 15; i++){
			for(int j = 3; j < 15; j++){
				if(i != j){
					if(Collections.frequency(toTest, i) == 2 && Collections.frequency(toTest, j) == 2)
						deuxPaires = true;
				}
			}
		}
		return deuxPaires;
	}	
}

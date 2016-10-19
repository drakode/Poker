package cartes;

import java.util.ArrayList;
import java.util.Collections;

public class Couleur extends Main{
	
	private ArrayList<Integer> cartes;

	public Couleur(ArrayList<Integer> cartes){
		this.cartes = cartes;
	}
	
	public int compareTo(Main m){
		if(m instanceof QuinteFlush || m instanceof QuinteFlushRoyale){
			return -1;
		}else if(m instanceof Couleur){
			Collections.sort(this.cartes);
			Collections.sort(((Couleur)m).cartes);
			for(int i = cartes.size()-1; i > 0; i--){
				if(this.cartes.get(i) > ((Couleur)m).cartes.get(i))
					return 1;
				else if(this.cartes.get(i) < ((Couleur)m).cartes.get(i))
					return 1;
			}
			return 0;
		}else{
			return 1;
		}
	}
	
	public String toString(){
		return "couleur " + " dont les cartes sont " + cartes;
	}
}

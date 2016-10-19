package cartes;

import java.util.ArrayList;
import java.util.Collections;

public class Paire extends Main {
	
	private int paire;
	private ArrayList<Integer> others;
	
	public Paire(int paire, ArrayList<Integer> others){
		this.paire = paire;
		this.others = others;
	}

	public int compareTo(Main m) {
		if(m instanceof QuinteFlush || m instanceof QuinteFlushRoyale || m instanceof Full || m instanceof Triple){
			return -1;
		}else if(m instanceof Paire){
			if(this.paire > ((Paire)m).paire)
				return 1;
			else if(this.paire < ((Paire)m).paire)
				return -1;
			else{
				Collections.sort(this.others);
				Collections.sort(((Paire)m).others);
				for(int i = others.size()-1; i > 0; i--){
					if(this.others.get(i) > ((Paire)m).others.get(i))
						return 1;
					else if(this.others.get(i) < ((Paire)m).others.get(i))
						return 1;
				}
				return 0;
			}
		}else{
			return 1;
		}
	}
	
	public String toString(){
		return "Paire de " + paire + " complete par " + others;
	}

}

package cartes;

import java.util.ArrayList;
import java.util.Collections;

public class HighCard extends Main{

	private ArrayList<Integer> others;
	
	public HighCard(ArrayList<Integer> others){
		this.others = others;
	}

	public int compareTo(Main m) {
		if(m instanceof QuinteFlush || m instanceof QuinteFlushRoyale || m instanceof Full || m instanceof Triple || m instanceof Paire){
			return -1;
		}else if(m instanceof HighCard){
			Collections.sort(this.others);
			Collections.sort(((HighCard)m).others);
			for(int i = others.size()-1; i > 0; i--){
				if(this.others.get(i) > ((HighCard)m).others.get(i))
					return 1;
				else if(this.others.get(i) < ((HighCard)m).others.get(i))
					return 1;
			}
			return 0;
		}else
			return -1;
	}
	
	public String toString(){
		return "rien de particulier : " + others;
	}
}

package cartes;

import java.util.ArrayList;
import java.util.Collections;

public class Triple extends Main{
	private int triple;
	private ArrayList<Integer> others;
	
	public Triple(int triple, ArrayList<Integer> others){
		this.triple = triple;
		this.others = others;
	}
	
	public int compareTo(Main m){
		if(m instanceof QuinteFlush || m instanceof QuinteFlushRoyale || m instanceof Full){
			return -1;
		}else if(m instanceof Triple){
			if(this.triple > ((Triple)m).triple)
				return 1;
			else if(this.triple < ((Triple)m).triple)
				return -1;
			else{
				Collections.sort(this.others);
				Collections.sort(((Triple)m).others);
				for(int i = others.size()-1; i > 0; i--){
					if(this.others.get(i) > ((Triple)m).others.get(i))
						return 1;
					else if(this.others.get(i) < ((Triple)m).others.get(i))
						return 1;
				}
				return 0;
			}
		}else{
			return 1;
		}
	}
	
	public String toString(){
		return "Brelan de " + triple + " complete par " + others;
	}
}

package cartes;

public class Full extends Main {
	
	private int paire;
	private int triple;
	
	public Full(int triple, int paire){
		this.triple = triple;
		this.paire = paire;
	}

	public int compareTo(Main m) {
		if(m instanceof QuinteFlushRoyale || m instanceof QuinteFlush){
			return -1;
		}else if(m instanceof Full){
			if(this.triple > ((Full)m).triple)
				return 1;
			else if(this.triple < ((Full)m).triple)
				return -1;
			else{
				if(this.paire > ((Full)m).paire)
					return 1;
				else if(this.paire < ((Full)m).paire)
					return -1;
				else
					return 0;
			}
		}
		return 1;
	}

	public String toString(){
		return "Full avec 3 " + triple + " et deux " + paire;
	}
}

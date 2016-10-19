package cartes;

public class Carre extends Main {
	
	private int carre;
	private int other;
	
	public Carre(int four, int other){
		this.carre = four;
		this.other = other;
	}

	public int compareTo(Main m) {
		if(m instanceof QuinteFlushRoyale)
			return -1;
		else if(m instanceof Carre){
			if(this.carre > ((Carre)m).carre)
				return 1;
			else if(this.carre < ((Carre)m).carre)
				return -1;
			else{
				if(this.other > ((Carre)m).other)
					return 1;
				else if(this.other < ((Carre)m).other)
					return -1;
				else
					return 0;
			}
		}else{
			return 1;
		}
	}
	
	public String toString(){
		return "Carré de " + carre + " complete par " + other;
	}

}

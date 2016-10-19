package cartes;

public class QuinteFlush extends Main{
	
	private int highestValue;
	
	public QuinteFlush(int highestValue){
		this.highestValue = highestValue;
	}

	public int compareTo(Main m){
		if(m instanceof QuinteFlushRoyale)
			return -1;
		else if(m instanceof QuinteFlush){
			if(this.highestValue > ((QuinteFlush)m).highestValue)
				return 1;
			if(this.highestValue == ((QuinteFlush)m).highestValue)
				return 0;
			else
				return -1;
		}else
			return -1;
	}
	
	public String toString(){
		return "Suite de couleur dont la plus haute valeur est " + highestValue;
	}
}

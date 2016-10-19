package cartes;

public class Suite extends Main{

	private int highestValue;
	
	public Suite(int highestValue){
		this.highestValue = highestValue;
	}

	public int compareTo(Main m){
		if(m instanceof QuinteFlushRoyale || m instanceof QuinteFlush || m instanceof Full || m instanceof Carre)
			return -1;
		else if(m instanceof Suite){
			if(this.highestValue > ((Suite)m).highestValue)
				return 1;
			if(this.highestValue == ((Suite)m).highestValue)
				return 0;
			else
				return -1;
		}else
			return -1;
	}
	
	public String toString(){
		return "Suite dont la plus haute valeure est " + highestValue;
	}
}

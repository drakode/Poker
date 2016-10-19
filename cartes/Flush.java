package cartes;

public class Flush extends Main{
	
	private int highestValue;
	
	public Flush(int highestValue){
		this.highestValue = highestValue;
	}

	public int compareTo(Main m){
		if(m instanceof QuinteFlushRoyale)
			return -1;
		else if(m instanceof Flush){
			if(this.highestValue > ((Flush)m).highestValue)
				return 1;
			if(this.highestValue == ((Flush)m).highestValue)
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

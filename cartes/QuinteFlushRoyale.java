package cartes;

public class QuinteFlushRoyale extends Main{
	
	public int compareTo(Main m){
		if(m instanceof QuinteFlushRoyale)
			return 0;
		else
			return 1;
	}
	
	public String toString(){
		return "Quinte flush royale mageule";
	}
}

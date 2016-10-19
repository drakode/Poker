package cartes;

public class TwoPairs extends Main {
	
	private int highestPair;
	private int lowestPair;
	private int otherCard;
	
	public TwoPairs(int highestPair, int lowestPair, int otherCard){
		this.highestPair = highestPair;
		this.lowestPair = lowestPair;
		this.otherCard = otherCard;
	}

	public int compareTo(Main m) {
		if(m instanceof QuinteFlush || m instanceof QuinteFlushRoyale || m instanceof Full){
			return -1;
		}else if(m instanceof TwoPairs){
			if(this.highestPair > ((TwoPairs)m).highestPair){
				return 1;
			}else if(this.highestPair < ((TwoPairs)m).highestPair){
				return -1;
			}else{
				if(this.lowestPair > ((TwoPairs)m).lowestPair){
					return 1;
				}else if(this.lowestPair < ((TwoPairs)m).lowestPair){
					return -1;
				}else{
					if(this.otherCard > ((TwoPairs)m).otherCard){
						return 1;
					}else if(this.otherCard < ((TwoPairs)m).otherCard){
						return -1;
					}else{
						return 0;
					}
				}
			}
		}else{
			return 1;
		}
	}
	
	public String toString(){
		return "Deux paires dont la plus forte est : " + highestPair + " et la moins forte est " + lowestPair + ", complete par " + otherCard;
	}

}

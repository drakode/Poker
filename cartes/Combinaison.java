package cartes;

import java.util.ArrayList;
import java.util.Arrays;

public enum Combinaison {
	QuinteFlushRoyale (100),
	QuinteFlush (50),
	Carre (40),
	Full (30),
	Couleur(20),
	Quinte(15),
	Brelan(10),
	DeuxPaires(5),
	Paire(2),
	HighCard(1);
	
	private int value;

	Combinaison(int value){
		this.value = value;
	}

	public String toString(){
		return String.valueOf(value);
	}
}
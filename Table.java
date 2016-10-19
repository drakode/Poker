import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Scanner;

import cartes.Carte;
import cartes.CouleurCarte;
import cartes.ValeurCarte;

public class Table {

	protected ArrayList<Joueur> joueurs;
	protected ArrayList<Carte> cartes;
	protected Joueur currentDealer;
	protected int currentMise;
	protected int currentBlind;
	
	protected int pot;
	
	private Carte turn;
	
	private Carte river;
	
	private Carte[] flop;
	
	public Table(){
		joueurs = new ArrayList<Joueur>();
		cartes = new ArrayList<Carte>();
		flop = new Carte[3];
		currentBlind = 10;
		getPaquet();
	}
	
	public void getPaquet(){
		for(CouleurCarte couleur : CouleurCarte.values()){
			for(ValeurCarte valeur : ValeurCarte.values()){
				cartes.add(new Carte(couleur, valeur));
			}
		}
		
		/*Carte[] test = new Carte[5];
		test[0] = cartes.get(1);
		test[1] = cartes.get(2);
		test[2] = cartes.get(12);
		test[3] = cartes.get(9);
		test[4] = cartes.get(5);
		//test[5] = cartes.get(20);
		//test[6] = cartes.get(42);
		
		ArrayList<Carte> tmp = new ArrayList<Carte>(Arrays.asList(test));
		Main m = Main.getMain(tmp);
		System.out.println(m);
		System.out.println(tmp);*/
	}
	
	public void start(){
		currentDealer = joueurs.get(0);
		while(joueurs.size() >= 2){
			for(int i = 0; i < 1; i++){
				lancerTour();
			}
			
			break;
		}
	}
	
	public void melanger(){
		Collections.shuffle(cartes);
	}
	
	public void lancerTour(){
		this.pot = 0;
		
		for(Joueur j : joueurs)
			j.resetCards();
		
		getPaquet();
		
		updateDealer();
		
		melanger();
		
		distribuer();
		
		blinds();
		System.out.println("-------blind--------");System.out.println(toString());
		mises();
		System.out.println("mises post blind");System.out.println(toString());
		flop();
		System.out.println("-------flop--------");System.out.println(toString());
		mises();
		System.out.println("mises post flop");System.out.println(toString());
		turn();
		System.out.println("-------turn--------");System.out.println(toString());
		mises();
		System.out.println("mises post turn");System.out.println(toString());
		river();
		System.out.println("-------river--------");System.out.println(toString());
		mises();
		System.out.println("mises post river"); System.out.println(toString());
		finirTour();
		
		System.out.println(toString());
	}
	
	public void addPlayer(Joueur j){
		joueurs.add(j);
	}
	
	public void updateDealer(){
		int indexOldDealer = joueurs.indexOf(currentDealer);
		int indexNewDealer = indexOldDealer + 1;
		if(indexNewDealer >= joueurs.size())
			indexNewDealer = 0;
		
		currentDealer.setDealer(false);
		currentDealer = joueurs.get(indexNewDealer);
		currentDealer.setDealer(true);
	}
	
	public void blinds(){
		int indexDealer = joueurs.indexOf(currentDealer);
		int petiteBlind = indexDealer + 1;
		if(petiteBlind >= joueurs.size())
			petiteBlind = 0;
		
		int grosseBlind = petiteBlind + 1;
		if (grosseBlind >= joueurs.size())
			grosseBlind = 0;
		
		pot += joueurs.get(petiteBlind).miser(currentBlind);
		pot += joueurs.get(grosseBlind).miser(currentBlind*2);
	}
	
	public void distribuer(){
		for(int i = 0; i < 2; i++){
			int indexDealer = joueurs.indexOf(currentDealer);
			int indexJoueurAdistribuer;
			for(int j = 0; j < joueurs.size(); j++){
				indexJoueurAdistribuer = indexDealer  + j;
				if(indexJoueurAdistribuer >= joueurs.size())
					indexJoueurAdistribuer = indexJoueurAdistribuer - joueurs.size();
				
				Joueur receveur = joueurs.get(indexJoueurAdistribuer);
				
				Carte aDistribuer = this.prendreCarte();
				receveur.recevoirCarte(aDistribuer);
			}
		}
	}
	
	public void flop(){
		cartes.remove(0); // on brule 1 carte
		for(int i = 0; i < flop.length; i++){
			flop[i] = prendreCarte();
		}
	}
	
	public void turn(){
		cartes.remove(0); // brule 1 carte
		
		this.turn = cartes.remove(0);
	}
	
	public void river(){
		cartes.remove(0); // brule 1 carte
		
		this.river = cartes.remove(0);
	}
	
	public Carte prendreCarte(){
		return this.cartes.remove(0);
	}
	
	public String toString(){
		String s = "/////////////////////////////////////////////////////\n";
		for(Joueur j : joueurs){
			s+= j.toString();
		}
		if(flop != null)
			s+= "\nflop : " + flop[0] + "/" + flop[1] + "/" + flop[2];
		if(turn != null)
			s+= "\nturn : " + turn;
		if(river != null)
			s += "\nriver : " + river;
		
		s+= "\npot actuel : " + pot;
		return s + "\n///////////////////////////////////////////////\n";
	}
	
	public void mises(){
			
		boolean miseOk = false;
		int currentMise = currentBlind*2;
		
		//on redetermine la petite blind
		int indexDealer = joueurs.indexOf(currentDealer);
		int petiteBlind = indexDealer + 1;
		if(petiteBlind >= joueurs.size())
			petiteBlind = 0;
		
		//idem grosse blind
		int grosseBlind = petiteBlind + 1;
		if (grosseBlind >= joueurs.size())
			grosseBlind = 0;
		
		//puis le premier a miser
		int currentMiseur = grosseBlind + 1;
		if (currentMiseur >= joueurs.size())
			currentMiseur = 0;
		
		while(!miseOk){
			Joueur j = joueurs.get(currentMiseur);
			String saisie = "";
			while(!saisieCorrecte(saisie)){
				System.out.println("Veuillez saisir 'F' pour folder, 'A' pour all in, 'S' pour suivre ou 'Rx' avec x un nombre pour relancer la mise de x");
				saisie = new Scanner(System.in).nextLine();
			}
			if(saisie.equals("F")){
				j.setFolded(true);
			}else if(saisie.equals("S")){
				int miseJoueur = j.getMise();
				if(miseJoueur < currentMise){
					int miseAAjouter = currentMise - miseJoueur;
					int jetonsRestantsJoueur = j.getNbrJetons();
					if(jetonsRestantsJoueur >= miseAAjouter){
						j.miser(miseAAjouter);
						pot += miseAAjouter;
					}else{
						j.miser(jetonsRestantsJoueur);
						pot += jetonsRestantsJoueur;
					}
				}
			}else if(saisie.equals("A")){
				int jetonsRestantsJoueur = j.getNbrJetons();
				j.miser(jetonsRestantsJoueur);
				pot += jetonsRestantsJoueur;
				if(j.getMise() > currentMise)
					currentMise = j.getMise();
			}else if(saisie.matches("R\\d+")){
				String relanceS = saisie.substring(1);
				int relance = Integer.parseInt(relanceS);
				int miseSouhaitee = currentMise + relance;
				int miseActuelleJoueur = j.getMise();
				int resteAMiser = miseSouhaitee - miseActuelleJoueur;
				int jetonsRestants = j.getNbrJetons();
				if(jetonsRestants >= resteAMiser){
					j.miser(resteAMiser);
					pot += resteAMiser;
				}else{
					j.miser(jetonsRestants);
					pot += jetonsRestants;
				}
			}else{
				throw new IllegalArgumentException("Paramètre invalide!");
			}
			miseOk = miseOk(currentMise);
			
			currentMiseur++;
			if(currentMiseur >= joueurs.size())
				currentMiseur = 0;
		}
		
		for(Joueur j : joueurs){
			j.resetMise();
		}
	}
	
	public boolean miseOk(int currentMise){
		for(Joueur j : joueurs){
			
			if(!(j.isFolded() || j.getMise() == currentMise || j.getNbrJetons() == 0))
				return false;
		}
		return true;
	}
	
	public boolean saisieCorrecte(String saisie){
		return saisie.equals("S") || saisie.equals("F") || saisie.equals("A") || saisie.matches("R\\d+");
	}
	
	public void finirTour(){
		for(Joueur j : joueurs){
			j.setMain(flop, turn, river);
		}
		
		ArrayList<Joueur> vainqueurs = new ArrayList<Joueur>();
		vainqueurs.add(joueurs.get(0));
		for(Joueur j : joueurs){
			if(j.getMain().compareTo(vainqueurs.get(0).getMain()) == 1){
				vainqueurs = new ArrayList<Joueur>(); //au cas ou il y deux joueurs a egalite pour le moment
				vainqueurs.add(j);
			}else if(j.getMain().compareTo(vainqueurs.get(0).getMain()) == 0 && !vainqueurs.get(0).equals(j)){
				vainqueurs.add(j);
			}
		}
		
		int cagnotte = this.pot / vainqueurs.size();
		for(Joueur j : vainqueurs){
			System.out.println("vainqueur tour " + j.name + " avec la main " + j.getMain());
			j.gagner(cagnotte);
		}
		
		for(Joueur j : joueurs){
			j.resetMise();
		}
		
		for(Joueur j : joueurs){
			if(j.getNbrJetons() == 0)
				joueurs.remove(j);
		}
	}
}

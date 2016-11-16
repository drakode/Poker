package partie;

import java.util.Scanner;

/**
 * @version 1.0
 * @author L�o
 * Cette classe g�re le d�roulement d'une partie
 */
public class Partie {
	
	/**la table sur laquelle se d�roule la partie*/
	protected Table table;
	
	/**
	 * Constructeur qui initialise, lance et termine une partie
	 */
	public Partie(){
		this.table = new Table();
		
		ajoutJoueurs();
		begin();
		resultats();
	}
	
	/**
	 * Cette methode permet d'ajouter les joueurs un � un pendant la phase d'ajout
	 */
	public void ajoutJoueurs(){
		String saisie = "";
		while(!saisie.equals("start")){
			System.out.println("Saisissez add pour ajouter un joueur, start pour commencer la partie");
			saisie = new Scanner(System.in).nextLine();
			
			if(saisie.equalsIgnoreCase("add")){
				System.out.println("\nSaisissez le nom du joueur � ajouter");
				String nomJoueur = new Scanner(System.in).nextLine();
				table.addPlayer(new Joueur(nomJoueur));
			}else if(saisie.equals("start")){
				if(!(table.getJoueurs().size() >= 2)){
					System.out.println("Pour commencer la partie, la table doit contenir au moins deux joueurss");
					saisie = "";
				}
			}else{
				System.out.println("Je n'ai pas compris votre demande. Veuillez effectuer une saisie valide");
			}
		}
	}
	
	/**
	 * Cette m�thode lance la partie avec les joueurs qui ont �t� ajout�s
	 */
	public void begin(){
		System.out.println("===================================================");
		System.out.println("================= Debut de partie =================");
		System.out.println("===================================================");
		
		table.start();
		
		while(!isFinished()){
			jouerTour();
		}
	}
	
	/**
	 * Cette m�thode permet de jouer un tour, en sebasant sur les m�thodes de la classe Table
	 */
	public void jouerTour(){
		System.out.println("----------------debut du tour----------------");
		for(Joueur j : table.getJoueurs())
			j.resetCards();
		
		table.getPaquet();
		
		table.updateDealer();
		
		table.melanger();
		
		table.blinds();
		
		table.distribuer();

		table.mises(true);

		table.flop();

		table.mises(false);

		table.turn();

		table.mises(false);

		table.river();

		table.mises(false);

		table.finirTour();
		
		System.out.println("----------------fin du tour----------------");
		System.out.println(table.toString());
	}
	
	/**
	 * Cette m�thode indique si la partie est finie ou non
	 * @return true si la partie est finie (il ne reste qu'un joueur autour de la table), false sinon
	 */
	public boolean isFinished(){
		return !(table.getJoueurs().size() >= 2);
	}
	
	/**
	 * Cette m�thode affiche es r�sultats en fin de partie
	 */
	public void resultats(){
		System.out.println("C'est fini!");
	}
	
	public static void main(String[] args){
		do{
			new Partie();
			
			System.out.println("---------------------------------------");
			System.out.println("||         Rejouer (OUI/NON)         ||");
			System.out.println("---------------------------------------");
		}while(new Scanner(System.in).nextLine().equals("OUI"));
	}
}

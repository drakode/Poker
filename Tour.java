
public class Tour {

	private int montantBlind;
	private int montantMise;
	
	public Tour(Partie partie){
		Table table = partie.getTable();
		
		Mise mise = new Mise();
		
		
		table.updateDealer();
		table.blinds();
	}
	
}

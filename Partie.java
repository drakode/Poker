import java.util.ArrayList;


public class Partie {
	
	protected Table table;
	protected ArrayList<Tour> tours;
	protected boolean isFinished;
	
	public Partie(){
		this.table = new Table();
		this.isFinished = false;
	}
	
	public void begin(){
		while(!isFinished){
			Tour currentTurn = new Tour(this);
			
			tours.add(currentTurn);
		}
	}
	
	public Table getTable(){
		return this.table;
	}

}

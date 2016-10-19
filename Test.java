
public class Test {

	public static void main(String[] args){
		 /*String host = "127.0.0.1";
	      int port = 2345;
	      
	      //creation du serveur
	      TCPServer ts = new TCPServer();
	      ts.open();
	      
	      System.out.println("Serveur initialisé.");
	      
	      //creation des clients
	      ClientConnection c1 = new ClientConnection(host, port);
	      ClientConnection c2 = new ClientConnection(host, port);


	      c1.play();
	      c2.play();*/
		
		//Partie p = new Partie();
		Table t = new Table();
		
		Joueur j1 = new Joueur("Leo");
		Joueur j2 = new Joueur("Thomas");
		Joueur j3 = new Joueur("Vincent");
		Joueur j4 = new Joueur("David");
	
		t.addPlayer(j1);
		t.addPlayer(j2);
		t.addPlayer(j3);
		t.addPlayer(j4);
		
		t.start();
		System.out.println(t.toString());
	}
	
}

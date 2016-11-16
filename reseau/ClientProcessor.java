package reseau;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;

import partie.Joueur;
import partie.Table;

public class ClientProcessor implements Runnable{

   private Socket sock;
   private PrintWriter writer = null;
   private BufferedInputStream reader = null;
   
   private Table table;
   
   private static int id = 0;
   
   public ClientProcessor(Socket pSock){
      sock = pSock;
      table = new Table();
   }
   
   //Le traitement lanc� dans un thread s�par�
   public void run(){
      System.out.println("Lancement du traitement de la connexion cliente");

      boolean closeConnexion = false;
      //tant que la connexion est active, on traite les demandes
      while(!sock.isClosed()){
         
         try {
            //Ici, nous n'utilisons pas les m�mes objets que pr�c�demment
            //Je vous expliquerai pourquoi ensuite
            writer = new PrintWriter(sock.getOutputStream());
            reader = new BufferedInputStream(sock.getInputStream());
            
            //On attend la demande du client            
            String response = read();
            
            //On affiche quelques infos, pour le d�buggage
            String debug = "\t -> Commande re�ue : " + response + "\n";
            System.err.println("\n" + debug);
            
            //On traite la demande du client en fonction de la commande envoy�e
            String toSend = "";
            
            if(response.equals("GET_ID")){
            	id++;
         	   	toSend = "id" + id;
            }else if(response.matches("PLAY\\|id\\d{1,2}\\|\\w*")){
            	String idJoueur = response.split("|")[1];
            	String nomJoueur = response.split("|")[2];
            	
            	table.addPlayer(new Joueur(nomJoueur));
            	toSend = "OK";
            }else{
            	toSend = "Commande inconnue >_<";
            }
            
            //On envoie la r�ponse au client
            writer.write(toSend);
            //Il FAUT IMPERATIVEMENT UTILISER flush()
            //Sinon les donn�es ne seront pas transmises au client
            //et il attendra ind�finiment
            writer.flush();
            
            if(closeConnexion){
               System.err.println("COMMANDE CLOSE DETECTEE ! ");
               writer = null;
               reader = null;
               sock.close();
               break;
            }
         }catch(SocketException e){
            System.err.println("LA CONNEXION A ETE INTERROMPUE ! ");
            break;
         } catch (IOException e) {
            e.printStackTrace();
         }         
      }
   }
   
   //La m�thode que nous utilisons pour lire les r�ponses
   private String read() throws IOException{      
      String response = "";
      int stream;
      byte[] b = new byte[4096];
      stream = reader.read(b);
      response = new String(b, 0, stream);
      return response;
   }
   
}
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
   
   //Le traitement lancé dans un thread séparé
   public void run(){
      System.out.println("Lancement du traitement de la connexion cliente");

      boolean closeConnexion = false;
      //tant que la connexion est active, on traite les demandes
      while(!sock.isClosed()){
         
         try {
            //Ici, nous n'utilisons pas les mêmes objets que précédemment
            //Je vous expliquerai pourquoi ensuite
            writer = new PrintWriter(sock.getOutputStream());
            reader = new BufferedInputStream(sock.getInputStream());
            
            //On attend la demande du client            
            String response = read();
            
            //On affiche quelques infos, pour le débuggage
            String debug = "\t -> Commande reçue : " + response + "\n";
            System.err.println("\n" + debug);
            
            //On traite la demande du client en fonction de la commande envoyée
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
            
            //On envoie la réponse au client
            writer.write(toSend);
            //Il FAUT IMPERATIVEMENT UTILISER flush()
            //Sinon les données ne seront pas transmises au client
            //et il attendra indéfiniment
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
   
   //La méthode que nous utilisons pour lire les réponses
   private String read() throws IOException{      
      String response = "";
      int stream;
      byte[] b = new byte[4096];
      stream = reader.read(b);
      response = new String(b, 0, stream);
      return response;
   }
   
}
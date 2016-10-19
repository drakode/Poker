import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientConnection{

   private Socket connexion = null;
   private PrintWriter writer = null;
   private BufferedInputStream reader = null;
   
   private String id;
      
   public ClientConnection(String host, int port){
      try {
         connexion = new Socket(host, port);
         
         writer = new PrintWriter(connexion.getOutputStream(), true);
         reader = new BufferedInputStream(connexion.getInputStream());

         getId();
      } catch (UnknownHostException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
   
   
   private void getId(){
         try {  
            //On envoie la commande au serveur
            
            String commande = "GET_ID";
            writer.write(commande);
            writer.flush();  

            
            //On attend la réponse
            String response = read();
            System.out.println(response);
            this.id = response;
            
         } catch (IOException e1) {
            e1.printStackTrace();
         }
   }
   
   public void play(){
	   try{
	       String commande = "PLAY|"+id+"|lqjselsr";
	       writer.write(commande);
	       writer.flush();  
	
	       
	       //On attend la réponse
	       String response = read();
	       System.out.println(response);
	       this.id = response;
   
	   }catch(IOException io){
		   io.printStackTrace();
	   }
   }
   
   //Méthode pour lire les réponses du serveur
   private String read() throws IOException{      
      String response = "";
      int stream;
      byte[] b = new byte[4096];
      stream = reader.read(b);
      response = new String(b, 0, stream);      
      return response;
   }   
}
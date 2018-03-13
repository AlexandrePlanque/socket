/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serveursocket.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author alexandreplanque
 */
public class client {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
       
        String ip = "";
        int port = 0;
        String message ="";
        
        // objet capturant le flux de System.in
        Scanner scanner = new Scanner(System.in);
        
        //Demande de l'adresse IP
        System.out.println("Veuillez entrer l'adresse IP:");
        
        if(scanner.hasNext()){
            ip = scanner.nextLine();
        }
        
        //Demande du port
        System.out.println("Veuillez entrer le port:");
        
        if(scanner.hasNext()){
            port = scanner.nextInt();
        }

        // création d'un socket pour se connecter sur un serveur
        Socket s = new Socket(ip, port);
        // objet PrintWriter pour plus de convivialité
        PrintWriter out = new PrintWriter(s.getOutputStream());
        
        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        
        
        String response = "";
        
        while (!(response = in.readLine()).contentEquals("q")) {
            System.out.println(response);
            
            // hasNext est bloquant tant que le flux n'est pas reçu par l'objet
            if(scanner.hasNext()){
                // on stocke le flux dans une variable
                message = scanner.nextLine();
                // si le caractère "q" est reçu on sort du while
                if(message.contentEquals("q")){
                    break;
                }else{
                    // message dans le tampon de sortie
                    out.println(message);
                    // envoi du message
                    out.flush();
                    // fermeture du flux
                    
                }
            }
        }
        
        

        s.close();
        scanner.close();
    }
    
}

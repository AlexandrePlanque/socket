/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serveursocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author alexandreplanque
 */
public class ServeurSocket {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // Socket spéciale serveur écoute sur 192.168.1.56:12107
        ServerSocket server = new ServerSocket(12107, 0, InetAddress.getByName("192.168.1.56"));
        Scanner scan = new Scanner(System.in);
        String reponse = "";

        Socket client = null;
        // attente d'une connexion cliente, retourn le handler du canal
        client = server.accept();
        System.out.println("connexion du client");
        //préparation de la gestion du flux entrant
        InputStream in = client.getInputStream();
        InputStreamReader reader = new InputStreamReader(in);
        BufferedReader buffReader = new BufferedReader(reader);
        String ligne = "";

        PrintWriter out = new PrintWriter(client.getOutputStream());
        out.println("La connexion est établie, vous pouvez désormais communiquer avec le serveur, si vous souhaitez arrêter taper 'q'.");

        out.flush();
        //tant que le client ne retourne pas "q" on lit les messages des clients
        while (!(ligne = buffReader.readLine()).contentEquals("q")) {
            System.out.println(ligne);
// utilisation du scanner (commenter la ligne avec "reçu par le serveur" pour éviter d'envoyer deux messages
//            if(scan.hasNext()){
//                reponse = scan.nextLine();
//                out.println(reponse);
//                out.flush();
//            }
            //envoi d'un message au client pour ne pas qu'il reste en attente
            out.println("Reçu par le serveur.");
            out.flush();
        }

        //fermeture des canaux avec un message pour le client
        out.println("Vous êtes en cours de déconnexion, au revoir.");
        buffReader.close();
        reader.close();
        in.close();
        client.close();
        server.close();
    }

}

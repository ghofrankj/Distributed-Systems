/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mutlthreadstcp;

import java.io.*; 
import java.net.*; 
import java.util.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ghadeer
 */
public class Server {
    
    static List<ClientHandler> clients;
    ServerSocket serverSocket;
    static int numOfUsers = 0;
    Socket socket;    
    
    Server(){
        clients = new ArrayList<>();
        try {
            serverSocket  = new ServerSocket(Constants.PORT);
        } catch (IOException ex) {
            log("Constructor : " + ex.getMessage());
        }
    }
    
    public static void main(String[] args) throws IOException  
    { 
        Server server = new Server();
        server.waitConnection();
    }
    
    private void waitConnection(){
        log("Server Running...");
        while(true){
            try {
                socket = serverSocket.accept();
            } catch (IOException ex) {
                log("waitConnection : " + ex.getMessage());
            }
            
            log("Client accepted : " + socket.getInetAddress());
            numOfUsers++;
            
            ClientHandler handler = new ClientHandler(socket, "user" + numOfUsers);
            
            Thread thread = new Thread(handler);
            
            addClient(handler);
            
            thread.start();
        }
    }
    public static List<ClientHandler> getClients(){
        return clients;
    }
    
    private void addClient(ClientHandler handler){
        this.clients.add(handler);
    }
    
    private void log(String message){
        System.out.println(message);
    }
}

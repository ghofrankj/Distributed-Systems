/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcpserverclient;

import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ghadeer
 */
public class Server {
    
    private Socket socket = null;
    private ServerSocket serverSocket = null;
    private DataInputStream input = null;
    
    public Server(){}
    
    public static void main(String[] args){
        Server server = new Server();
        
        server.startConnection();
        server.Connecting();
        server.stopConnection();
    }
    
    private void startConnection(){
        openSocket();
        waitingConnection();
        openInputStream();
    }
    
    private void Connecting(){
        String line = "";
        while(! line.equals(Constants.STOP)){
            line = read();
            System.out.println("Client : " + line);
        }
    }
    
    private void stopConnection(){
        closeSocket();
        closeInputStream();
    }
    
    private void openSocket(){
        try{
            serverSocket = new ServerSocket(Constants.PORT);
            log("Server Started...");
        }catch(IOException ex){
            log("start server : " + ex.getMessage());
        }
    }
    
    private void waitingConnection(){
        log("Waiting for client...");
        
        try {
            socket = serverSocket.accept();
            log("Client accepted");
            
        } catch (IOException ex) {
            log("waiting Connection : " + ex.getMessage());
        }        
    }
    
    private void openInputStream(){
        try{
            input = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            
        }catch(IOException ex){
            log("open InputStream : " + ex.getMessage());
        }
    }
    
    private String read(){
        String line = "";
        try{
            line = input.readUTF();            
        }catch(IOException ex){
            log("read : " + ex.getMessage());
        }
        return line;
    }
   
    private void closeSocket(){
        try{
            serverSocket.close();
            socket.close();            
        }catch(IOException ex){
            log("close socket : " + ex.getMessage());
        }
    }
    
    private void closeInputStream(){
        try{
            input.close();
        }catch(IOException ex){
            log("close Input Stream : " + ex.getMessage());
        }
    }
    
    private void log(String message){
        System.out.println(message);
    }
}

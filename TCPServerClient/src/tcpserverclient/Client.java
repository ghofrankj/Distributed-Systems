/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcpserverclient;
import java.net.*;
import java.io.*;
/**
 *
 * @author Radi
 */
public class Client {
    
    private Socket socket = null;
    private DataInputStream input = null;
    private DataOutputStream output = null;

    public Client(){}
    
    public static void main(String[] args) {
    Client client = new Client();
    client.startConnection();
    client.connecting();
    client.stopConnection();
    }     
    
    private void startConnection(){
        openSocket();
        if(socket != null){
            openStreams();            
        }
        log("Start Client");
    }

    private void connecting(){
        String line = "";
        while(! line.equals(Constants.STOP)){
            line = read();
            write(line);
        }
    }

    private void stopConnection(){
        closeStreams();
        closeSocket();
        log("Close Client");
    }

    private void openSocket(){
        try{
            socket= new Socket(Constants.IP, Constants.PORT);

        }catch(UnknownHostException ex){
            log("openSocket : " + ex.getMessage());
        }catch(IOException ex){
            log("openSocket : " + ex.getMessage());
        }
    }
    
    private void openStreams(){
    try{
        input = new DataInputStream((System.in));
        output = new DataOutputStream(socket.getOutputStream());
    }catch(IOException ex){
        log("openStreams : " + ex.getMessage());
    }
}
    
    private void closeSocket(){
        try{
           socket.close();
        }catch(IOException ex){
           log("closeSocket : " + ex.getMessage());
        }
    }
    
    private void closeStreams(){
        try{
            input.close();
            output.close();
        }catch(IOException ex){
            log("closeStreams : " + ex.getMessage());
        }
    }
    
    private String read(){
        String line = " " ;
        try{
            line = input.readLine();
        }catch(IOException ex){
            log("read : " + ex.getMessage());
        }  
       return line;
    }

    private void write(String message){
        try{
            output.writeUTF(message);

        }catch(IOException ex){
            log("write : " + ex.getMessage());
        }
    }
    
    private void log(String message){
    System.out.println(message);
    }
}

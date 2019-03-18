/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package udpserverclient;
import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Ghadeer
 */
public class Server {
    DatagramSocket socket = null;
    byte[] receiverBuffer = null;
    DatagramPacket receivePacket = null;
    
    public static void main(String[] args){
        Server server = new Server();
        server.initializeVarible();
        server.connecting();
       
    }
    
    private void initializeVarible(){        
        try {
            socket = new DatagramSocket(Constants.PORT);
            receiverBuffer = new byte[Constants.BUFFER_SIZE];
        } catch (SocketException ex) {
            log("initializeVarible : " + ex.getMessage());
        }        
    }
    
    private void connecting(){
        while(true){
            String data = receiveData();
            log("Client : " + data);
            
            if(data.equals(Constants.STOP)){
                log("Client say bye...Exiting");
                break;
            }
            
            receiverBuffer = new byte[Constants.BUFFER_SIZE];
        }
    }
    private String receiveData(){      
            String line = ""   ;
        try {
            receivePacket = new DatagramPacket(receiverBuffer,receiverBuffer.length);
            socket.receive(receivePacket);
            line = new String(receivePacket.getData(),0,receivePacket.getLength());
        } catch (IOException ex) {
            log("receiveData : " + ex.getMessage());
        }
        return line;
    }
    private void log(String message){
        System.out.println(message);
    }
}

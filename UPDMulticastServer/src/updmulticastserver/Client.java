/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package updmulticastserver;
import java.net.*;
import java.io.*;
/**
 *
 * @author Ghadeer
 */

public class Client {
    
    MulticastSocket socket = null;
    byte[] buffer = null;
    DatagramPacket packet = null;
    InetAddress ip = null;
    
    public static void  main(String[] args){
       Client client = new Client();
       client.initializeVarible();
       client.connecting();
    }
    
    private void connecting(){    
        joinGroup();
        
        while(true){
            String line = receiveData();
            log("Client Received : " + line);
        }
    }

    private void initializeVarible() {
        try {
            socket = new MulticastSocket(Constants.PORT);
            ip =  InetAddress.getByName(Constants.IP);
            buffer = new byte[Constants.BUFFER_SIZE];
            
        } catch (SocketException ex) {
            log("initializeVarible : " + ex.getMessage());
        } catch (IOException ex) {
            log("initializeVarible : " + ex.getMessage());
        }
    }

    private String receiveData() {
        String line = "";
        try {
            packet = new DatagramPacket(buffer, buffer.length);
            socket.receive(packet);
            line = new String(packet.getData(), 0, packet.getLength());
        } catch (IOException ex) {
            log("receiveData : " + ex.getMessage());
        }
        return line;
    }
      
    private void log(String message){
        System.out.println(message);
    }
    
    private void joinGroup(){
        try {
            socket.joinGroup(ip);
            log("Client Running...");
        } catch (IOException ex) {
            log("joinGroup : " + ex.getMessage());
        }
    }
}

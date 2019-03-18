/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package udpserverclient;
import java.net.*;
import java.io.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Ghadeer
 */
public class Client {
    Scanner scan = null;
    DatagramSocket socket = null;
    byte[] buffer = null;
    
    public static void  main(String[] args){
       Client client = new Client();
       client.initializeVarible();
       client.connecting();
    }
    
    private void connecting(){
        while(true){
            String line = readFromKeyboard();
            send(line);
            
            if(line.equals(Constants.STOP)){
                break;
            }
        }
    }
    private void send(String message){                
        try {
            InetAddress ip = InetAddress.getLocalHost();
        
            buffer = message.getBytes();
        
            DatagramPacket packetSend = new DatagramPacket(buffer,buffer.length,
                        ip,Constants.PORT);
            socket.send(packetSend);
        } catch (IOException ex) {
            log("send : " + ex.getMessage());
        }
    }
    private String readFromKeyboard(){
        String line = scan.nextLine();
        return line;
    }
    private void initializeVarible(){        
        try {
            scan = new Scanner(System.in);
            socket = new DatagramSocket();
        } catch (SocketException ex) {
            log("initializeVarible : " + ex.getMessage());
        }        
    }
    private void log(String message){
        System.out.println(message);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package updmulticastserver;

import java.io.*;
import java.net.*;
import java.util.Scanner;
/**
 *
 * @author Ghadeer
 */
public class Server {

    MulticastSocket socket = null;
    byte[] buffer = null;
    DatagramPacket receivePacket = null;
    Scanner scan = null;

    public static void main(String[] args) {
        Server server = new Server();
        server.initializeVarible();
        server.connecting();
    }

    private void initializeVarible() {
        try {
            socket = new MulticastSocket();
            buffer = new byte[Constants.BUFFER_SIZE];
            scan = new Scanner(System.in);
            
            log("Server Running...");
        } catch (SocketException ex) {
            log("initializeVarible : " + ex.getMessage());
        } catch (IOException ex) {
            log("initializeVarible : " + ex.getMessage());
        }
    }

    private void connecting() {
        while (true) {
            String data = readFromKeyboard();            
            send(data);
            
            buffer = new byte[Constants.BUFFER_SIZE];
        }
    }
    
    private String readFromKeyboard(){
        String line = scan.nextLine();
        return line;
    }

    private void send(String message) {
        try {
            InetAddress ip = InetAddress.getByName(Constants.IP);

            buffer = message.getBytes();

            DatagramPacket packetSend = new DatagramPacket(buffer, buffer.length,
                    ip, Constants.PORT);
            socket.send(packetSend);
            log("Message Sent");
        } catch (IOException ex) {
            log("send : " + ex.getMessage());
        }
    }

    private void log(String message) {
        System.out.println(message);
    }
}

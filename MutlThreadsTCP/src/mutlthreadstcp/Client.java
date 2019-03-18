/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mutlthreadstcp;

import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ghadeer
 */
public class Client {

    Scanner scn = new Scanner(System.in);
    Socket socket = null;
    DataInputStream input = null;
    DataOutputStream output;
    InetAddress ip;
    
    public Client(){
        try{
             ip = InetAddress.getByName("localhost");
            socket = new Socket(ip, Constants.PORT);

            // obtaining input and out streams 
            input = new DataInputStream(socket.getInputStream());
            output = new DataOutputStream(socket.getOutputStream());
            
        }catch(UnknownHostException ex){
        }catch(IOException ex){
        }
    }

    public static void main(String[] args) {
        Client client = new Client();
        client.readMessageThread();
        client.writeMessageThread();
    }
    
    private void readMessageThread(){
         // readMessage thread 
            Thread readMessage = new Thread(new Runnable() {
                @Override
                public void run() {

                    while (true) {
                        try {
                            // read the message sent to this client 
                            String msg = input.readUTF();
                            System.out.println(msg);
                        } catch (IOException e) {

                            e.printStackTrace();
                        }
                    }
                }
            });
            readMessage.start();
    }
    
    private void writeMessageThread(){
        // sendMessage thread 
            Thread sendMessage = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {

                        // read the message to deliver. 
                        String msg = scn.nextLine();

                        try {
                            // write on the output stream 
                            output.writeUTF(msg);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            
            sendMessage.start();
    }
}

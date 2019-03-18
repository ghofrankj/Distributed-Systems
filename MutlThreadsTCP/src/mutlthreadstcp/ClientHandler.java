/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mutlthreadstcp;

import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.StringTokenizer;


/**
 *
 * @author Ghadeer
 */
public class ClientHandler implements Runnable {

    final Socket socket;
    final Scanner scan;
    String name;
    boolean isLoggedIn;

    private DataInputStream input;
    private DataOutputStream output;

    public ClientHandler(Socket socket, String name) {
        this.socket = socket;
        scan = new Scanner(System.in);
        this.name = name;
        isLoggedIn = true;
        try {
            input = new DataInputStream(socket.getInputStream());
            output = new DataOutputStream(socket.getOutputStream());

        } catch (IOException ex) {
            log("Constructor : " + ex.getMessage());
        }
    }

    @Override
    public void run() {
        String received;
        write(output,"Your name : " + name);
        while (true) {

            received = read();

            if (received.equalsIgnoreCase(Constants.LOGOUT)) {
                this.isLoggedIn = false;
                closeSocket();
                closeStreams();
                break;
            }

            forwardToClient(received);
        }
        closeStreams();
    }

    private void forwardToClient(String received) {
        StringTokenizer tokenizer = new StringTokenizer(received, "#");
        String recipient = tokenizer.nextToken().trim();

        String message = tokenizer.nextToken().trim();

        for (ClientHandler c : Server.getClients()) {

            if (isLoggedIn && c.name.equals(recipient)) {
                log(name + " --> " + c.name + " : " + message);
                write(c.output,recipient + " : " + message);
                break;
            }
        }
    }
    
    private void closeStreams() {
        try {
            this.input.close();
            this.output.close();
        } catch (IOException ex) {
            log("Run : " + ex.getMessage());
        }
    }

    private String read() {
        String line = "";
        try {
            line = input.readUTF();
        } catch (IOException ex) {
            log("read : " + ex.getMessage());
        }
        return line;
    }

    private void closeSocket(){
        try {
            socket.close();
        } catch (IOException ex) {
            log("closeSocket : " + ex.getMessage());
        }
    }
    private void write(DataOutputStream output, String message) {
        try {
            output.writeUTF(message);
        } catch (IOException ex) {
            log("write : " + ex.getMessage());
        }
    }

    private void log(String message) {
        System.out.println(message);
    }

}

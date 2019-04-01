/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
import server.loginFacade;

/**
 *
 * @author Ghadeer
 */
public class Client {

    public static void main(String[] args) {
        try{
            Registry reg = LocateRegistry.getRegistry("localhost", 1099);
            loginFacade server = (loginFacade) reg.lookup("rmi://localhost/service");
            Scanner scan = new Scanner(System.in);
            
            System.out.println("Enter username : ");
            String username = scan.nextLine();
            System.out.println("Enter password : ");
            String password = scan.nextLine();
            
            String response = server.login(username, password);
            System.out.println("Message From Server : " + response);
        }catch(RemoteException | NotBoundException ex){
            System.out.println(ex);
        }
    }    
}

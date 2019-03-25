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
import server.IHello;

/**
 *
 * @author Ghadeer
 */
public class Client {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try{
            Registry reg = LocateRegistry.getRegistry("localhost", 1099);
            IHello server = (IHello) reg.lookup("rmi://localhost/service");
            System.out.println("Bound to: " + server);
            
            Scanner scan = new Scanner(System.in);
            System.out.print("Enter ur name: ");
            System.out.println(server.sayHello(scan.nextLine()));
            
        }catch(RemoteException | NotBoundException ex){
            System.out.println(ex);
        }
    }
    
}

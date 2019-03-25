/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Ghadeer
 */
public class HelloServer extends UnicastRemoteObject implements IHello{
    
    public HelloServer() throws RemoteException{
        super();
    }
    @Override
    public String sayHello(String name) throws RemoteException {
       return "Hello : " + name;
    }
    
    public static void main(String[] args){
        try{
            Registry reg = LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
            HelloServer obj = new HelloServer();
            reg.rebind("rmi://localhost/service", obj);
            
             System.out.println("HelloServer bound in registry!");
             
        }catch(RemoteException e){
            System.out.println(e);
        }
    }
}

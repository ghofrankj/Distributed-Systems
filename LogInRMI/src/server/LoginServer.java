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
import java.util.*;


/**
 *
 * @author Ghadeer
 */
public class LoginServer extends UnicastRemoteObject implements loginFacade{
    
   private TreeMap clients = new TreeMap<String,String>();
    
    public LoginServer() throws RemoteException{
        super();
     
    }

    @Override
    public String login(String username, String password) throws RemoteException {
        Init();
        String response = search(username, password);
           
                    
      return response;
        
    }

    private void Init() {
       clients.put("user1", "1234");
       clients.put("user2","1234");
    }
    
    private String search(String username,String password){
        String response ="";
        Set set = clients.entrySet();
        Iterator it = set.iterator();
        boolean flag = false;
       while(it.hasNext()){
           response ="";           
           Map.Entry me = (Map.Entry) it.next();
           String user = me.getKey().toString();
           String pass = me.getValue().toString();
           if(username.equalsIgnoreCase(user)){
               flag = true;               
               if(password.equalsIgnoreCase(pass)){
                   response ="LOGIN_SUCCESFUL";
               }else{
                   response = "PASSWORD_INCORRECT";
               }
               break;
           }
       }
       if(!flag){
           response = "USER_NOT_EXISTS";
       }       
       return response;
    }
    
    public static void main(String[] args){
        try{
            Registry reg = LocateRegistry.createRegistry(Registry.REGISTRY_PORT);	
            LoginServer obj = new LoginServer();

            reg.rebind("rmi://localhost/service", obj);

            System.out.println("Server Running...");
        }catch(RemoteException ex){
            System.out.println(ex);
        }        
    }    
}

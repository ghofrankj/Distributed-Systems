/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package server;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Ghadeer
 */
public interface loginFacade extends Remote{
    public String login(String username, String password) throws RemoteException;
}

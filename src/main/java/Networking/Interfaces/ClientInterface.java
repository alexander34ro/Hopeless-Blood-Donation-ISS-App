package Networking.Interfaces;

import Utils.LogException;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientInterface extends Remote {

    Object login(String username, String password) throws LogException, RemoteException;

}

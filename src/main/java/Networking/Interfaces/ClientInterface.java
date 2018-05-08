package Networking.Interfaces;

import Persistence.IUser;
import Utils.LogException;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientInterface extends Remote {

    IUser login(String username, String password) throws LogException, RemoteException;

}

package Networking.Interfaces;

import Networking.NetworkException;
import Persistence.IUser;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientInterface extends Remote {

    IUser login(String username, String password) throws NetworkException, RemoteException;

    void logout() throws NetworkException, RemoteException;

}

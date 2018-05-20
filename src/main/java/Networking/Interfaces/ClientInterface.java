package Networking.Interfaces;

import Networking.NetworkException;
import Persistence.IUser;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ClientInterface extends Remote {
    IUser login(String username, String password) throws NetworkException, RemoteException;
    void logout() throws NetworkException, RemoteException;
    void signUp(IUser user) throws NetworkException, RemoteException;

    <T> List<T> getAll(Class className) throws NetworkException, RemoteException;
    <T> void saveOrUpdate(final T o) throws NetworkException, RemoteException;

    void donationAdded() throws NetworkException, RemoteException;
}

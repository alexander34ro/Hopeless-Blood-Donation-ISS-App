package Networking.Interfaces;

import Networking.NetworkException;
import Persistence.IUser;

import java.rmi.RemoteException;
import java.util.List;

public interface ServerInterface {

    IUser login(String username, String password, ClientInterface client) throws NetworkException;
    void logout(ClientInterface client) throws NetworkException;
    void signuUp(IUser user) throws NetworkException;
    <T> List<T> getAll(Class className) throws NetworkException;
    <T> void saveOrUpdate(final T o) throws NetworkException, RemoteException;
    //void logout(IUser user) throws LogException;
}

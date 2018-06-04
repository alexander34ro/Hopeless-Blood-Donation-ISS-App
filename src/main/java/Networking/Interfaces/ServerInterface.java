package Networking.Interfaces;

import Networking.NetworkException;
import Persistence.DonatorEntity;
import Persistence.IUser;

import java.rmi.RemoteException;
import java.util.List;

public interface ServerInterface {

    IUser login(String username, String password, ClientInterface client) throws NetworkException;
    void logout(ClientInterface client) throws NetworkException;
    void signuUp(IUser user) throws NetworkException;
    <T> List<T> getAll(Class className) throws NetworkException, RemoteException;
    <T> void saveOrUpdate(final T o) throws NetworkException, RemoteException;
    <T> void delete(final T o) throws NetworkException,RemoteException;
    //void logout(IUser user) throws LogException;
    void sendNotification(DonatorEntity donatorEntity, String notificationContent) throws NetworkException, RemoteException;
}

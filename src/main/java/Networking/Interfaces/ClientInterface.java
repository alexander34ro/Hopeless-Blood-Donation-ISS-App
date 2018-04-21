package Networking.Interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientInterface extends Remote {
    String modifyText(String text) throws RemoteException;
}

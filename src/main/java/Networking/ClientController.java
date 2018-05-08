package Networking;

import Networking.Interfaces.ClientInterface;
import Networking.Interfaces.ServerInterface;
import Persistence.IUser;
import Utils.LogException;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ClientController extends UnicastRemoteObject implements ClientInterface, java.io.Serializable {

    private ServerInterface server;


    public ClientController(ServerInterface server) throws RemoteException {
        this.server = server;
    }


    @Override
    public IUser login(String username, String password) throws LogException, RemoteException {
        return this.server.login(username, password, this);
    }
}

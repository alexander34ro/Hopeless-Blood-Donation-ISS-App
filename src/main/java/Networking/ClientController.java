package Networking;

import Networking.Interfaces.ClientInterface;
import Networking.Interfaces.ServerInterface;
import Persistence.IUser;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ClientController extends UnicastRemoteObject implements ClientInterface, java.io.Serializable {

    private ServerInterface server;

    public ClientController(ServerInterface server) throws RemoteException {
        this.server = server;
    }

    @Override
    public IUser login(String username, String password) throws NetworkException, RemoteException {
        try {
            return this.server.login(username, password, this);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void logout() throws RemoteException {
        try {
            this.server.logout(this);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}

package Networking;

import Networking.Interfaces.ClientInterface;
import Networking.Interfaces.ServerInterface;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ClientController extends UnicastRemoteObject implements ClientInterface, java.io.Serializable {

    private ServerInterface server;


    public ClientController(ServerInterface server) throws RemoteException {
        this.server = server;
    }

    @Override
    public String modifyText(String text) {
        return server.removeLastCharacter(text) + " mere";
    }
}

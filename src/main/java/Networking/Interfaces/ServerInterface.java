package Networking.Interfaces;

import Networking.NetworkException;
import Persistence.IUser;
import Utils.LogException;

public interface ServerInterface {

    IUser login(String username, String password, ClientInterface client) throws NetworkException, LogException;
    void logout(ClientInterface client) throws NetworkException, LogException;

    //void logout(IUser user) throws LogException;

}

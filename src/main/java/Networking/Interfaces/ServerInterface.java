package Networking.Interfaces;

import Persistence.IUser;
import Utils.LogException;

public interface ServerInterface {

    IUser login(String username, String password, ClientInterface client) throws LogException;
    //void logout(IUser user) throws LogException;

}

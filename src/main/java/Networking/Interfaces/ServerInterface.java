package Networking.Interfaces;

import Utils.LogException;

public interface ServerInterface {

    Object login(String username, String password,  ClientInterface client) throws LogException;
    //void logout(IUser user) throws LogException;

}

package Servers;

import Controllers.IUserController;
import Models.IUser;
import Utils.LogException;

public interface IServer {

    void login(IUser user, IUserController controller) throws LogException;
    void logout(IUser user,IUserController controller) throws LogException;
}

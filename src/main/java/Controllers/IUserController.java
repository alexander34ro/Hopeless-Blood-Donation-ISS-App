package Controllers;

import Servers.IServer;

public interface IUserController<E> {


     void setUser(E user);

     void setServer(IServer server);
}

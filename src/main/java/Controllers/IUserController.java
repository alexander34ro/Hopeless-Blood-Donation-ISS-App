package Controllers;

import Networking.Interfaces.ClientInterface;

public interface IUserController<E> {
     void setUser(E user);

     void setClient(ClientInterface client);
}

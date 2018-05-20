package Controllers;

import Networking.Interfaces.ClientInterface;

public interface IUserController<E> {
     void setUser(E user);

     //void setService(DumbService service);
     void setClient(ClientInterface client);
}

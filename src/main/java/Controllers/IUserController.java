package Controllers;

import Networking.Interfaces.ClientInterface;
import Services.DumbService;

public interface IUserController<E> {
     void setUser(E user);

     void setService(DumbService service);
     void setClient(ClientInterface client);
}

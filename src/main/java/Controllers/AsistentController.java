package Controllers;

import Networking.Interfaces.ClientInterface;
import Persistence.AsistentEntity;

public class AsistentController implements IUserController<AsistentEntity>{

    private AsistentEntity user;
    private ClientInterface client;

    public void setUser(AsistentEntity user){
        this.user = user;
    }

    public void setClient(ClientInterface client){
        this.client = client;
    }
}

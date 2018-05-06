package Controllers;

import Networking.Interfaces.ClientInterface;
import Persistence.AsistentEntity;
import Services.DumbService;

public class AsistentController implements IUserController<AsistentEntity>{

    DumbService service;
    private AsistentEntity user;
    private ClientInterface client;

    public void setService(DumbService service){this.service=service;}
    public void setUser(AsistentEntity user){
        this.user = user;
    }

    public void setClient(ClientInterface client){
        this.client = client;
    }
}

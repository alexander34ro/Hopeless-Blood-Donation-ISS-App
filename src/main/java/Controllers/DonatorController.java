package Controllers;

import Networking.Interfaces.ClientInterface;
import Persistence.DonatorEntity;

public class DonatorController implements IUserController<DonatorEntity> {
    private DonatorEntity user;
    private ClientInterface client;
    public DonatorController(){}
    public void setUser(DonatorEntity user){
        this.user = user;
    }

    public void setClient(ClientInterface client){
        this.client = client;
    }
}

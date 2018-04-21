package Controllers;

import Networking.Interfaces.ClientInterface;
import Persistence.MedicEntity;

public class MedicController implements IUserController<MedicEntity>{

    private MedicEntity user;
    private ClientInterface client;

    public void setUser(MedicEntity user){
        this.user = user;

    }

    public void setClient(ClientInterface client){
        this.client = client;
    }
}

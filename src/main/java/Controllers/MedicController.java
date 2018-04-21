package Controllers;

import Persistence.MedicEntity;
import Servers.IServer;

public class MedicController implements IUserController<MedicEntity>{

    private MedicEntity user;
    private IServer server;

    public void setUser(MedicEntity user){
        this.user = user;

    }

    public void setServer(IServer server){
        this.server=server;
    }
}

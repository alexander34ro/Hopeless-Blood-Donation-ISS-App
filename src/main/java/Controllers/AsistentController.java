package Controllers;

import Persistence.AsistentEntity;
import Servers.IServer;

public class AsistentController implements IUserController<AsistentEntity>{

    private AsistentEntity user;
    private IServer server;

    public void setUser(AsistentEntity user){
        this.user = user;

    }

    public void setServer(IServer server){
        this.server=server;
    }
}

package Controllers;

import Persistence.DonatorEntity;
import Servers.IServer;

public class DonatorController implements IUserController<DonatorEntity> {
    private DonatorEntity user;
    private IServer server;
    public DonatorController(){}
    public void setUser(DonatorEntity user){
        this.user = user;
    }

    public void setServer(IServer server){
        this.server=server;
    }
}

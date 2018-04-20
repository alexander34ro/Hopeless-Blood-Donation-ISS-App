package Controllers;

import Models.Medic;
import Servers.IServer;

public class MedicController implements IUserController<Medic>{

    private Medic user;
    private IServer server;

    public void setUser(Medic user){
        this.user=user;

    }

    public void setServer(IServer server){
        this.server=server;
    }
}

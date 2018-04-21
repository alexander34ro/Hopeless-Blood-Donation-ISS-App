package Controllers;

import Models.Asistent;
import Servers.IServer;

public class AsistentController implements IUserController<Asistent>{

    private Asistent user;
    private IServer server;

    public void setUser(Asistent user){
        this.user=user;

    }

    public void setServer(IServer server){
        this.server=server;
    }
}

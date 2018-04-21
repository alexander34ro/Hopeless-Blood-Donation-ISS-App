package Controllers;

import Models.Donator;
import Servers.IServer;

public class DonatorController implements IUserController<Donator> {
    private Donator user;
    private IServer server;
    public DonatorController(){}
    public void setUser(Donator user){
        this.user=user;

    }

    public void setServer(IServer server){
        this.server=server;
    }
}

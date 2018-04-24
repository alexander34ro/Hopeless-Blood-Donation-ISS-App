package Networking;


import Models.IUser;
import Networking.Interfaces.ClientInterface;
import Networking.Interfaces.ServerInterface;
import Persistence.AsistentEntity;
import Utils.LogException;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ServerImplementation implements ServerInterface {

    private Map<Short, ClientInterface> loggedInClients = new ConcurrentHashMap<>();

    public ServerImplementation() {
    }


    @Override
    public synchronized Object login(String username, String password, ClientInterface client) throws LogException {

        // user / password ok
        AsistentEntity user = new AsistentEntity(); // result

        /*if( this.loggedInClients.containsKey( user.getId() ) ) {
            throw new LogException("User deja autentificat");
        }*/

        this.loggedInClients.put(user.getId(), client);

        return user;

    }

    @Override
    public void logout(IUser user) throws LogException {

        if( ! this.loggedInClients.containsKey( user.getId() ) ) {
            throw new LogException("Userul nu este autentificat");
        }

        this.loggedInClients.remove(user.getId());

    }
}

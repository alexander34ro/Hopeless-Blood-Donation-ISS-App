package Networking;

import Networking.Interfaces.ClientInterface;
import Networking.Interfaces.ServerInterface;
import Persistence.AsistentEntity;
import Persistence.DonatorEntity;
import Persistence.IUser;
import Persistence.MedicEntity;
import Services.DumbService;
import Utils.LogException;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ServerImplementation implements ServerInterface {

    private Map<Short, ClientInterface> loggedInClients = new ConcurrentHashMap<>();

    private DumbService dumbService = new DumbService();

    public ServerImplementation() {
    }


    @Override
    public IUser login(String username, String password, ClientInterface client) throws LogException {

        // user / password ok
        //AsistentEntity user = new AsistentEntity(); // result

        /*if( this.loggedInClients.containsKey( user.getId() ) ) {
            throw new LogException("User deja autentificat");
        }*/

        // this.loggedInClients.put(user.getId(), client);

        //return user;

        Class[] classes = new Class[]{DonatorEntity.class, AsistentEntity.class, MedicEntity.class};

        System.out.println(dumbService.getAll(DonatorEntity.class).size());

        for(Class c : classes) {
            for(Object obj : dumbService.getAll(c)) {
                if(obj instanceof IUser) {
                    IUser user = (IUser) obj;
                    System.out.println("~~" + user.getUsername() + " - " + user.getParola());
                    if(username.equals(user.getUsername()) && password.equals(user.getParola())) {
                        if( ! this.loggedInClients.containsKey(user.getId())) {
                            this.loggedInClients.put(user.getId(), client);
                            return user;
                        }
                    }
                }
            }
        }

        return null;
    }

    @Override
    public void logout(ClientInterface client) throws LogException {
        this.loggedInClients.values().remove( client );
    }

    /*
    @Override
    public void logout(IUser user) throws LogException {

        if( ! this.loggedInClients.containsKey( user.getId() ) ) {
            throw new LogException("Userul nu este autentificat");
        }

        this.loggedInClients.remove(user.getId());

    }
    */
}

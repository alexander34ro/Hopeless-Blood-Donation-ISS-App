package Networking;

import Networking.Interfaces.ClientInterface;
import Networking.Interfaces.ServerInterface;
import Persistence.AsistentEntity;
import Persistence.DonatorEntity;
import Persistence.IUser;
import Persistence.MedicEntity;
import Services.DumbService;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ServerImplementation implements ServerInterface {

    private Map<Short, ClientInterface> loggedInClients = new ConcurrentHashMap<>();

    private DumbService dumbService = new DumbService();

    public ServerImplementation() {
    }


    @Override
    public IUser login(String username, String password, ClientInterface client) {

        // user / password ok
        //AsistentEntity user = new AsistentEntity(); // result

        /*if( this.loggedInClients.containsKey( user.getId() ) ) {
            throw new LogException("User deja autentificat");
        }*/

        // this.loggedInClients.put(user.getId(), client);

        //return user;

        Class[] classes = new Class[]{DonatorEntity.class, AsistentEntity.class, MedicEntity.class};

        System.out.println(dumbService.getAll(DonatorEntity.class).size());
        System.out.println(dumbService.getAll(AsistentEntity.class).size());
        System.out.println(dumbService.getAll(MedicEntity.class).size());

        for(Class c : classes) {
            for(Object obj : dumbService.getAll(c)) {
                if(obj instanceof IUser) {
                    IUser user = (IUser) obj;
                    System.out.println("~~" + user.getUsername() + " - " + user.getParola());
                    if(username.equals(user.getUsername()) && password.equals(user.getParola())) {
                        System.out.println("Logged in");
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
    public void logout(ClientInterface client) {
        this.loggedInClients.values().remove( client );
    }

    @Override
    public void signuUp(IUser user) throws NetworkException {
        Class cl = null;

        if(user instanceof DonatorEntity)
            cl = DonatorEntity.class;
        else if(user instanceof AsistentEntity)
            cl = AsistentEntity.class;
        else if(user instanceof MedicEntity)
            cl = MedicEntity.class;

        if(cl != null) {
            dumbService.saveOrUpdate(user);
        }
    }

    public <T> List<T> getAll(Class className) throws NetworkException {
        return dumbService.getAll(className);
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

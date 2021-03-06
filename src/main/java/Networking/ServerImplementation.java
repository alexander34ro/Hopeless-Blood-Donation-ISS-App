package Networking;

import Networking.Interfaces.ClientInterface;
import Networking.Interfaces.ServerInterface;
import Persistence.*;
import Services.DumbService;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class ServerImplementation implements ServerInterface {

    private Map<Short, ClientInterface> loggedInClients = new ConcurrentHashMap<>();

    private DumbService dumbService = new DumbService();

    public ServerImplementation() {
    }


    @Override
    public IUser login(String username, String password, ClientInterface client) {
        for(Class c : new Class[]{DonatorEntity.class, AsistentEntity.class, MedicEntity.class}) {
            for(Object obj : dumbService.getAll(c)) {
                if(obj instanceof IUser) {
                    IUser user = (IUser) obj;
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

    @Override
    public <T> void saveOrUpdate(final T o) throws NetworkException, RemoteException {
        dumbService.saveOrUpdate(o);

        if(o instanceof DonatieEntity) {
            for(ClientInterface clientInterface : loggedInClients.values()) {
                clientInterface.donationAddedOrUpdated();
            }
        }
        else if(o instanceof NotificareEntity) {
            for(ClientInterface clientInterface : loggedInClients.values()) {
                clientInterface.notificationsUpdated();
            }
        }
    }

    @Override
    public <T> void delete(final T o) throws NetworkException,RemoteException{
        dumbService.delete(o);
    }

    @Override
    public void sendNotification(DonatorEntity donatorEntity, String notificationContent) throws NetworkException, RemoteException {
        NotificareEntity notificareEntity = new NotificareEntity();

        short id = 1;

        List<NotificareEntity> notificareEntityList = this.getAll(NotificareEntity.class);

        if(notificareEntityList.size() > 0) {
            id = (short) (this.getAll( NotificareEntity.class ).stream().mapToInt( notificare -> (int) ((NotificareEntity) notificare).getId() ).reduce((A,B) -> A > B ? A : B).getAsInt() + 1);
        }

        notificareEntity.setId( id );
        notificareEntity.setDonatorByDonator(donatorEntity);
        notificareEntity.setMesaj(notificationContent);
        this.saveOrUpdate(notificareEntity);
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

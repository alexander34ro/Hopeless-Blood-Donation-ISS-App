package Networking;

import Controllers.AsistentController;
import Controllers.DonatorController;
import Controllers.MedicController;
import Networking.Interfaces.ClientInterface;
import Networking.Interfaces.ServerInterface;
import Persistence.DonatorEntity;
import Persistence.IUser;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class ClientController extends UnicastRemoteObject implements ClientInterface, java.io.Serializable {

    private ServerInterface server;

    private AsistentController asistentController = null;
    private DonatorController donatorController = null;
    private MedicController medicController = null;

    public ClientController(ServerInterface server) throws RemoteException {
        this.server = server;
    }

    @Override
    public IUser login(String username, String password) throws NetworkException, RemoteException {
        try {
            return this.server.login(username, password, this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void logout() throws RemoteException {
        try {
            this.server.logout(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void signUp(IUser user) throws NetworkException, RemoteException {
        this.server.signuUp(user);
    }

    public <T> List<T> getAll(Class className) throws NetworkException, RemoteException {
        return server.getAll(className);
    }

    @Override
    public <T> void saveOrUpdate(final T o) throws NetworkException, RemoteException {
        server.saveOrUpdate(o);
    }

    @Override
    public <T> void delete(final T o) throws NetworkException, RemoteException {
        server.delete(o);
    }

    @Override
    public void donationAddedOrUpdated() {
        try {
            if (this.asistentController != null) {
                this.asistentController.updateDonatii();
            } else if (this.donatorController != null) {
                donatorController.updateTable();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void notificationsUpdated() throws NetworkException, RemoteException {
        if(this.donatorController != null) {
            this.donatorController.updateNotificationsList();
        }
    }

    @Override
    public void sendNotification(DonatorEntity donatorEntity, String notificationContent) throws NetworkException, RemoteException {
        this.server.sendNotification(donatorEntity, notificationContent);
    }

    public void setAsistentController(AsistentController asistentController) {
        this.asistentController = asistentController;
    }

    public void setDonatorController(DonatorController donatorController) {
        this.donatorController = donatorController;
    }

    public void setMedicController(MedicController medicController) {
        this.medicController = medicController;
    }
}

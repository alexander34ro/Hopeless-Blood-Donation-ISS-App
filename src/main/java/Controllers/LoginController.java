package Controllers;

import Networking.Interfaces.ClientInterface;
import Networking.NetworkException;
import Persistence.AsistentEntity;
import Persistence.DonatorEntity;
import Persistence.IUser;
import Persistence.MedicEntity;
import Utils.MessageAllert;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class LoginController {
    @FXML private TextField userText;
    @FXML private PasswordField passwordField;

    private ClientInterface client;

    public void setClient(ClientInterface client){
        this.client = client;
    }

    @FXML
    public void handleLogin(ActionEvent actionEvent) {
        String nume = userText.getText();
        String password = passwordField.getText();
        // apel catre proxy
        // primim raspuns un donator, un asistent sau un medic

        // Object response = new AsistentEntity(); //hardcodat

        try {

            IUser response = this.client.login(nume, password);

            if(response == null) {
                MessageAllert.showErrorMessage(null, "Eroare la autentificare");
                return;
            }

            FXMLLoader aloader = null;
            if (response instanceof DonatorEntity) {
                System.out.println("Log in donator");
                aloader = new FXMLLoader(getClass().getClassLoader().getResource("./Views/Donator.fxml"));
            }
            else if (response instanceof AsistentEntity) {
                System.out.println("Log in asistent");
                aloader = new FXMLLoader(getClass().getClassLoader().getResource("./Views/Asistent.fxml"));
            }
            else if (response instanceof MedicEntity) {
                System.out.println("Log in medic");
                aloader = new FXMLLoader(getClass().getClassLoader().getResource("./Views/Medic.fxml"));
            }
            Parent aroot = aloader.load();

            IUserController controller = aloader.getController();
            controller.setUser(response);
            controller.setClient(client);

            //controller.setUser(response);
            Stage stage = new Stage();
            stage.setTitle("Donation Status");
            stage.setScene(new Scene(aroot));

            stage.setOnCloseRequest((WindowEvent we) -> {
                try {
                    this.client.logout();
                    Platform.exit();
                    System.exit(0);
                }
                catch(Exception e) {
                    e.printStackTrace();
                }
            });

            stage.show();

            ((Node)(actionEvent.getSource())).getScene().getWindow().hide();

        }
        catch(IOException e){
            e.printStackTrace();
        }
        catch(NetworkException e) {
            MessageAllert.showErrorMessage(null, e.getMessage());
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleCreareCont(){
        try{

            FXMLLoader aloader=new FXMLLoader(getClass().getClassLoader().getResource("./Views/Signup.fxml"));
            Parent aroot=aloader.load();
            SignupController controller = aloader.getController();
            controller.setClient(client);
            Stage stage=new Stage();
            stage.setTitle("Creare Cont");
            stage.setScene(new Scene(aroot));
            stage.showAndWait();
        }

        catch(IOException e){
            e.printStackTrace();
        }
    }
}

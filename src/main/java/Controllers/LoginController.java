package Controllers;

import Persistence.AsistentEntity;
import Persistence.DonatorEntity;
import Persistence.MedicEntity;
import Servers.IServer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    @FXML private TextField userText;
    @FXML private PasswordField passwordField;
    private IServer server;

    @FXML

    public void handleLogin(ActionEvent actionEvent) {
        String nume = userText.getText();
        String password = passwordField.getText();
        // apel catre proxy
        // primim raspuns un donator, un asistent sau un medic
        Object response = new AsistentEntity(); //hardcodat

        try {
            FXMLLoader aloader = null;
            if (response instanceof DonatorEntity)
                aloader = new FXMLLoader(getClass().getClassLoader().getResource("./Views/Donator.fxml"));
            else if (response instanceof AsistentEntity)
                aloader = new FXMLLoader(getClass().getClassLoader().getResource("./Views/Asistent.fxml"));
            else if (response instanceof MedicEntity)
                aloader = new FXMLLoader(getClass().getClassLoader().getResource("./Views/Medic.fxml"));
            Parent aroot = aloader.load();

            IUserController controller = aloader.getController();
            controller.setServer(server);
            //controller.setUser(response);
            Stage stage = new Stage();
            stage.setTitle("Donation Status");
            stage.setScene(new Scene(aroot));
            stage.show();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    public void handleCreareCont(){
        try{

            FXMLLoader aloader=new FXMLLoader(getClass().getClassLoader().getResource("./Views/Signup.fxml"));
            Parent aroot=aloader.load();
            Stage stage=new Stage();
            stage.setTitle("Creare Cont");
            stage.setScene(new Scene(aroot));
            stage.show();
        }

        catch(IOException e){
            e.printStackTrace();
        }
    }
}

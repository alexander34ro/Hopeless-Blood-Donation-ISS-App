package Controllers;

import Models.Asistent;
import Models.Donator;
import Models.IUser;
import Models.Medic;
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
        String nume=userText.getText();
        String password=passwordField.getText();
        IUser user=new Asistent(); //hardcodat
        user.setUsername(nume);
        user.setParola(password);

        try {
                FXMLLoader aloader=null;
                if(user instanceof Donator)
                    aloader=new FXMLLoader(getClass().getClassLoader().getResource("./Views/fereastraDonator.fxml"));
                else if(user instanceof Asistent)
                    aloader=new FXMLLoader(getClass().getClassLoader().getResource("./Views/Asistent.fxml"));
                else if (user instanceof Medic)
                    aloader=new FXMLLoader(getClass().getClassLoader().getResource("./Views/medic1.fxml"));
                Parent aroot=aloader.load();

                IUserController controller=aloader.getController();
                controller.setServer(server);
                controller.setUser(user);
                //server.login(user, controller); nu e implementat
                Stage stage=new Stage();
                stage.setTitle("Donation Status");
                stage.setScene(new Scene(aroot));
                stage.show();
            }
        //catch(LogException e){ exceptie de la Server
            //MessageAllert.showErrorMessage(null,e.getMessage());
      //  }
            catch(IOException e){
                e.printStackTrace();
            }

    }

    @FXML
    public void handleCreareCont(){
        try{

            FXMLLoader aloader=new FXMLLoader(getClass().getClassLoader().getResource("./Views/fereastra.fxml"));
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

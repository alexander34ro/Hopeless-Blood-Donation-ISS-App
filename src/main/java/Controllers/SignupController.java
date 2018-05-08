package Controllers;

import Networking.Interfaces.ClientInterface;
import Services.DumbService;
import javafx.fxml.FXML;

public class SignupController {
    DumbService service;
    private ClientInterface client;

    public void  setService(DumbService service){this.service=service;}
    public void setClient(ClientInterface client){
        this.client = client;
    }

    @FXML
    private void initialize() {

    }
}

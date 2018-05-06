package Controllers;

import Networking.Interfaces.ClientInterface;
import Persistence.MedicEntity;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MedicController implements IUserController<MedicEntity>{


    private MedicEntity user;
    private ClientInterface client;

    public void setUser(MedicEntity user){
        this.user = user;

    }
    @FXML
    private Label labelNume;
    @FXML
    private Label labelSpital;



    @FXML
    public void initialize() {

        labelNume.setText(user.getNume());
        labelSpital.setText(user.getNumeSpital());

    }

    public void setClient(ClientInterface client){
        this.client = client;
    }
}

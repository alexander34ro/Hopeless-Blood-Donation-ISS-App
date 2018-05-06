package Controllers;

import Networking.Interfaces.ClientInterface;
import Persistence.DonatieEntity;
import Persistence.DonatorEntity;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class DonatorController implements IUserController<DonatorEntity> {
    private DonatorEntity user;
    private ClientInterface client;
    public DonatorController(){}
    public void setUser(DonatorEntity user){
        this.user = user;
    }

    public void setClient(ClientInterface client){
        this.client = client;
    }

    DonatieEntity donatieEntity=new DonatieEntity();
    @FXML
    private TextField textFieldGreutate;
    @FXML
    private TextField textFieldPuls;
    @FXML
    private TextField textFieldTensiune;
    @FXML
    private TextField textFieldGrS;
    @FXML
    private TextField textFieldRh;


}

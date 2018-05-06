package Controllers;

import Persistence.DetaliiCerereEntity;
import Services.DumbService;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class CerereController {
    DumbService service;
    public void setService(DumbService service) {
        this.service=service;
    }


    @FXML
    private ComboBox comboBox3;
    @FXML
    private TextField textFieldUnitati;
    @FXML
    private ComboBox comboBox2;
    @FXML
    private ComboBox comboBox1;

    public void handleTrimitere(){
        DetaliiCerereEntity cerereEntity=new DetaliiCerereEntity();
        cerereEntity.setPrioritate(String.valueOf(comboBox3));
        cerereEntity.setCantitate(Short.parseShort(String.valueOf(textFieldUnitati)));
        cerereEntity.setProdusSange(String.valueOf(comboBox2));
        cerereEntity.setTipSange(String.valueOf(comboBox1));
        service.save(cerereEntity);
    }
    public void handleStergere(){
        DetaliiCerereEntity cerereEntity=new DetaliiCerereEntity();
        cerereEntity.setPrioritate(String.valueOf(comboBox3));
        cerereEntity.setCantitate(Short.parseShort(String.valueOf(textFieldUnitati)));
        cerereEntity.setProdusSange(String.valueOf(comboBox2));
        cerereEntity.setTipSange(String.valueOf(comboBox1));
       service.delete(cerereEntity);
    }
}

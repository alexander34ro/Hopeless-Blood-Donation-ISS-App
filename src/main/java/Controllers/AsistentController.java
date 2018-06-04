package Controllers;

import Models.TipSange;
import Networking.Interfaces.ClientInterface;
import Networking.NetworkException;
import Persistence.*;
import Utils.DumbGeocoder;
import Utils.MessageAllert;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class AsistentController implements IUserController<AsistentEntity>{
    @FXML private TableView<DonatieEntity> tableViewDonatie;
    @FXML private  TableView<DetaliiCerereEntity> tableViewCerere;

    @FXML private TableColumn<DonatieEntity, String> tipSangeTableColumn;
    @FXML private TableColumn<DetaliiCerereEntity,String> spitalTableColumn;
    @FXML private Label labelPlasmaO,labelPlasmaA,labelPlasmaB,labelPlasmaAB,labelGlobuleO,labelGlobuleA,labelGlobuleB,labelGlobuleAB,labelTrombociteA,labelTrombociteB,labelTrombociteAB,labelTrombociteO;

    private AsistentEntity user;
    private ClientInterface client;
    private ObservableList<DetaliiCerereEntity> modelDetaliiCerere = FXCollections.observableArrayList();

    @FXML private Button cerereUrgentaButton;
    @FXML public void initialize() {

        tipSangeTableColumn.setCellValueFactory(p -> {
            if (p.getValue() != null) {
                return new SimpleStringProperty(p.getValue().getDonatorByDonator().getTipSange());
            } else {
                return new SimpleStringProperty("N/A");
            }
        });
        spitalTableColumn.setCellValueFactory(p->{
            if(p.getValue()!=null){
                return new SimpleStringProperty(p.getValue().getCerereByCerere().getSpitalBySpital().getNume());
            }
            else
                return new SimpleStringProperty("N/A");
        });
        tableViewDonatie.setOnMouseClicked( event -> {
            if( event.getClickCount() == 2 ) {
                DonatieEntity selectedDonation = this.tableViewDonatie.getSelectionModel().getSelectedItem();

                final Stage dialog = new Stage();
                dialog.initStyle(StageStyle.UNDECORATED);
                dialog.initModality(Modality.APPLICATION_MODAL);
                HBox hBox = new HBox();
                hBox.setAlignment(Pos.CENTER);

                hBox.setStyle("-fx-padding: 10px;");

                GridPane gridPane = new GridPane();
                hBox.getChildren().add(gridPane);
                gridPane.setHgap(15);
                gridPane.setVgap(15);

                Button doneButton = new Button("OK");
                Button cancelButton = new Button("Anuleaza");

                gridPane.add(new Label("Actualizare donaţie"), 0, 0, 2, 1);

                gridPane.add(new Label("ID"), 0, 1);

                TextField donationCode = new TextField("" + selectedDonation.getId());
                donationCode.setDisable(true);
                gridPane.add(donationCode, 1, 1);


                TextField greutateTextField = new TextField("");
                gridPane.add(new Label("Greutate:"), 0, 2);
                gridPane.add(greutateTextField, 1, 2);

                TextField pulsTextField = new TextField("");
                gridPane.add(new Label("Puls:"), 0, 3);
                gridPane.add(pulsTextField, 1, 3);

                TextField tensiuneTextField = new TextField("");
                gridPane.add(new Label("Tensiune:"), 0, 4);
                gridPane.add(tensiuneTextField, 1, 4);

                ComboBox<String> stadiuChoiceBox = new ComboBox<>();
                stadiuChoiceBox.getItems().addAll("Recoltare", "Pregatirea","Prelevare","Calificare","Distribuire", "Finalizat");
                gridPane.add(new Label("Stadiu:"), 0, 5);
                gridPane.add(stadiuChoiceBox, 1, 5);

                ComboBox<String> bloodTypeChoiceBox = new ComboBox<>();
                String bloodType = "";

                bloodTypeChoiceBox.getItems().add("Necunoscută");
                for(TipSange tipSange : TipSange.values()) {
                    if(selectedDonation.getDonatorByDonator().getTipSange().equals(tipSange.name())) {
                        bloodType = tipSange.name();
                    }
                }

                if( ! bloodType.equals("")) {
                    bloodTypeChoiceBox.getItems().add(bloodType);
                    bloodTypeChoiceBox.setDisable(true);
                }
                else {
                    bloodTypeChoiceBox.getItems().add("Necunoscută");
                    for(TipSange tipSange : TipSange.values()) {
                        bloodTypeChoiceBox.getItems().add(tipSange.toString());
                    }
                    bloodTypeChoiceBox.getSelectionModel().select(0);
                }

                bloodTypeChoiceBox.getSelectionModel().select(0);

                gridPane.add(new Label("Grupa sanguniă:"), 0, 6);
                gridPane.add(bloodTypeChoiceBox, 1, 6);


                gridPane.add(doneButton, 0, 7);
                gridPane.add(cancelButton, 1, 7);

                doneButton.setOnMouseClicked(event12 -> {
                    try {
                        selectedDonation.setGreutate(Short.parseShort(greutateTextField.getText()));
                        selectedDonation.setPuls(Short.parseShort(pulsTextField.getText()));
                        selectedDonation.setTensiune(Short.parseShort(tensiuneTextField.getText()));
                        selectedDonation.setStadiu(stadiuChoiceBox.getSelectionModel().getSelectedItem());

                        if( ! bloodTypeChoiceBox.isDisabled())
                        {
                            DonatorEntity donatorEntity = selectedDonation.getDonatorByDonator();
                            donatorEntity.setTipSange(bloodTypeChoiceBox.getSelectionModel().getSelectedItem());

                            client.saveOrUpdate(donatorEntity);
                        }



                        client.saveOrUpdate(selectedDonation);
                    }
                    catch(Exception exception) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText(exception.getMessage());
                        alert.setHeaderText("Eroare");
                        alert.showAndWait();
                    }
                });

                cancelButton.setOnMouseClicked(event1 -> dialog.close());

                Scene dialogScene = new Scene(hBox, 400, 400);
                dialog.setScene(dialogScene);

                dialog.showAndWait();
            }
        });

    }



    public void setUser(AsistentEntity user){
        this.user = user;
    }

    public void setClient(ClientInterface client){
        this.client = client;
        tableViewDonatie.getItems().clear();
        try {
            /*client.getAll(DonatieEntity.class).forEach(object -> {
                DonatieEntity donatie = (DonatieEntity) object;
                if(donatie.getCentruTransfuziiByCentruTransfuzii().getId() == user.getId()) {
                    tableViewDonatie.getItems().add(donatie);
                }
            });*/

            /*System.out.println("luam donatiile de la server");
            List<DonatieEntity> donatieEntities = client.getAll(DonatieEntity.class);
            System.out.println(donatieEntities.get(0).getDonatorByDonator().getNume());
            System.out.println("punem donatiile");
            modelDonatie.setAll(donatieEntities);
            */
            setLabels();
            this.updateDonatii();
            tableViewCerere.getItems().clear();
            this.updateCereri();

        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void updateDonatii() throws NetworkException, RemoteException {
        tableViewDonatie.getItems().clear();
        tableViewDonatie.setItems(
            FXCollections.observableArrayList(
                client.getAll(DonatieEntity.class)
                .stream()
                .map(obj -> (DonatieEntity) obj)
                .filter(donatieEntity -> donatieEntity.getCentruTransfuziiByCentruTransfuzii().getId() == this.user.getCentruTransfuziiByCentruTransfuzii().getId())
                .collect(Collectors.toList())
            )
        );
    }
    public void updateCereri() throws NetworkException, RemoteException {
        tableViewCerere.getItems().clear();
        tableViewCerere.setItems(
                FXCollections.observableArrayList(
                        client.getAll(DetaliiCerereEntity.class)
                                .stream()
                                .map(obj -> (DetaliiCerereEntity) obj)
                                .collect(Collectors.toList())
                )
        );
    }
    @FXML
    public void handleTrimiteSange(){
        DetaliiCerereEntity cerereEntity = tableViewCerere.getSelectionModel().getSelectedItem();
        if(cerereEntity!=null){
            try{int cantitate=0;
                List<UnitateSanguinaEntity> unitateSanguinaEntities=client.getAll(UnitateSanguinaEntity.class);
                for(UnitateSanguinaEntity unitateSanguinaEntity:unitateSanguinaEntities)
                    if(unitateSanguinaEntity.getTipSange().equals(cerereEntity.getTipSange()) && unitateSanguinaEntity.getCategorie().equals(cerereEntity.getProdusSange()))
                         {
                        cantitate++;
                        if(cantitate<=cerereEntity.getCantitate()){
                        client.delete(unitateSanguinaEntity);}
                        else
                            break;
                    }
                     if(cantitate-cerereEntity.getCantitate()>=0) {
                         client.delete(cerereEntity);
                         MessageAllert.showMessage(null, Alert.AlertType.CONFIRMATION, "Confirmare", "Sangele a fost trimis");
                         setLabels();
                         updateCereri();
                     }
                     else MessageAllert.showErrorMessage(null,"Nu exista suficient sange");
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        else{
            MessageAllert.showErrorMessage(null,"Trebuie selectata o cerere");
        }
    }
    @FXML
    public void handleCerereUrgenta(){
        DetaliiCerereEntity cerereEntity = tableViewCerere.getSelectionModel().getSelectedItem();
        if(cerereEntity!=null) {

            try {

                List<DonatorEntity> donatori = client.getAll(DonatorEntity.class);
                List<CentruTransfuziiEntity> centru = client.getAll(CentruTransfuziiEntity.class);
                String oras = centru.get(0).getOras();
                double distanta=-1;
                DonatorEntity donatorPerfect = donatori.get(0);
                for (DonatorEntity donator : donatori) {
                    if (donator.getTipSange().equals(cerereEntity.getTipSange()))
                        if (distanta == -1 || distanta > DumbGeocoder.getDistanceBetween(donator.getOras(), oras)) {
                            distanta = DumbGeocoder.getDistanceBetween(donator.getOras(), oras);
                            donatorPerfect = donator;

                        }
                }


                MessageAllert.showMessage(null, Alert.AlertType.CONFIRMATION, "Cerere Urgenta", "Cererea a fost trimisa lui:" + donatorPerfect.getNume());
                //aici trebuie trimisa notificarea

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else MessageAllert.showErrorMessage(null,"NU s-a selectat cerere");
    }
    private void setLabels(){
        int[] valori = new int[12];
        try {
            List<UnitateSanguinaEntity> lista = client.getAll(UnitateSanguinaEntity.class);

            labelTrombociteO.setText("size"+lista.size());
            for (UnitateSanguinaEntity entity : lista) {
                switch (entity.getTipSange()) {
                    case "OPozitiv":
                    case "ONegativ":
                        switch (entity.getCategorie()) {
                            case "Plasma":
                                valori[0]++;
                                break;
                            case "GlobuleRosii":
                                valori[1]++;
                                break;
                            case "Trombocite":
                                valori[2]++;
                                break;
                        }
                        break;
                    case "APozitiv":
                    case "ANegativ":
                        switch (entity.getCategorie()) {
                            case "Plasma":
                                valori[3]++;
                                break;
                            case "GlobuleRosii":
                                valori[4]++;
                                break;
                            case "Trombocite":
                                valori[5]++;
                                break;
                        }
                        break;
                    case "BPozitiv":
                    case "BNegativ":
                        switch (entity.getCategorie()) {
                            case "Plasma":
                                valori[6]++;
                                break;
                            case "GlobuleRosii":
                                valori[7]++;
                                break;
                            case "Trombocite":
                                valori[8]++;
                                break;
                        }
                        break;
                    case "ABPozitiv":
                    case "ABNegativ":
                        switch (entity.getCategorie()) {
                            case "Plasma":
                                valori[9]++;
                                break;
                            case "GlobuleRosii":
                                valori[10]++;
                                break;
                            case "Trombocite":
                                valori[11]++;
                                break;
                        }
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        labelPlasmaO.setText(String.valueOf(valori[0]));
        labelGlobuleO.setText(String.valueOf(valori[1]));
        labelTrombociteO.setText(String.valueOf(valori[2]));
        labelPlasmaA.setText(String.valueOf(valori[3]));
        labelGlobuleA.setText(String.valueOf(valori[4]));
        labelTrombociteA.setText(String.valueOf(valori[5]));
        labelPlasmaB.setText(String.valueOf(valori[6]));
        labelGlobuleB.setText(String.valueOf(valori[7]));
        labelTrombociteB.setText(String.valueOf(valori[8]));
        labelPlasmaAB.setText(String.valueOf(valori[9]));
        labelGlobuleAB.setText(String.valueOf(valori[10]));
        labelTrombociteAB.setText(String.valueOf(valori[11]));


    }

    @FXML public void handleNotificareDonatori(ActionEvent event) throws NetworkException, RemoteException {
        GridPane gridPane = new GridPane();

        List<DonatorEntity> donatorEntities = this.client.getAll(DonatorEntity.class);

        HashMap<Short, CheckBox> checkBoxHashMap = new HashMap<>();

        gridPane.add(new Label("ID"), 0, 0);
        gridPane.add(new Label("Nume"), 1, 0);
        gridPane.add(new Label("Trimite"), 2, 0);

        int currentLine = 1;

        for(DonatorEntity donatorEntity : donatorEntities) {

            CheckBox checkBox = new CheckBox();

            checkBoxHashMap.put(donatorEntity.getId(), checkBox);

            gridPane.add(new Label("" + donatorEntity.getId()), 0, currentLine);
            gridPane.add(new Label("" + donatorEntity.getNume()), 1, currentLine);
            gridPane.add(checkBox, 2, currentLine);
            currentLine++;
        }

        TextArea textArea = new TextArea();
        textArea.setPrefColumnCount(20);
        textArea.setPrefRowCount(3);

        Button trimiteNotificariButton = new Button("Trimite notificari");

        gridPane.add(textArea, 0, currentLine, 2, 1);
        gridPane.add(trimiteNotificariButton, 2, currentLine);

        trimiteNotificariButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                for(DonatorEntity donatorEntity : donatorEntities) {
                    if( checkBoxHashMap.get( donatorEntity.getId() ).isSelected() ) {
                        System.out.println("Trimit notificare la donatorul " + donatorEntity.getId());

                        try {
                            client.sendNotification( donatorEntity, textArea.getText() );
                        } catch (NetworkException | RemoteException e) {
                            e.printStackTrace();
                        }

                    }
                }
            }
        });

        gridPane.setHgap(10);
        gridPane.setVgap(10);

        ScrollPane scrollPane = new ScrollPane(gridPane);

        scrollPane.setMinWidth(400);
        scrollPane.setMinHeight(400);

        scrollPane.setStyle("-fx-padding: 10px;");

        Stage stage = new Stage();
        stage.setScene(new Scene(scrollPane));
        /*stage.setWidth(500);
        stage.setHeight(700);*/
        stage.showAndWait();



    }
}

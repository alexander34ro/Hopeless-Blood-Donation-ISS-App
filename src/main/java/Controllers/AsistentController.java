package Controllers;

import Models.TipSange;
import Networking.Interfaces.ClientInterface;
import Networking.NetworkException;
import Persistence.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.rmi.RemoteException;
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

                gridPane.add(new Label("Grupa sanguniă:"), 0, 5);
                gridPane.add(bloodTypeChoiceBox, 1, 5);


                gridPane.add(doneButton, 0, 6);
                gridPane.add(cancelButton, 1, 6);

                doneButton.setOnMouseClicked(event12 -> {
                    try {
                        selectedDonation.setGreutate(Short.parseShort(greutateTextField.getText()));
                        selectedDonation.setPuls(Short.parseShort(pulsTextField.getText()));
                        selectedDonation.setTensiune(Short.parseShort(tensiuneTextField.getText()));

                        if( ! bloodTypeChoiceBox.isDisabled())
                        {
                            DonatorEntity donatorEntity = selectedDonation.getDonatorByDonator();
                            donatorEntity.setTipSange(bloodTypeChoiceBox.getSelectionModel().getSelectedItem());

                            client.saveOrUpdate(donatorEntity);
                        }

                        selectedDonation.setStadiu("Pregatire");

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

            this.updateDonatii();
            tableViewCerere.getItems().clear();
            this.updateCereri();
            setLabels();
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

    private void setLabels(){
        int[] valori = new int[12];
        try {
            List<UnitateSanguinaEntity> lista = client.getAll(UnitateSanguinaEntity.class);

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
        for(int i=0;i<valori.length;i++)
            System.out.println(valori[i]);
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
}

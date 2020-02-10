package Controllers;

import Queries.SportToolQueries;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SportToolItemController implements Initializable {
    @FXML
    private HBox itemT;

    @FXML
    private TextField FieldName;

    @FXML
    private TextField FieldAmount;

    @FXML
    private TextField FieldURLImage;

    @FXML
    private Label FieldNameLabel;

    @FXML
    private Label FieldAmountLabel;

    @FXML
    private Label FieldURLImageLabel;

    @FXML
    private Button btnDelete;


    @FXML
    private Button btnEdit;

    @FXML
    private Button btnSave;

    @FXML
    private Pane stackEdit;

    @FXML
    private Pane stackNormal;

    @FXML
    private Pane itemT1;
    @FXML
    private Pane itemT2;


    @FXML
    private HBox InsertItem;

    @FXML
    private TextField FieldInsertName;
    @FXML
    private TextField FieldInsertAmount;
    @FXML
    private TextField FieldInsertURLImage;

    @FXML
    private Button btnAddInsert;
    @FXML
    private Pane stackAddInsert;
    @FXML
    private Button btnSaveInsert;
    @FXML
    private Pane stackNormalInsert;
    @FXML
    private Button btnCancelInsert;
    @FXML
    private Button btnCancel;

    private Controller mainController;

    public Controller getMainController() {
        return mainController;
    }

    public void setMainController(Controller mainController) {
        this.mainController = mainController;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void assignTextLabel(String name, String amount, String urlImage){
        FieldName.setText(name);
        FieldAmount.setText(amount);
        FieldURLImage.setText(urlImage);

        FieldNameLabel.setText(name);
        FieldAmountLabel.setText(amount);
        FieldURLImageLabel.setText(urlImage);
    }

    @FXML
    public void methodToMouseEntered(){
        this.itemT1.setStyle("-fx-background-color : #0A0E3F");
        this.itemT2.setStyle("-fx-background-color : #0A0E3F");
    }

    @FXML
    public void methodToMouseExited(){
        this.itemT1.setStyle("-fx-background-color : #02030A");
        this.itemT2.setStyle("-fx-background-color : #02030A");
    }
    @FXML
    public void methodToMouseEnteredEditButton(){
        this.btnEdit.setStyle("-fx-background-color : #AEF3AA");
    }
    @FXML
    public void methodToMouseExitedEditButton(){
        this.btnEdit.setStyle("-fx-background-color : #008000 ");
    }

    @FXML
    public void methodToMouseEnteredDeleteButton(){
        this.btnDelete.setStyle("-fx-background-color : #E85D55");
    }
    @FXML
    public void methodToMouseExitedDeleteButton(){
        this.btnDelete.setStyle("-fx-background-color : #DD0D01");
    }

    @FXML
    public void methodToMouseEnteredSaveButton(){
        this.btnSave.setStyle("-fx-background-color : #AEF3AA");
    }
    @FXML
    public void methodToMouseExitedSaveButton(){
        this.btnSave.setStyle("-fx-background-color : #008000 ");
    }
    @FXML
    public void methodToMouseEnteredCancelButton(){
        this.btnCancel.setStyle("-fx-background-color : #E85D55");
    }
    @FXML
    public void methodToMouseExitedCancelButton(){
        this.btnCancel.setStyle("-fx-background-color : #DD0D01");
    }


    public void handleClicks(ActionEvent actionEvent) throws IOException {
        SportToolQueries sportToolSQL = new SportToolQueries(mainController.getConn());
        sportToolSQL.setMainController(mainController);
        if(actionEvent.getSource() == btnDelete){
            sportToolSQL.deleteSportTool(this.FieldName.getText());
            if(sportToolSQL.getActionForCancel() == 0){
                mainController.getPnSportToolsItems().getChildren().remove(this.itemT);
                mainController.getNorSportTools().setText(String.valueOf(Integer.valueOf(mainController.getNorSportTools().getText())-1));

            }

            System.out.println(this.FieldName.getText() + " Deleted\n");
        }
        if(actionEvent.getSource() == btnAddInsert) {
            stackAddInsert.toFront();
        }
        if(actionEvent.getSource() == btnSaveInsert){

            int isFail = sportToolSQL.insertSportTools(FieldInsertName.getText(), FieldInsertAmount.getText(), FieldInsertURLImage.getText());

            if(isFail !=0){
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../ItemsFXML/SportToolItem.fxml"));
                Node node = fxmlLoader.load();
                SportToolItemController sportToolItemController = fxmlLoader.getController();

                sportToolItemController.assignTextLabel(FieldInsertName.getText(), FieldInsertAmount.getText(), FieldInsertURLImage.getText());

                sportToolItemController.setMainController(this.mainController);

                mainController.getPnSportToolsItems().getChildren().add(node);
                stackNormalInsert.toFront();
                mainController.getNorSportTools().setText(String.valueOf(Integer.valueOf(mainController.getNorSportTools().getText())+1));
            }


        }


        if(actionEvent.getSource() == btnCancelInsert){

            stackNormalInsert.toFront();
        }
        if(actionEvent.getSource() == btnEdit){
            stackEdit.toFront();
        }
        if(actionEvent.getSource() == btnCancel){
            FieldName.setText(FieldNameLabel.getText());
            FieldAmount.setText(FieldAmountLabel.getText());
            FieldURLImage.setText(FieldURLImageLabel.getText());
            stackNormal.toFront();
        }

        if(actionEvent.getSource() == btnSave){
            String oldFieldName = FieldNameLabel.getText();
            String oldFieldAmount = FieldAmountLabel.getText();
            String oldFieldURLImage = FieldURLImageLabel.getText();

            String newFieldName = FieldName.getText();
            String newFieldAmount = FieldAmount.getText();
            String newFieldURLImage = FieldURLImage.getText();


            if(( (oldFieldName != null && newFieldName == null) || (oldFieldName == null && newFieldName != null))
                    ? true
                    : (oldFieldName == null && newFieldName == null)
                    ? false
                    : !oldFieldName.equals(newFieldName)){
                sportToolSQL.updateSportToolName(oldFieldName,newFieldName);
                FieldNameLabel.setText(newFieldName);
            }
            if(( (oldFieldAmount != null && newFieldAmount == null) || (oldFieldAmount == null && newFieldAmount != null))
                    ? true
                    : (oldFieldAmount == null && newFieldAmount == null)
                    ? false
                    : !oldFieldAmount.equals(newFieldAmount)){
                sportToolSQL.updateSportToolAmount(newFieldName, newFieldAmount);
                FieldAmountLabel.setText(newFieldAmount);
            }

            if(( (oldFieldURLImage != null && newFieldURLImage == null) || (oldFieldURLImage == null && newFieldURLImage != null))
                    ? true
                    : (oldFieldURLImage == null && newFieldURLImage == null)
                    ? false
                    : !oldFieldURLImage.equals(newFieldURLImage)){
                sportToolSQL.updateSportToolAmount(newFieldName, newFieldAmount);
                FieldAmountLabel.setText(newFieldAmount);
            }

            JOptionPane.showMessageDialog(null, "Saved "); //showConfirmDialog(null, "Are you sure you want to delete this item?");
            stackNormal.toFront();
        }
    }
}

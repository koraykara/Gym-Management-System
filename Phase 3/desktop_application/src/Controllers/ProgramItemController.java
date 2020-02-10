package Controllers;

import Queries.ProgramQueries;
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

public class ProgramItemController implements Initializable {

    @FXML
    private HBox itemT;

    @FXML
    private TextField FieldID;

    @FXML
    private TextField FieldBMI;

    @FXML
    private Label FieldIDLabel;

    @FXML
    private Label FieldBMILabel;

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
    private Pane itemC1;
    @FXML
    private Pane itemC2;



    @FXML
    private TextField FieldInsertBMI;

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

    public void assignTextLabel( String ID, String bmi){

        FieldID.setText(ID);
        FieldIDLabel.setText(ID);

        FieldBMI.setText(bmi);
        FieldBMILabel.setText(bmi);
    }

    @FXML
    public void methodToMouseEntered(){
        this.itemC1.setStyle("-fx-background-color : #0A0E3F");
        this.itemC2.setStyle("-fx-background-color : #0A0E3F");
    }

    @FXML
    public void methodToMouseExited(){
        this.itemC1.setStyle("-fx-background-color : #02030A");
        this.itemC2.setStyle("-fx-background-color : #02030A");
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
        ProgramQueries programQueries = new ProgramQueries(mainController.getConn());
        programQueries.setMainController(mainController);
        if(actionEvent.getSource() == btnDelete){

            programQueries.deleteProgram(this.FieldID.getText());
            if(programQueries.getActionForCancel() == 0){
                mainController.getPnProgramItems().getChildren().remove(this.itemT);
                mainController.getNorProgram().setText(String.valueOf(Integer.valueOf(mainController.getNorProgram().getText())-1));

            }

        }

        if(actionEvent.getSource() == btnAddInsert) {
            stackAddInsert.toFront();
        }
        if(actionEvent.getSource() == btnSaveInsert){

            int insertedID = programQueries.insertProgram(FieldInsertBMI.getText());

            if(insertedID !=0){
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../ItemsFXML/ProgramItem.fxml"));
                Node node = fxmlLoader.load();
                ProgramItemController programItemController = fxmlLoader.getController();

                programItemController.assignTextLabel(String.valueOf(insertedID),FieldInsertBMI.getText());

                programItemController.setMainController(this.mainController);

                mainController.getPnProgramItems().getChildren().add(node);
                FieldInsertBMI.setText("");
                stackNormalInsert.toFront();
                mainController.getNorProgram().setText(String.valueOf(Integer.valueOf(mainController.getNorProgram().getText())+1));
            }
        }

        if(actionEvent.getSource() == btnCancelInsert){
            stackNormalInsert.toFront();
        }
        if(actionEvent.getSource() == btnCancel){
            FieldID.setText(FieldIDLabel.getText());
            FieldBMI.setText(FieldBMILabel.getText());
            stackNormal.toFront();
        }
        if(actionEvent.getSource() == btnEdit){
            stackEdit.toFront();
        }

        if(actionEvent.getSource() == btnSave){
            String oldFieldID = FieldIDLabel.getText();
            String oldFieldBMI = FieldBMILabel.getText();

            String newFieldID = FieldID.getText();
            String newFieldBMI = FieldBMI.getText();

            if(( (oldFieldID != null && newFieldID == null) || (oldFieldID == null && newFieldID != null))
                    ? true
                    : (oldFieldID == null && newFieldID == null)
                    ? false
                    : !oldFieldID.equals(newFieldID)){
                programQueries.updateID(oldFieldID,newFieldID);
                FieldIDLabel.setText(newFieldID);
            }

            if(( (oldFieldBMI != null && newFieldBMI == null) || (oldFieldBMI == null && newFieldBMI != null))
                    ? true
                    : (oldFieldBMI == null && newFieldBMI == null)
                    ? false
                    : !oldFieldBMI.equals(newFieldBMI)){
                programQueries.updateBMI(newFieldID,newFieldBMI);
                FieldBMILabel.setText(newFieldBMI);
            }
            JOptionPane.showMessageDialog(null, "Saved "); //showConfirmDialog(null, "Are you sure you want to delete this item?");
            stackNormal.toFront();
            System.out.println("Updated");
        }
    }
}
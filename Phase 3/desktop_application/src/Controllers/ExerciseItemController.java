package Controllers;

import Queries.ExerciseQueries;
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

public class ExerciseItemController implements Initializable {
    @FXML
    private HBox itemC;
    @FXML
    private Label FieldNameLabel; // ??????!!!!!!! UsernameID is here
    @FXML
    private Label FieldSetLabel;
    @FXML
    private Label FieldRepetitionPerSetLabel;

    @FXML
    private TextField FieldName;
    @FXML
    private TextField FieldSet;
    @FXML
    private TextField FieldRepetitionPerSet;

    @FXML
    private Button btnDelete; // for Delete operation
    @FXML
    private Button btnDelete1;
    @FXML
    private Button btnEdit;
    @FXML
    private Button btnSave;
    @FXML
    private Pane stackEdit;

    public Pane getStackNormal() {
        return stackNormal;
    }

    public void setStackNormal(Pane stackNormal) {
        this.stackNormal = stackNormal;
    }

    @FXML
    private Pane stackNormal;

    @FXML
    private Pane itemC1;
    @FXML
    private Pane itemC2;

    //FOR INSERT
    @FXML
    private HBox InsertItem;

    @FXML
    private TextField FieldInsertName;
    @FXML
    private TextField FieldInsertSet;
    @FXML
    private TextField FieldInsertRepetitionPerSet;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void assignTextLabel(String Name , String Set , String RepetitionPerSet){
        FieldName.setText(Name);
        FieldSet.setText(Set );
        FieldRepetitionPerSet.setText(RepetitionPerSet );

        FieldNameLabel.setText(Name);
        FieldSetLabel.setText(Set );
        FieldRepetitionPerSetLabel.setText(RepetitionPerSet );
    }

    public void handleClicks(ActionEvent actionEvent) throws IOException {
        ExerciseQueries exerciseSQL = new ExerciseQueries(mainController.getConn());
        exerciseSQL.setMainController(mainController);
        if(actionEvent.getSource() == btnDelete){
            exerciseSQL.deleteExercise(this.FieldName.getText());
            if(exerciseSQL.getActionForCancel() == 0){
                mainController.getPnExerciseItems().getChildren().remove(this.itemC);
                mainController.getNorExercise().setText(String.valueOf(Integer.valueOf(mainController.getNorExercise().getText())-1));

            }
        }
        if(actionEvent.getSource() == btnAddInsert) {
            stackAddInsert.toFront();
        }

        if(actionEvent.getSource() == btnSaveInsert){

            int insertID = exerciseSQL.insertExercise(FieldInsertName.getText(), FieldInsertSet.getText(), FieldInsertRepetitionPerSet.getText() );

            if(insertID != 0){
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../ItemsFXML/ExerciseItem.fxml"));
                Node node = fxmlLoader.load();
                ExerciseItemController exerciseItemController = fxmlLoader.getController();

                exerciseItemController.assignTextLabel(FieldInsertName.getText(), FieldInsertSet.getText(), FieldInsertRepetitionPerSet.getText() );
                FieldInsertName.setText("");
                FieldInsertSet.setText("");
                FieldInsertRepetitionPerSet.setText("");

                exerciseItemController.setMainController(this.mainController);

                mainController.getPnExerciseItems().getChildren().add(node);
                stackNormalInsert.toFront();
                mainController.getNorExercise().setText(String.valueOf(Integer.valueOf(mainController.getNorExercise().getText())+1));
            }

        }


        if(actionEvent.getSource() == btnCancelInsert){
            stackNormalInsert.toFront();
        }

        if(actionEvent.getSource() == btnCancel){
            FieldName.setText(FieldNameLabel.getText());
            FieldSet.setText(FieldSetLabel.getText());
            FieldRepetitionPerSet.setText(FieldRepetitionPerSetLabel.getText() );
            stackNormal.toFront();
        }
        if(actionEvent.getSource() == btnEdit){
            stackEdit.toFront();
        }

        if(actionEvent.getSource() == btnSave){
            String oldFieldName = FieldNameLabel.getText();
            String oldFieldSet = FieldSetLabel.getText();
            String oldFieldRepetitionPerSet = FieldRepetitionPerSetLabel.getText();

            String newFieldName = FieldName.getText();
            String newFieldSet = FieldSet.getText();
            String newFieldRepetitionPerSet = FieldRepetitionPerSet.getText();


            if(( (oldFieldName != null && newFieldName == null) || (oldFieldName == null && newFieldName != null))
                    ? true
                    : (oldFieldName == null && newFieldName == null)
                    ? false
                    : !oldFieldName.equals(newFieldName))
            {
                exerciseSQL.updateName(oldFieldName,newFieldName);
                FieldNameLabel.setText(newFieldName);
            }
            if(( (oldFieldSet != null && newFieldSet == null) || (oldFieldSet == null && newFieldSet != null))
                    ? true
                    : (oldFieldSet == null && newFieldSet == null)
                    ? false
                    : !oldFieldSet.equals(newFieldSet))
            {
                exerciseSQL.updateSet(newFieldName,newFieldSet);
                FieldSetLabel.setText(newFieldSet);
            }

            if(( (oldFieldRepetitionPerSet != null && newFieldRepetitionPerSet == null) || (oldFieldRepetitionPerSet == null && newFieldRepetitionPerSet != null))
                    ? true
                    : (oldFieldRepetitionPerSet == null && newFieldRepetitionPerSet == null)
                    ? false
                    : !oldFieldRepetitionPerSet.equals(newFieldRepetitionPerSet))
            {
                exerciseSQL.updateRepetitionPerSet(newFieldName,newFieldRepetitionPerSet);
                FieldRepetitionPerSetLabel.setText(newFieldRepetitionPerSet);
            }
            JOptionPane.showMessageDialog(null, "Saved "); //showConfirmDialog(null, "Are you sure you want to delete this item?");
            stackNormal.toFront();
            System.out.println("Updated");
        }
    }
}

package Controllers;

import Queries.ConsisftOfQueries;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class ConsistOfItemController implements Initializable {
    @FXML
    private HBox itemC;
    @FXML
    private Label FieldProgramIDLabel; // ??????!!!!!!! UsernameID is here
    @FXML
    private Label FieldExerciseNameLabel;

    @FXML
    private ChoiceBox<String> boxInsertItemProgramID;
    @FXML
    private ChoiceBox<String> boxInsertItemExerciseName;
    @FXML
    private ChoiceBox<String> boxItemProgramID;
    @FXML
    private ChoiceBox<String> boxItemExerciseName;



    @FXML
    private Button btnDelete; // for Delete operation
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

    private void createBox() throws SQLException {
        Statement stmt = mainController.getConn().createStatement();
        String sql = "SELECT * FROM Program";
        ResultSet rs = stmt.executeQuery(sql);
        while(rs.next()){
            this.boxItemProgramID.getItems().add(rs.getString("ID"));
        }
        this.boxItemProgramID.setValue(this.FieldProgramIDLabel.getText());

        stmt = mainController.getConn().createStatement();
        sql = "SELECT * FROM Exercise";
        rs = stmt.executeQuery(sql);
        while(rs.next()){
            this.boxItemExerciseName.getItems().add(rs.getString("Name"));
        }
        this.boxItemExerciseName.setValue(this.FieldExerciseNameLabel.getText());

    }


    public void createInsertBox() throws SQLException {
        Statement stmt = mainController.getConn().createStatement();
        String sql = "SELECT * FROM Program";
        ResultSet rs = stmt.executeQuery(sql);
        this.boxInsertItemProgramID.getItems().add("--select--");
        while(rs.next()){
            this.boxInsertItemProgramID.getItems().add(rs.getString("ID"));
        }
        this.boxInsertItemProgramID.setValue("--select--");

        stmt = mainController.getConn().createStatement();
        sql = "SELECT * FROM Exercise";
        rs = stmt.executeQuery(sql);
        this.boxInsertItemExerciseName.getItems().add("--select--");
        while(rs.next()){
            this.boxInsertItemExerciseName.getItems().add(rs.getString("Name"));
        }
        this.boxInsertItemExerciseName.setValue("--select--");
    }


    public void assignTextLabel(String programID, String exerciseName) throws SQLException {
        FieldProgramIDLabel.setText(programID );
        FieldExerciseNameLabel.setText(exerciseName);
        this.createBox();
    }

    public void handleClicks(ActionEvent actionEvent) throws IOException, SQLException {
        ConsisftOfQueries consistOfSQL = new ConsisftOfQueries(mainController.getConn());

        if(actionEvent.getSource() == btnDelete){
            consistOfSQL.deleteConsistOf(this.FieldProgramIDLabel.getText(), this.FieldExerciseNameLabel.getText());
            if(consistOfSQL.getActionForCancel() == 0){
                mainController.getPnConsistOfItems().getChildren().remove(this.itemC);
                mainController.getNorConsistOf().setText(String.valueOf(Integer.valueOf(mainController.getNorConsistOf().getText())-1));

            }
        }
        if(actionEvent.getSource() == btnAddInsert) {
            stackAddInsert.toFront();
        }

        if(actionEvent.getSource() == btnSaveInsert){

            if(!boxInsertItemProgramID.getValue().equals("--select--")
                    && !boxInsertItemExerciseName.getValue().equals("--select--")) {
                int insertID = consistOfSQL.insertConsistOf(boxInsertItemProgramID.getValue(), boxInsertItemExerciseName.getValue());

                if (insertID != 0) {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../ItemsFXML/ConsistOfItem.fxml"));
                    Node node = fxmlLoader.load();
                    ConsistOfItemController consistOfItemController = fxmlLoader.getController();


                    consistOfItemController.setMainController(this.mainController);
                    consistOfItemController.assignTextLabel(boxInsertItemProgramID.getValue(), boxInsertItemExerciseName.getValue());

                    mainController.getPnConsistOfItems().getChildren().add(node);

                    stackNormalInsert.toFront();
                    mainController.getNorConsistOf().setText(String.valueOf(Integer.valueOf(mainController.getNorConsistOf().getText()) + 1));
                    this.boxInsertItemProgramID.setValue("--select--");
                    this.boxInsertItemExerciseName.setValue("--select--");
                }
            }
            else
                JOptionPane.showMessageDialog(null, "You should choose something for all choice boxes!");

        }


        if(actionEvent.getSource() == btnCancelInsert){
            this.boxInsertItemProgramID.setValue("--select--");
            this.boxInsertItemExerciseName.setValue("--select--");
            stackNormalInsert.toFront();
        }

        if(actionEvent.getSource() == btnCancel){
            this.boxItemProgramID.setValue(FieldProgramIDLabel.getText() );
            this.boxItemExerciseName.setValue(FieldExerciseNameLabel.getText());
            stackNormal.toFront();
        }
        if(actionEvent.getSource() == btnEdit){
            stackEdit.toFront();
        }

        if(actionEvent.getSource() == btnSave){
            String oldFieldProgramID = FieldProgramIDLabel.getText();
            String oldFieldExerciseName = FieldExerciseNameLabel.getText();

            String newFieldProgramID = boxItemProgramID.getValue();
            String newFieldExerciseName = boxItemExerciseName.getValue();

            if(( (oldFieldProgramID != null && newFieldProgramID == null) || (oldFieldProgramID == null && newFieldProgramID != null))
                    ? true
                    : (oldFieldProgramID == null && newFieldProgramID == null)
                    ? false
                    : !oldFieldProgramID.equals(newFieldProgramID))
            {
                consistOfSQL.updateProgramID(oldFieldProgramID,newFieldExerciseName, newFieldProgramID);
                FieldProgramIDLabel.setText(newFieldProgramID);
            }
            if(( (oldFieldExerciseName != null && newFieldExerciseName == null) || (oldFieldExerciseName == null && newFieldExerciseName != null))
                    ? true
                    : (oldFieldExerciseName == null && newFieldExerciseName == null)
                    ? false
                    : !oldFieldExerciseName.equals(newFieldExerciseName))
            {
                consistOfSQL.updateExerciseName(oldFieldExerciseName,newFieldProgramID,newFieldExerciseName);
                FieldExerciseNameLabel.setText(newFieldExerciseName);
            }

            JOptionPane.showMessageDialog(null, "Saved "); //showConfirmDialog(null, "Are you sure you want to delete this item?");
            stackNormal.toFront();
            System.out.println("Updated");
        }
    }
}

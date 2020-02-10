package Controllers;

import Queries.MakeWithQueries;
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

public class MakeWithItemController implements Initializable {
    @FXML
    private HBox itemC;
    @FXML
    private Label FieldSportToolsNameLabel; // ??????!!!!!!! UsernameID is here
    @FXML
    private Label FieldExerciseNameLabel;

    @FXML
    private ChoiceBox<String> boxItemSportToolsName;
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
    private ChoiceBox<String> boxInsertItemSportToolsName;
    @FXML
    private ChoiceBox<String> boxInsertItemExerciseName;

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
        String sql = "SELECT * FROM Sport_Tools";
        ResultSet rs = stmt.executeQuery(sql);
        while(rs.next()){
            this.boxItemSportToolsName.getItems().add(rs.getString("Name"));
        }

        this.boxItemSportToolsName.setValue(this.FieldSportToolsNameLabel.getText());

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
        String sql = "SELECT * FROM Sport_Tools";
        ResultSet rs = stmt.executeQuery(sql);
        this.boxInsertItemSportToolsName.getItems().add("--select--");
        while(rs.next()){
            this.boxInsertItemSportToolsName.getItems().add(rs.getString("Name"));
        }
        this.boxInsertItemSportToolsName.setValue("--select--");

        stmt = mainController.getConn().createStatement();
        sql = "SELECT * FROM Exercise";
        rs = stmt.executeQuery(sql);
        this.boxInsertItemExerciseName.getItems().add("--select--");
        while(rs.next()){
            this.boxInsertItemExerciseName.getItems().add(rs.getString("Name"));
        }
        this.boxInsertItemExerciseName.setValue("--select--");
    }

    public void assignTextLabel(String SportToolsName , String ExerciseName) throws SQLException {

        FieldSportToolsNameLabel.setText(SportToolsName);
        FieldExerciseNameLabel.setText(ExerciseName);
        this.createBox();
    }

    public void handleClicks(ActionEvent actionEvent) throws IOException, SQLException {
        MakeWithQueries makeWithSQL = new MakeWithQueries(mainController.getConn());

        if(actionEvent.getSource() == btnDelete){
            makeWithSQL.deleteMakeWith(this.FieldSportToolsNameLabel.getText(), this.FieldExerciseNameLabel.getText());
            if(makeWithSQL.getActionForCancel() == 0){
                mainController.getPnMakeWithItems().getChildren().remove(this.itemC);
                mainController.getNorMakeWith().setText(String.valueOf(Integer.valueOf(mainController.getNorMakeWith().getText())-1));
            }
        }
        if(actionEvent.getSource() == btnAddInsert) {
            stackAddInsert.toFront();
        }

        if(actionEvent.getSource() == btnSaveInsert){

            if(!boxInsertItemSportToolsName.getValue().equals("--select--")
                    && !boxInsertItemExerciseName.getValue().equals("--select--")) {

                int insertID = makeWithSQL.insertMakeWith(boxInsertItemSportToolsName.getValue(), boxInsertItemExerciseName.getValue());

                if (insertID != 0) {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../ItemsFXML/MakeWithItem.fxml"));
                    Node node = fxmlLoader.load();
                    MakeWithItemController makeWithItemController = fxmlLoader.getController();

                    makeWithItemController.setMainController(this.mainController);
                    makeWithItemController.assignTextLabel(boxInsertItemSportToolsName.getValue(), boxInsertItemExerciseName.getValue());


                    mainController.getPnMakeWithItems().getChildren().add(node);
                    stackNormalInsert.toFront();
                    mainController.getNorMakeWith().setText(String.valueOf(Integer.parseInt(mainController.getNorMakeWith().getText()) + 1));
                    this.boxInsertItemSportToolsName.setValue("--select--");
                    this.boxInsertItemExerciseName.setValue("--select--");
                }
            }
            else
                JOptionPane.showMessageDialog(null, "You should choose something for all choice boxes!");

        }

        if(actionEvent.getSource() == btnCancelInsert){
            this.boxInsertItemSportToolsName.setValue("--select--");
            this.boxInsertItemExerciseName.setValue("--select--");
            stackNormalInsert.toFront();
        }

        if(actionEvent.getSource() == btnCancel){
            boxItemSportToolsName.setValue(FieldSportToolsNameLabel.getText());
            boxItemExerciseName.setValue(FieldExerciseNameLabel.getText());
            stackNormal.toFront();
        }
        if(actionEvent.getSource() == btnEdit){
            stackEdit.toFront();
        }

        if(actionEvent.getSource() == btnSave){
            String oldFieldSportToolsName = FieldSportToolsNameLabel.getText();
            String oldFieldExerciseName = FieldExerciseNameLabel.getText();

            String newFieldSportToolsName = boxItemSportToolsName.getValue();
            String newFieldExerciseName = boxItemExerciseName.getValue();

            if(( (oldFieldSportToolsName != null && newFieldSportToolsName == null) || (oldFieldSportToolsName == null && newFieldSportToolsName != null))
                    ? true
                    : (oldFieldSportToolsName == null && newFieldSportToolsName == null)
                    ? false
                    : !oldFieldSportToolsName.equals(newFieldSportToolsName))
            {
                makeWithSQL.updateSportToolsName(oldFieldSportToolsName,newFieldExerciseName, newFieldSportToolsName);
                FieldSportToolsNameLabel.setText(newFieldSportToolsName);
            }
            if(( (oldFieldExerciseName != null && newFieldExerciseName == null) || (oldFieldExerciseName == null && newFieldExerciseName != null))
                    ? true
                    : (oldFieldExerciseName == null && newFieldExerciseName == null)
                    ? false
                    : !oldFieldExerciseName.equals(newFieldExerciseName))
            {
                makeWithSQL.updateExerciseName(oldFieldExerciseName,newFieldSportToolsName,newFieldExerciseName);
                FieldExerciseNameLabel.setText(newFieldExerciseName);
            }
            JOptionPane.showMessageDialog(null, "Saved "); //showConfirmDialog(null, "Are you sure you want to delete this item?");
            stackNormal.toFront();
            System.out.println("Updated");
        }
    }
}

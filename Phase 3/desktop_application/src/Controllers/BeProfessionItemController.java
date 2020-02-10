package Controllers;

import Queries.BeProfessionQueries;
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

public class BeProfessionItemController implements Initializable {
    @FXML
    private HBox itemC;
    @FXML
    private Label FieldProfessionNameLabel; // ??????!!!!!!! UsernameID is here
    @FXML
    private Label FieldTrainerIDLabel;
    //Changed
    @FXML
    private ChoiceBox<String> boxItemProfessionName;
    @FXML
    private ChoiceBox<String> boxItemTrainerID;
    @FXML
    private ChoiceBox<String> boxInsertItemProfessionName;
    @FXML
    private ChoiceBox<String> boxInsertItemTrainerID;


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

    @FXML
    private Button btnCancel;
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
        String sql = "SELECT * FROM Profession";
        ResultSet rs = stmt.executeQuery(sql);
        while(rs.next()){
            this.boxItemProfessionName.getItems().add(rs.getString("Name"));
        }

        this.boxItemProfessionName.setValue(this.FieldProfessionNameLabel.getText());

        stmt = mainController.getConn().createStatement();
        sql = "SELECT * FROM Trainer";
        rs = stmt.executeQuery(sql);
        while(rs.next()){
            this.boxItemTrainerID.getItems().add(rs.getString("UsernameID"));
        }
        this.boxItemTrainerID.setValue(this.FieldTrainerIDLabel.getText());

    }


    public void createInsertBox() throws SQLException {
        Statement stmt = mainController.getConn().createStatement();
        String sql = "SELECT * FROM Profession";
        ResultSet rs = stmt.executeQuery(sql);

        this.boxInsertItemProfessionName.getItems().add("--select--");
        while(rs.next()){
            this.boxInsertItemProfessionName.getItems().add(rs.getString("Name"));
        }
        this.boxInsertItemProfessionName.setValue("--select--");

        stmt = mainController.getConn().createStatement();
        sql = "SELECT * FROM Trainer";
        rs = stmt.executeQuery(sql);


        this.boxInsertItemTrainerID.getItems().add("--select--");
        while(rs.next()){
            this.boxInsertItemTrainerID.getItems().add(rs.getString("UsernameID"));
        }
        this.boxInsertItemTrainerID.setValue("--select--");
    }


    public void assignTextLabel(String name, String id) throws SQLException {
        FieldProfessionNameLabel.setText(name);
        FieldTrainerIDLabel.setText(id);
        this.createBox(); // The coming of this method after the initializing of labels is important!
    }

    public void handleClicks(ActionEvent actionEvent) throws IOException, SQLException {
        BeProfessionQueries beProfessionSQL = new BeProfessionQueries(mainController.getConn());

        if(actionEvent.getSource() == btnDelete){
            //mainController.getNodes().remove(0);
            beProfessionSQL.deleteBeProfessionName(this.FieldProfessionNameLabel.getText());
            if(beProfessionSQL.getActionForCancel() == 0){
                mainController.getPnBeProfessionItems().getChildren().remove(this.itemC);
                mainController.getNorBeProfession().setText(String.valueOf(Integer.valueOf(mainController.getNorBeProfession().getText())-1));
            }
        }
        if(actionEvent.getSource() == btnAddInsert) {
            stackAddInsert.toFront();
        }
        if(actionEvent.getSource() == btnSaveInsert){

            if(!boxInsertItemProfessionName.getValue().equals("--select--") &&
                    !boxInsertItemTrainerID.getValue().equals("--select--")){
                int isFail = beProfessionSQL.insertBeProfession(boxInsertItemProfessionName.getValue(), boxInsertItemTrainerID.getValue());

                if(isFail !=0){
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../ItemsFXML/BeProfessionItem.fxml"));
                    Node node = fxmlLoader.load();
                    BeProfessionItemController beProfessionItemController = fxmlLoader.getController();

                    beProfessionItemController.setMainController(this.mainController);
                    beProfessionItemController.assignTextLabel(boxInsertItemProfessionName.getValue(), boxInsertItemTrainerID.getValue());
                    mainController.getPnBeProfessionItems().getChildren().add(node);
                    stackNormalInsert.toFront();

                    this.boxInsertItemProfessionName.setValue("--select--");
                    this.boxInsertItemTrainerID.setValue("--select--");
                    mainController.getNorBeProfession().setText(String.valueOf(Integer.valueOf(mainController.getNorBeProfession().getText())+1));
                }
            }
            else{
                JOptionPane.showMessageDialog(null, "You should choose something for all choice boxes!");
            }

        }


        if(actionEvent.getSource() == btnCancelInsert){
            this.boxInsertItemProfessionName.setValue("--select--");
            this.boxInsertItemTrainerID.setValue("--select--");
            stackNormalInsert.toFront();
        }

        if(actionEvent.getSource() == btnCancel){

            boxItemProfessionName.setValue(FieldProfessionNameLabel.getText());
            boxItemTrainerID.setValue(FieldTrainerIDLabel.getText());
            stackNormal.toFront();
        }

        if(actionEvent.getSource() == btnEdit){
            stackEdit.toFront();
        }

        if(actionEvent.getSource() == btnSave){
            String oldFieldProfessionName = FieldProfessionNameLabel.getText();
            String oldFieltrainerID = FieldTrainerIDLabel.getText();

            String newFieldProfessionName = boxItemProfessionName.getValue();
            String newFieltrainerID = boxItemTrainerID.getValue();
            if(( (oldFieldProfessionName != null && newFieldProfessionName == null) || (oldFieldProfessionName == null && newFieldProfessionName != null))
                    ? true
                    : (oldFieldProfessionName == null && newFieldProfessionName == null)
                    ? false
                    : !oldFieldProfessionName.equals(newFieldProfessionName)){
                beProfessionSQL.updateBeProfessionName(oldFieldProfessionName, newFieldProfessionName);
                FieldProfessionNameLabel.setText(newFieldProfessionName);
            }
            if(( (oldFieltrainerID != null && newFieltrainerID == null) || (oldFieltrainerID == null && newFieltrainerID != null))
                    ? true
                    : (oldFieltrainerID == null && newFieltrainerID == null)
                    ? false
                    : !oldFieltrainerID.equals(newFieltrainerID)){
                beProfessionSQL.updateBeProfessionTrainerID(newFieldProfessionName,newFieltrainerID);
                FieldTrainerIDLabel.setText(newFieltrainerID);
            }
            JOptionPane.showMessageDialog(null, "Saved "); //showConfirmDialog(null, "Are you sure you want to delete this item?");
            stackNormal.toFront();
            System.out.println("Updated");
        }
    }
}

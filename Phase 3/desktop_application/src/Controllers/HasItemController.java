package Controllers;

import Queries.HasQueries;
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

public class HasItemController implements Initializable {
    @FXML
    private HBox itemC;
    @FXML
    private Label FieldCustomerIDLabel; // ??????!!!!!!! UsernameID is here
    @FXML
    private Label FieldPyhsicalAilmentNameLabel;

    @FXML
    private ChoiceBox<String> boxItemCustomerID;
    @FXML
    private ChoiceBox<String> boxItemPyhsicalAilmentName;

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
    private ChoiceBox<String> boxInsertItemCustomerID;
    @FXML
    private ChoiceBox<String> boxInsertItemPyhsicalAilmentName;

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
        String sql = "SELECT * FROM Customer";
        ResultSet rs = stmt.executeQuery(sql);
        while(rs.next()){
            this.boxItemCustomerID.getItems().add(rs.getString("UsernameID"));
        }

        this.boxItemCustomerID.setValue(this.FieldCustomerIDLabel.getText());

        stmt = mainController.getConn().createStatement();
        sql = "SELECT * FROM physical_ailment";
        rs = stmt.executeQuery(sql);
        while(rs.next()){
            this.boxItemPyhsicalAilmentName.getItems().add(rs.getString("Name"));
        }
        this.boxItemPyhsicalAilmentName.setValue(this.FieldPyhsicalAilmentNameLabel.getText());

    }


    public void createInsertBox() throws SQLException {
        Statement stmt = mainController.getConn().createStatement();
        String sql = "SELECT * FROM Customer";
        ResultSet rs = stmt.executeQuery(sql);
        this.boxInsertItemCustomerID.getItems().add("--select--");
        while(rs.next()){
            this.boxInsertItemCustomerID.getItems().add(rs.getString("UsernameID"));
        }
        this.boxInsertItemCustomerID.setValue("--select--");

        stmt = mainController.getConn().createStatement();
        sql = "SELECT * FROM physical_ailment";
        rs = stmt.executeQuery(sql);
        this.boxInsertItemPyhsicalAilmentName.getItems().add("--select--");
        while(rs.next()){
            this.boxInsertItemPyhsicalAilmentName.getItems().add(rs.getString("Name"));
        }
        this.boxInsertItemPyhsicalAilmentName.setValue("--select--");
    }
    public void assignTextLabel(String customerID, String PyhsicalAilmentName) throws SQLException {

        FieldCustomerIDLabel.setText(customerID);
        FieldPyhsicalAilmentNameLabel.setText(PyhsicalAilmentName);

        this.createBox(); // The coming of this method after the initializing of labels is important!

    }

    public void handleClicks(ActionEvent actionEvent) throws IOException, SQLException {
        HasQueries hasSQL = new HasQueries(mainController.getConn());

        if(actionEvent.getSource() == btnDelete){
            //mainController.getNodes().remove(0);
            hasSQL.deleteHas(this.FieldCustomerIDLabel.getText(), this.FieldPyhsicalAilmentNameLabel.getText());
            if(hasSQL.getActionForCancel() == 0){
                mainController.getPnHasItems().getChildren().remove(this.itemC);
                mainController.getNorHas().setText(String.valueOf(Integer.valueOf(mainController.getNorHas().getText())-1));

            }
        }

        if(actionEvent.getSource() == btnAddInsert) {
            stackAddInsert.toFront();
        }

        if(actionEvent.getSource() == btnSaveInsert){
            if(!boxInsertItemCustomerID.getValue().equals("--select--")
                    && !boxInsertItemPyhsicalAilmentName.getValue().equals("--select--")) {
                int insertID = hasSQL.insertHas(boxInsertItemCustomerID.getValue(), boxInsertItemPyhsicalAilmentName.getValue());

                if (insertID != 0) {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../ItemsFXML/HasItem.fxml"));
                    Node node = fxmlLoader.load();
                    HasItemController hasItemController = fxmlLoader.getController();


                    hasItemController.setMainController(this.mainController);
                    hasItemController.assignTextLabel(boxInsertItemCustomerID.getValue(), boxInsertItemPyhsicalAilmentName.getValue());

                    mainController.getPnHasItems().getChildren().add(node);
                    stackNormalInsert.toFront();
                    mainController.getNorHas().setText(String.valueOf(Integer.valueOf(mainController.getNorHas().getText()) + 1));
                    this.boxInsertItemCustomerID.setValue("--select--");
                    this.boxInsertItemPyhsicalAilmentName.setValue("--select--");
                }
            }
            else
                JOptionPane.showMessageDialog(null, "You should choose something for all choice boxes!");


        }


        if(actionEvent.getSource() == btnCancelInsert){
            this.boxInsertItemCustomerID.setValue("--select--");
            this.boxInsertItemPyhsicalAilmentName.setValue("--select--");
            stackNormalInsert.toFront();
        }

        if(actionEvent.getSource() == btnCancel){


            boxItemCustomerID.setValue(FieldCustomerIDLabel.getText());
            boxItemPyhsicalAilmentName.setValue(FieldPyhsicalAilmentNameLabel.getText());
            stackNormal.toFront();
        }

        if(actionEvent.getSource() == btnEdit){
            stackEdit.toFront();
        }

        if(actionEvent.getSource() == btnSave){
            String oldFieldCustomerID = FieldCustomerIDLabel.getText();
            String oldFieldPyhsicalAilment = FieldPyhsicalAilmentNameLabel.getText();

            String newFieldCustomerID = boxItemCustomerID.getValue();
            String newFieldPyhsicalAilment = boxItemPyhsicalAilmentName.getValue();

            if(( (oldFieldCustomerID != null && newFieldCustomerID == null) || (oldFieldCustomerID == null && newFieldCustomerID != null))
                    ? true
                    : (oldFieldCustomerID == null && newFieldCustomerID == null)
                    ? false
                    : !oldFieldCustomerID.equals(newFieldCustomerID))
             {
                hasSQL.updateCustomerID(oldFieldCustomerID,newFieldPyhsicalAilment, newFieldCustomerID);
                FieldCustomerIDLabel.setText(newFieldCustomerID);
            }
            if(( (oldFieldPyhsicalAilment != null && newFieldPyhsicalAilment == null) || (oldFieldPyhsicalAilment == null && newFieldPyhsicalAilment != null))
                    ? true
                    : (oldFieldPyhsicalAilment == null && newFieldPyhsicalAilment == null)
                    ? false
                    : !oldFieldPyhsicalAilment.equals(newFieldPyhsicalAilment))
            {
                hasSQL.updatePyhsicalAilment(oldFieldPyhsicalAilment,newFieldCustomerID,newFieldPyhsicalAilment);
                FieldPyhsicalAilmentNameLabel.setText(newFieldPyhsicalAilment);
            }
            JOptionPane.showMessageDialog(null, "Saved "); //showConfirmDialog(null, "Are you sure you want to delete this item?");
            stackNormal.toFront();
            System.out.println("Updated");
        }
    }
}

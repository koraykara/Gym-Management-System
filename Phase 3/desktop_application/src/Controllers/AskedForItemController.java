package Controllers;

import Queries.AskedForQueries;
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

public class AskedForItemController implements Initializable {
    @FXML
    private HBox itemC;
    @FXML
    private Label FieldCustomerIDLabel; // ??????!!!!!!! UsernameID is here
    @FXML
    private Label FieldProgramIDLabel;

    //Changed
    @FXML
    private ChoiceBox<String> boxItemCustomerID;
    @FXML
    private ChoiceBox<String> boxItemProgramID;
    @FXML
    private ChoiceBox<String> boxInsertItemCustomerID;
    @FXML
    private ChoiceBox<String> boxInsertItemProgramID;

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
        sql = "SELECT * FROM Program";
        rs = stmt.executeQuery(sql);
        while(rs.next()){
            this.boxItemProgramID.getItems().add(rs.getString("ID"));
        }
        this.boxItemProgramID.setValue(this.FieldProgramIDLabel.getText());

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
        sql = "SELECT * FROM Program";
        rs = stmt.executeQuery(sql);
        this.boxInsertItemProgramID.getItems().add("--select--");
        while(rs.next()){
            this.boxInsertItemProgramID.getItems().add(rs.getString("ID"));
        }
        this.boxInsertItemProgramID.setValue("--select--");
    }

    public void assignTextLabel(String CustomerID, String ProgramID) throws SQLException {
        FieldCustomerIDLabel.setText(CustomerID);
        FieldProgramIDLabel.setText(ProgramID);
        this.createBox(); // The coming of this method after the initializing of labels is important!
    }

    public void handleClicks(ActionEvent actionEvent) throws IOException, SQLException {
        AskedForQueries askedForSQL = new AskedForQueries(mainController.getConn());

        if(actionEvent.getSource() == btnDelete){
            askedForSQL.deleteAskedFor(this.FieldCustomerIDLabel.getText(), this.FieldProgramIDLabel.getText());
            if(askedForSQL.getActionForCancel() == 0){
                mainController.getPnAskedForItems().getChildren().remove(this.itemC);
                mainController.getNorAskedFor().setText(String.valueOf(Integer.valueOf(mainController.getNorAskedFor().getText())-1));
            }
        }


        if(actionEvent.getSource() == btnAddInsert) {
            stackAddInsert.toFront();
        }

        if(actionEvent.getSource() == btnSaveInsert){

            if(!boxInsertItemCustomerID.getValue().equals("--select--")
                    && !boxInsertItemProgramID.getValue().equals("--select--")){

                int insertID = askedForSQL.insertAskedFor(boxInsertItemCustomerID.getValue(), boxInsertItemProgramID.getValue() );

                if(insertID != 0){
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../ItemsFXML/AskedForItem.fxml"));
                    Node node = fxmlLoader.load();
                    AskedForItemController askedForItemController = fxmlLoader.getController();

                    // Should be added
                    askedForItemController.setMainController(this.mainController);
                    askedForItemController.assignTextLabel(boxInsertItemCustomerID.getValue(), boxInsertItemProgramID.getValue());
                    // Should be added


                    mainController.getPnAskedForItems().getChildren().add(node);
                    stackNormalInsert.toFront();
                    mainController.getNorAskedFor().setText(String.valueOf(Integer.valueOf(mainController.getNorAskedFor().getText())+1));

                    this.boxInsertItemCustomerID.setValue("--select--");
                    this.boxInsertItemProgramID.setValue("--select--");
                }
            }
            else{
                JOptionPane.showMessageDialog(null, "You should choose something for all choice boxes!");
            }
        }


        if(actionEvent.getSource() == btnCancelInsert){
            this.boxInsertItemCustomerID.setValue("--select--");
            this.boxInsertItemProgramID.setValue("--select--");
            stackNormalInsert.toFront();
        }

        if(actionEvent.getSource() == btnCancel){
            boxItemCustomerID.setValue(FieldCustomerIDLabel.getText());
            boxItemProgramID.setValue(FieldProgramIDLabel.getText());
            stackNormal.toFront();
        }

        if(actionEvent.getSource() == btnEdit){
            stackEdit.toFront();
        }

        if(actionEvent.getSource() == btnSave){
            String oldFieldCustomerID = FieldCustomerIDLabel.getText();
            String oldFieldProgramID = FieldProgramIDLabel.getText();

            String newFieldCustomerID = boxItemCustomerID.getValue();
            String newFieldProgramID = boxItemProgramID.getValue();


            if(( (oldFieldCustomerID != null && newFieldCustomerID == null) || (oldFieldCustomerID == null && newFieldCustomerID != null))
                    ? true
                    : (oldFieldCustomerID == null && newFieldCustomerID == null)
                    ? false
                    : !oldFieldCustomerID.equals(newFieldCustomerID))
            {
                askedForSQL.updateCustomerID(oldFieldCustomerID,newFieldProgramID, newFieldCustomerID);
                FieldCustomerIDLabel.setText(newFieldCustomerID);
            }
            if(( (oldFieldProgramID != null && newFieldProgramID == null) || (oldFieldProgramID == null && newFieldProgramID != null))
                    ? true
                    : (oldFieldProgramID == null && newFieldProgramID == null)
                    ? false
                    : !oldFieldProgramID.equals(newFieldProgramID))
            {
                askedForSQL.updateProgramID(oldFieldProgramID,newFieldCustomerID,newFieldProgramID);
                FieldProgramIDLabel.setText(newFieldProgramID);
            }
            JOptionPane.showMessageDialog(null, "Saved "); //showConfirmDialog(null, "Are you sure you want to delete this item?");
            stackNormal.toFront();
            System.out.println("Updated");
        }
    }
}



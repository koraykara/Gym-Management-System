package Controllers;

import Queries.PurchaseQueries;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class PurchaseItemController implements Initializable {
    @FXML
    private HBox itemC;
    @FXML
    private Label FieldCustomerIDLabel; // ??????!!!!!!! UsernameID is here
    @FXML
    private Label FieldLessonIDLabel;
    @FXML
    private Label FieldEnrollTimeDayLabel;
    @FXML
    private Label FieldPurchaseDateLabel;

    @FXML
    private ChoiceBox<String> boxItemCustomerID;
    @FXML
    private TextField FieldLessonID;
    @FXML
    private TextField FieldEnrollTimeDay;
    @FXML
    private TextField FieldPurchaseDate;

    @FXML
    private Button btnDelete; // for Delete operation
    @FXML
    private Button btnDelete1;
    @FXML
    private Button btnEdit;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnCancel;
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
    private HBox InsertItem;

    @FXML
    private ChoiceBox<String> boxInsertItemCustomerID;
    @FXML
    private TextField FieldInsertLessonID;
    @FXML
    private TextField FieldInsertEnrollTimeDay;
    @FXML
    private TextField FieldInsertPurchaseDate;

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
        String sql = "SELECT * FROM Customer";
        ResultSet rs = stmt.executeQuery(sql);
        while(rs.next()){
            this.boxItemCustomerID.getItems().add(rs.getString("UsernameID"));
        }
        this.boxItemCustomerID.setValue(this.FieldCustomerIDLabel.getText());

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
    }


    public void assignTextLabel(String customerID, String lessonID, String EnrollTimeDay, String purchaseDate) throws SQLException {

        if(lessonID != null)
            FieldLessonID.setText(lessonID );
        FieldEnrollTimeDay.setText(EnrollTimeDay);
        FieldPurchaseDate.setText(purchaseDate);

        FieldCustomerIDLabel.setText(customerID);
        if(lessonID != null)
            FieldLessonIDLabel.setText(lessonID );
        FieldEnrollTimeDayLabel.setText(EnrollTimeDay);
        FieldPurchaseDateLabel.setText(purchaseDate);
        this.createBox();

    }
    public void handleClicks(ActionEvent actionEvent) throws IOException, SQLException {
        PurchaseQueries purchaseSQL = new PurchaseQueries(mainController.getConn());

        if(actionEvent.getSource() == btnDelete){
            //mainController.getNodes().remove(0);
            purchaseSQL.deletePurchase(this.FieldCustomerIDLabel.getText(), this.FieldLessonID.getText(),this.FieldEnrollTimeDay.getText());
            if(purchaseSQL.getActionForCancel() == 0){
                mainController.getPnPurchaseItems().getChildren().remove(this.itemC);
                mainController.getNorPurchase().setText(String.valueOf(Integer.valueOf(mainController.getNorPurchase().getText())-1));

            }
        }

        if(actionEvent.getSource() == btnAddInsert) {
            stackAddInsert.toFront();
        }

        if(actionEvent.getSource() == btnSaveInsert){

            if(!boxInsertItemCustomerID.getValue().equals("--select--") ) {
                int insertID = purchaseSQL.insertPurchase(boxInsertItemCustomerID.getValue(), FieldInsertLessonID.getText(), FieldInsertEnrollTimeDay.getText(), FieldInsertPurchaseDate.getText());

                if (insertID != 0) {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../ItemsFXML/PurchaseItem.fxml"));
                    Node node = fxmlLoader.load();
                    PurchaseItemController purchaseItemController = fxmlLoader.getController();

                    purchaseItemController.setMainController(this.mainController);
                    purchaseItemController.assignTextLabel(boxInsertItemCustomerID.getValue(), FieldInsertLessonID.getText(), FieldInsertEnrollTimeDay.getText(), FieldInsertPurchaseDate.getText());

                    FieldInsertLessonID.setText("");
                    FieldInsertEnrollTimeDay.setText("");
                    FieldInsertPurchaseDate.setText("");

                    mainController.getPnPurchaseItems().getChildren().add(node);
                    stackNormalInsert.toFront();
                    mainController.getNorPurchase().setText(String.valueOf(Integer.valueOf(mainController.getNorPurchase().getText()) + 1));

                    this.boxInsertItemCustomerID.setValue("--select--");
                }
            }
            else
                JOptionPane.showMessageDialog(null, "You should choose something for all choice boxes!");

        }


        if(actionEvent.getSource() == btnCancelInsert){
            this.boxInsertItemCustomerID.setValue("--select--");
            stackNormalInsert.toFront();
        }
        if(actionEvent.getSource() == btnCancel){
            boxItemCustomerID.setValue(FieldCustomerIDLabel.getText());

            FieldLessonID.setText(FieldLessonIDLabel.getText() );
            FieldEnrollTimeDay.setText(FieldEnrollTimeDayLabel.getText());
            FieldPurchaseDate.setText(FieldPurchaseDateLabel.getText());

            stackNormal.toFront();
        }

        if(actionEvent.getSource() == btnEdit){
            stackEdit.toFront();
        }

        if(actionEvent.getSource() == btnSave){
            boolean isUpdate = false;
            String oldFieldCustomerID = FieldCustomerIDLabel.getText();
            String oldFieldLessonId = FieldLessonIDLabel.getText();
            String oldFieldEnrollTimeDay = FieldEnrollTimeDayLabel.getText();
            String oldFieldPurchasedDate = FieldPurchaseDateLabel.getText();

            String newFieldCustomerID = boxItemCustomerID.getValue();
            String newFieldLessonId = FieldLessonID.getText();
            String newFieldEnrollTimeDay = FieldEnrollTimeDay.getText();
            String newFieldPurchasedDate = FieldPurchaseDate.getText();

            if(( (oldFieldCustomerID != null && newFieldCustomerID == null) || (oldFieldCustomerID == null && newFieldCustomerID != null))
                    ? true
                    : (oldFieldCustomerID == null && newFieldCustomerID == null)
                    ? false
                    : !oldFieldCustomerID.equals(newFieldCustomerID))
            {
                purchaseSQL.updateCustomerID(oldFieldCustomerID,newFieldLessonId, newFieldEnrollTimeDay,newFieldCustomerID);
                FieldCustomerIDLabel.setText(newFieldCustomerID);
            }
            if(( (oldFieldLessonId != null && newFieldLessonId == null) || (oldFieldLessonId == null && newFieldLessonId != null))
                    ? true
                    : (oldFieldLessonId == null && newFieldLessonId == null)
                    ? false
                    : !oldFieldLessonId.equals(newFieldLessonId))
            {
                purchaseSQL.updateLessonID(newFieldCustomerID,oldFieldLessonId,newFieldEnrollTimeDay,newFieldLessonId);
                FieldLessonIDLabel.setText(newFieldLessonId);
            }

            if(( (oldFieldEnrollTimeDay != null && newFieldEnrollTimeDay == null) || (oldFieldEnrollTimeDay == null && newFieldEnrollTimeDay != null))
                    ? true
                    : (oldFieldEnrollTimeDay == null && newFieldEnrollTimeDay == null)
                    ? false
                    : !oldFieldEnrollTimeDay.equals(newFieldEnrollTimeDay))
            {
                purchaseSQL.updateEnrollTimeDay(newFieldCustomerID,newFieldLessonId, oldFieldEnrollTimeDay,newFieldEnrollTimeDay);
                FieldEnrollTimeDayLabel.setText(newFieldEnrollTimeDay);
            }

            if(( (oldFieldPurchasedDate != null && newFieldPurchasedDate == null) || (oldFieldPurchasedDate == null && newFieldPurchasedDate != null))
                    ? true
                    : (oldFieldPurchasedDate == null && newFieldPurchasedDate == null)
                    ? false
                    : !oldFieldPurchasedDate.equals(newFieldPurchasedDate))
            {
                purchaseSQL.updatePurchasedDay(newFieldCustomerID,newFieldLessonId, newFieldEnrollTimeDay,newFieldPurchasedDate);
                FieldPurchaseDateLabel.setText(newFieldPurchasedDate);
            }
            JOptionPane.showMessageDialog(null, "Saved "); //showConfirmDialog(null, "Are you sure you want to delete this item?");
            stackNormal.toFront();
            System.out.println("Updated");
        }
    }
}

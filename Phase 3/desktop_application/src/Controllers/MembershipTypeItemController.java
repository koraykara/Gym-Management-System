package Controllers;

import Queries.MembershipQueries;
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

public class MembershipTypeItemController implements Initializable {
    @FXML
    private HBox itemC;
    @FXML
    private Label FieldNameLabel; // ??????!!!!!!! UsernameID is here
    @FXML
    private Label FieldPriceLabel;

    @FXML
    private TextField FieldName;
    @FXML
    private TextField FieldPrice;

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
    private HBox InsertItem;

    @FXML
    private TextField FieldInsertName;
    @FXML
    private TextField FieldInsertPrice;

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

    public void assignTextLabel(String Name, String Price){
        if(Price != null)
            FieldPrice.setText(Price );

        FieldName.setText(Name);

        if(Price != null)
            FieldPriceLabel.setText(Price );

        FieldNameLabel.setText(Name);
    }

    public void handleClicks(ActionEvent actionEvent) throws IOException {
        MembershipQueries membershipTypeSQL = new MembershipQueries(mainController.getConn());
        membershipTypeSQL.setMainController(mainController);


        if(actionEvent.getSource() == btnDelete){
            membershipTypeSQL.deleteMembershipType(this.FieldName.getText());
            if(membershipTypeSQL.getActionForCancel() == 0){
                mainController.getPnMembershipItems().getChildren().remove(this.itemC);
                mainController.getNorMembershipType().setText(String.valueOf(Integer.valueOf(mainController.getNorMembershipType().getText())-1));

            }
        }

        if(actionEvent.getSource() == btnAddInsert) {
            stackAddInsert.toFront();
        }
        if(actionEvent.getSource() == btnSaveInsert){

            membershipTypeSQL.insertMembershipType(FieldInsertName.getText(), FieldInsertPrice.getText() );

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../ItemsFXML/MembershipItem.fxml"));
            Node node = fxmlLoader.load();
            MembershipTypeItemController membershipTypeItemController = fxmlLoader.getController();

            membershipTypeItemController.assignTextLabel(FieldInsertName.getText(), FieldInsertPrice.getText() );

            membershipTypeItemController.setMainController(this.mainController);
            FieldInsertName.setText("");
            FieldInsertPrice.setText("");
            mainController.getPnMembershipItems().getChildren().add(node);
            stackNormalInsert.toFront();
            mainController.getNorMembershipType().setText(String.valueOf(Integer.valueOf(mainController.getNorMembershipType().getText())+1));

        }


        if(actionEvent.getSource() == btnCancelInsert){
            stackNormalInsert.toFront();
        }


        if(actionEvent.getSource() == btnCancel){
            FieldPrice.setText(FieldPriceLabel.getText() );

            FieldName.setText(FieldNameLabel.getText());

            stackNormal.toFront();
        }

        if(actionEvent.getSource() == btnEdit){
            stackEdit.toFront();
        }

        if(actionEvent.getSource() == btnSave){
            String oldFieldName = FieldNameLabel.getText();
            String oldFieldPrice = FieldPriceLabel.getText();

            String newFieldName = FieldName.getText();
            String newFieldPrice = FieldPrice.getText();

            if(( (oldFieldName != null && newFieldName == null) || (oldFieldName == null && newFieldName != null))
                    ? true
                    : (oldFieldName == null && newFieldName == null)
                    ? false
                    : !oldFieldName.equals(newFieldName)){
                membershipTypeSQL.updateName(oldFieldName,newFieldName);
                FieldNameLabel.setText(newFieldName);
            }
            if(( (oldFieldPrice != null && newFieldPrice == null) || (oldFieldPrice == null && newFieldPrice != null))
                    ? true
                    : (oldFieldPrice == null && newFieldPrice == null)
                    ? false
                    : !oldFieldPrice.equals(newFieldPrice)){
                membershipTypeSQL.updatePrice(newFieldName,newFieldPrice);
                FieldPriceLabel.setText(newFieldPrice);
            }
            JOptionPane.showMessageDialog(null, "Saved "); //showConfirmDialog(null, "Are you sure you want to delete this item?");
            stackNormal.toFront();
            System.out.println("Updated");
        }
    }
}

package Controllers;

import Queries.CustomerQueries;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CustomerItemController implements Initializable {


    @FXML
    private HBox itemC;
    @FXML
    private Label FieldUsernameIDLabel; // ??????!!!!!!! UsernameID is here
    @FXML
    private Label FieldMembershipTypeLabel;
    @FXML
    private Label FieldTrainerUsernameLabel;
    @FXML
    private Label FieldWeightLabel;
    @FXML
    private Label FieldLengthLabel;
    @FXML
    private Label FieldAgeLabel;
    @FXML
    private Label FieldFatRatioLabel;
    @FXML
    private Label FieldCreditCardNameLabel;
    @FXML
    private Label FieldCardExpireDateLabel;


    @FXML
    private TextField FieldUsernameID;
    @FXML
    private ChoiceBox<String> boxItemMembershipType;
    @FXML
    private ChoiceBox<String> boxItemTrainerUsername;
    @FXML
    private TextField FieldWeight;
    @FXML
    private TextField FieldLength;
    @FXML
    private TextField FieldAge;
    @FXML
    private TextField FieldFatRatio;
    @FXML
    private TextField FieldCreditCardName;
    @FXML
    private TextField FieldCardExpireDate;

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



    // For insert operation
    @FXML
    private HBox InsertItemCustomer;

    @FXML
    private TextField FieldInsertUsernameID;
    @FXML
    private ChoiceBox<String> boxInsertItemMembership;
    @FXML
    private ChoiceBox<String> boxInsertItemTrainerID;
    @FXML
    private TextField FieldInsertWeight;
    @FXML
    private TextField FieldInsertLength;
    @FXML
    private TextField FieldInsertAge;
    @FXML
    private TextField FieldInsertFatRatio;
    @FXML
    private TextField FieldInsertCreditCardNumber;
    @FXML
    private TextField FieldInsertCreditCardExpireDate;

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
        String sql = "SELECT * FROM membership_type";
        ResultSet rs = stmt.executeQuery(sql);
        this.boxItemMembershipType.getItems().add(null);
        while(rs.next()){
            this.boxItemMembershipType.getItems().add(rs.getString("Name"));
        }

        this.boxItemMembershipType.setValue(this.FieldMembershipTypeLabel.getText());

        stmt = mainController.getConn().createStatement();
        sql = "SELECT * FROM Trainer";
        rs = stmt.executeQuery(sql);
        this.boxItemTrainerUsername.getItems().add(null);
        while(rs.next()){
            this.boxItemTrainerUsername.getItems().add(rs.getString("UsernameID"));
        }
        this.boxItemTrainerUsername.setValue(this.FieldTrainerUsernameLabel.getText());

    }


    public void createInsertBox() throws SQLException {
        Statement stmt = mainController.getConn().createStatement();
        String sql = "SELECT * FROM membership_type";
        ResultSet rs = stmt.executeQuery(sql);
        this.boxInsertItemMembership.getItems().add("--select--");
        while(rs.next()){
            this.boxInsertItemMembership.getItems().add(rs.getString("Name"));
        }
        this.boxInsertItemMembership.setValue("--select--");

        stmt = mainController.getConn().createStatement();
        sql = "SELECT * FROM Trainer";
        rs = stmt.executeQuery(sql);
        this.boxInsertItemTrainerID.getItems().add("--select--");
        while(rs.next()){
            this.boxInsertItemTrainerID.getItems().add(rs.getString("UsernameID"));
        }
        this.boxInsertItemTrainerID.setValue("--select--");
    }

    public void assignTextLabel(String id, String membership,
                                String trainerId, String weight, String length, String age, String fatRatio, String creditCard,  String cardExpireDate) throws SQLException {
        FieldUsernameID.setText(id);
        FieldWeight.setText(weight);
        FieldLength.setText(length);
        FieldAge.setText(age);
        FieldFatRatio.setText(fatRatio);
        FieldCreditCardName.setText(creditCard);
        FieldCardExpireDate.setText(cardExpireDate);

        FieldUsernameIDLabel.setText(id);
        FieldMembershipTypeLabel.setText(membership);
        FieldTrainerUsernameLabel.setText(trainerId);
        FieldWeightLabel.setText(weight);
        FieldLengthLabel.setText(length);
        FieldAgeLabel.setText(age);
        FieldFatRatioLabel.setText(fatRatio);
        FieldCreditCardNameLabel.setText(creditCard);
        FieldCardExpireDateLabel.setText(cardExpireDate);
        this.createBox();
    }

    public void handleClicks(ActionEvent actionEvent) throws IOException, SQLException {
        CustomerQueries customerSQL = new CustomerQueries(mainController.getConn());
        customerSQL.setMainController(mainController);
        if(actionEvent.getSource() == btnDelete){
            customerSQL.deleteCustomer(this.FieldUsernameIDLabel.getText());
            if(customerSQL.getActionForCancel() == 0){
                mainController.getPnItems().getChildren().remove(this.itemC);
                mainController.getNorCustomer().setText(String.valueOf(Integer.valueOf(mainController.getNorCustomer().getText())-1));

            }
        }

        if(actionEvent.getSource() == btnAddInsert) {
            stackAddInsert.toFront();
        }
        if(actionEvent.getSource() == btnSaveInsert){
            String pattern = "[1-9][0-9][0-9][0-9]-(0[1-9]|[1][0-2])-((([1-2][0-9])|([3][0-1]))|0[1-9])";
            Pattern patternObject = Pattern.compile(pattern);

            // Now create matcher object.
            Matcher matcher = patternObject.matcher(FieldInsertCreditCardExpireDate.getText());
            if (matcher.find( )) {
                if(!boxInsertItemMembership.getValue().equals("--select--")
                        && !boxInsertItemTrainerID.getValue().equals("--select--")) {
                    int insertFail = customerSQL.insertCustomer(FieldInsertUsernameID.getText(), boxInsertItemMembership.getValue(), boxInsertItemTrainerID.getValue(),
                            FieldInsertWeight.getText(), FieldInsertLength.getText(),
                            FieldInsertAge.getText(), FieldInsertFatRatio.getText(), FieldInsertCreditCardNumber.getText(), FieldInsertCreditCardExpireDate.getText());

                    if (insertFail != 0) {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../ItemsFXML/CustomerItem.fxml"));
                        Node node = fxmlLoader.load();
                        CustomerItemController customerItemController = (CustomerItemController) fxmlLoader.getController();

                        customerItemController.setMainController(this.mainController);

                        customerItemController.assignTextLabel(FieldInsertUsernameID.getText(), boxInsertItemMembership.getValue(), boxInsertItemTrainerID.getValue(),
                                FieldInsertWeight.getText(), FieldInsertLength.getText(),
                                FieldInsertAge.getText(), FieldInsertFatRatio.getText(), FieldInsertCreditCardNumber.getText(), FieldInsertCreditCardExpireDate.getText());


                        FieldInsertUsernameID.setText("");
                        FieldInsertWeight.setText("");
                        FieldInsertLength.setText("");
                        FieldInsertAge.setText("");
                        FieldInsertFatRatio.setText("");
                        FieldInsertCreditCardNumber.setText("");
                        FieldInsertCreditCardExpireDate.setText("");

                        mainController.getPnItems().getChildren().add(node);

                        stackNormalInsert.toFront();
                        mainController.getNorCustomer().setText(String.valueOf(Integer.valueOf(mainController.getNorCustomer().getText()) + 1));
                        this.boxInsertItemMembership.setValue("--select--");
                        this.boxInsertItemTrainerID.setValue("--select--");
                    }
                }
                else if(!boxInsertItemMembership.getValue().equals("--select--") &&
                        boxInsertItemTrainerID.getValue().equals("--select--")){
                    int insertFail = customerSQL.insertCustomer(FieldInsertUsernameID.getText(), boxInsertItemMembership.getValue(), null,
                            FieldInsertWeight.getText(), FieldInsertLength.getText(),
                            FieldInsertAge.getText(), FieldInsertFatRatio.getText(), FieldInsertCreditCardNumber.getText(), FieldInsertCreditCardExpireDate.getText());

                    if (insertFail != 0) {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../ItemsFXML/CustomerItem.fxml"));
                        Node node = fxmlLoader.load();
                        CustomerItemController customerItemController = (CustomerItemController) fxmlLoader.getController();

                        customerItemController.setMainController(this.mainController);

                        customerItemController.assignTextLabel(FieldInsertUsernameID.getText(), boxInsertItemMembership.getValue(), null,
                                FieldInsertWeight.getText(), FieldInsertLength.getText(),
                                FieldInsertAge.getText(), FieldInsertFatRatio.getText(), FieldInsertCreditCardNumber.getText(), FieldInsertCreditCardExpireDate.getText());


                        FieldInsertUsernameID.setText("");
                        FieldInsertWeight.setText("");
                        FieldInsertLength.setText("");
                        FieldInsertAge.setText("");
                        FieldInsertFatRatio.setText("");
                        FieldInsertCreditCardNumber.setText("");
                        FieldInsertCreditCardExpireDate.setText("");

                        mainController.getPnItems().getChildren().add(node);

                        stackNormalInsert.toFront();
                        mainController.getNorCustomer().setText(String.valueOf(Integer.valueOf(mainController.getNorCustomer().getText()) + 1));
                        this.boxInsertItemMembership.setValue("--select--");
                        this.boxInsertItemTrainerID.setValue("--select--");
                    }
                }

                else if(boxInsertItemMembership.getValue().equals("--select--")
                        && !boxInsertItemTrainerID.getValue().equals("--select--")) {
                    int insertFail = customerSQL.insertCustomer(FieldInsertUsernameID.getText(), null, boxInsertItemTrainerID.getValue(),
                            FieldInsertWeight.getText(), FieldInsertLength.getText(),
                            FieldInsertAge.getText(), FieldInsertFatRatio.getText(), FieldInsertCreditCardNumber.getText(), FieldInsertCreditCardExpireDate.getText());

                    if (insertFail != 0) {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../ItemsFXML/CustomerItem.fxml"));
                        Node node = fxmlLoader.load();
                        CustomerItemController customerItemController = (CustomerItemController) fxmlLoader.getController();

                        customerItemController.setMainController(this.mainController);

                        customerItemController.assignTextLabel(FieldInsertUsernameID.getText(), null, boxInsertItemTrainerID.getValue(),
                                FieldInsertWeight.getText(), FieldInsertLength.getText(),
                                FieldInsertAge.getText(), FieldInsertFatRatio.getText(), FieldInsertCreditCardNumber.getText(), FieldInsertCreditCardExpireDate.getText());


                        FieldInsertUsernameID.setText("");
                        FieldInsertWeight.setText("");
                        FieldInsertLength.setText("");
                        FieldInsertAge.setText("");
                        FieldInsertFatRatio.setText("");
                        FieldInsertCreditCardNumber.setText("");
                        FieldInsertCreditCardExpireDate.setText("");

                        mainController.getPnItems().getChildren().add(node);

                        stackNormalInsert.toFront();
                        mainController.getNorCustomer().setText(String.valueOf(Integer.valueOf(mainController.getNorCustomer().getText()) + 1));
                        this.boxInsertItemMembership.setValue("--select--");
                        this.boxInsertItemTrainerID.setValue("--select--");
                    }
                }


                else if(boxInsertItemMembership.getValue().equals("--select--")
                        && boxInsertItemTrainerID.getValue().equals("--select--")) {
                    int insertFail = customerSQL.insertCustomer(FieldInsertUsernameID.getText(), null, null,
                            FieldInsertWeight.getText(), FieldInsertLength.getText(),
                            FieldInsertAge.getText(), FieldInsertFatRatio.getText(), FieldInsertCreditCardNumber.getText(), FieldInsertCreditCardExpireDate.getText());

                    if (insertFail != 0) {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../ItemsFXML/CustomerItem.fxml"));
                        Node node = fxmlLoader.load();
                        CustomerItemController customerItemController = (CustomerItemController) fxmlLoader.getController();

                        customerItemController.setMainController(this.mainController);

                        customerItemController.assignTextLabel(FieldInsertUsernameID.getText(), null, null,
                                FieldInsertWeight.getText(), FieldInsertLength.getText(),
                                FieldInsertAge.getText(), FieldInsertFatRatio.getText(), FieldInsertCreditCardNumber.getText(), FieldInsertCreditCardExpireDate.getText());




                        mainController.getPnItems().getChildren().add(node);

                        stackNormalInsert.toFront();
                        mainController.getNorCustomer().setText(String.valueOf(Integer.valueOf(mainController.getNorCustomer().getText()) + 1));
                        FieldInsertUsernameID.setText("");
                        FieldInsertWeight.setText("");
                        FieldInsertLength.setText("");
                        FieldInsertAge.setText("");
                        FieldInsertFatRatio.setText("");
                        FieldInsertCreditCardNumber.setText("");
                        FieldInsertCreditCardExpireDate.setText("");
                        this.boxInsertItemMembership.setValue("--select--");
                        this.boxInsertItemTrainerID.setValue("--select--");
                    }
                }



            }else {
                JOptionPane.showMessageDialog(null, "For Card Expire Date is not valid !\nExample: 2021-12-28 ");
            }


        }
        if(actionEvent.getSource() == btnCancelInsert){
            FieldInsertUsernameID.setText("");
            this.boxInsertItemMembership.setValue("--select--");
            this.boxInsertItemTrainerID.setValue("--select--");
            FieldInsertWeight.setText("");
            FieldInsertLength.setText("");
            FieldInsertAge.setText("");
            FieldInsertFatRatio.setText("");
            FieldInsertCreditCardNumber.setText("");
            FieldInsertCreditCardExpireDate.setText("");
            stackNormalInsert.toFront();
        }
        if(actionEvent.getSource() == btnCancel){

            FieldUsernameID.setText(FieldUsernameIDLabel.getText());
            boxItemMembershipType.setValue(FieldMembershipTypeLabel.getText());
            boxItemTrainerUsername.setValue(FieldTrainerUsernameLabel.getText());
            FieldWeight.setText(FieldWeightLabel.getText());
            FieldLength.setText(FieldLengthLabel.getText());
            FieldAge.setText(FieldAgeLabel.getText());
            FieldFatRatio.setText(FieldFatRatioLabel.getText());
            FieldCreditCardName.setText(FieldCreditCardNameLabel.getText());
            FieldCardExpireDate.setText(FieldCardExpireDateLabel.getText());
            stackNormal.toFront();
        }

        if(actionEvent.getSource() == btnEdit){
            stackEdit.toFront();
        }
        if(actionEvent.getSource() == btnSave){
            boolean isUpdate = false;
            String oldFieldUsernameID = FieldUsernameIDLabel.getText();
            String oldFieldMembershipType = FieldMembershipTypeLabel.getText();
            String oldFieldTrainerUsername = FieldTrainerUsernameLabel.getText();
            String oldFieldWeight = FieldWeightLabel.getText();
            String oldFieldLength = FieldLengthLabel.getText();
            String oldFieldAge = FieldAgeLabel.getText();
            String oldFieldFatRatio = FieldFatRatioLabel.getText();
            String oldCreditCardName = FieldCreditCardNameLabel.getText();
            String oldFieldCardExpireDate = FieldCardExpireDateLabel.getText();


            String newFieldUsernameID = FieldUsernameID.getText();
            String newFieldMembershipTypeLabel = boxItemMembershipType.getValue();
            String newFieldTrainerUsernameLabel = boxItemTrainerUsername.getValue();
            String newFieldWeightLabel = FieldWeight.getText();
            String newFieldLengthLabel = FieldLength.getText();
            String newFieldAgeLabel = FieldAge.getText();
            String newFieldFatRatioLabel = FieldFatRatio.getText();
            String newFieldCardExpireDateLabel = FieldCardExpireDate.getText();
            String newFieldCreditCardNameLabel = FieldCreditCardName.getText();

            if(!oldFieldUsernameID.equals(newFieldUsernameID)){
                customerSQL.updateCustomerUserNameID(oldFieldUsernameID, newFieldUsernameID);
                FieldUsernameIDLabel.setText(newFieldUsernameID);
                 isUpdate = true;
            }


            if(( (oldFieldMembershipType != null && newFieldMembershipTypeLabel == null) || (oldFieldMembershipType == null && newFieldMembershipTypeLabel != null))
                    ? true
                    : (oldFieldMembershipType == null && newFieldMembershipTypeLabel == null)
                    ? false
                    : !oldFieldMembershipType.equals(newFieldMembershipTypeLabel)){
                customerSQL.updateCustomerMembershipType(newFieldUsernameID,newFieldMembershipTypeLabel);
                FieldMembershipTypeLabel.setText(newFieldMembershipTypeLabel);
                 isUpdate = true;
            }

            if(( (oldFieldTrainerUsername != null && newFieldTrainerUsernameLabel == null) || (oldFieldTrainerUsername == null && newFieldTrainerUsernameLabel != null))
                    ? true
                    : (oldFieldTrainerUsername == null && newFieldTrainerUsernameLabel == null)
                    ? false
                    : !oldFieldTrainerUsername.equals(newFieldTrainerUsernameLabel)){
                customerSQL.updateCustomerTrainerID(newFieldUsernameID,newFieldTrainerUsernameLabel);
                FieldTrainerUsernameLabel.setText(newFieldTrainerUsernameLabel);
                 isUpdate = true;
            }

            if(( (oldFieldWeight != null && newFieldWeightLabel == null) || (oldFieldWeight == null && newFieldWeightLabel != null))
                    ? true
                    : (oldFieldWeight == null && newFieldWeightLabel == null)
                        ? false
                        :!oldFieldWeight.equals(newFieldWeightLabel)){
                customerSQL.updateCustomerWeight(newFieldUsernameID,newFieldWeightLabel);
                FieldWeightLabel.setText(newFieldWeightLabel);
                 isUpdate = true;
            }

            if(((oldFieldLength != null && newFieldLengthLabel == null) || (oldFieldLength == null && newFieldLengthLabel != null))
                    ? true
                    : (oldFieldLength == null && newFieldLengthLabel == null)
                        ? false
                        : !oldFieldLength.equals(newFieldLengthLabel)){
                customerSQL.updateCustomerLength(newFieldUsernameID,newFieldLengthLabel);
                FieldLengthLabel.setText(newFieldLengthLabel);
                 isUpdate = true;
            }
            if(((oldFieldAge != null && newFieldAgeLabel == null) || (oldFieldAge == null && newFieldAgeLabel != null))
                    ? true
                    : ((oldFieldAge == null && newFieldAgeLabel == null)
                        ? false
                        : !oldFieldAge.equals(newFieldAgeLabel))){
                customerSQL.updateCustomerAge(newFieldUsernameID,newFieldAgeLabel);
                FieldAgeLabel.setText(newFieldAgeLabel);
                 isUpdate = true;
            }

            if(((oldFieldFatRatio != null && newFieldFatRatioLabel == null) || (oldFieldFatRatio == null && newFieldFatRatioLabel != null))
                    ? true
                    : (oldFieldFatRatio == null && newFieldFatRatioLabel == null)
                    ? false
                    : !oldFieldFatRatio.equals(newFieldFatRatioLabel)){
                customerSQL.updateCustomerFatRatio(newFieldUsernameID,newFieldFatRatioLabel);
                FieldFatRatioLabel.setText(newFieldFatRatioLabel);
                 isUpdate = true;
            }

            if(((oldCreditCardName != null && newFieldCreditCardNameLabel == null) || (oldCreditCardName == null && newFieldCreditCardNameLabel != null))
                    ? true
                    : (oldCreditCardName == null && newFieldCreditCardNameLabel == null)
                    ? false
                    : !oldCreditCardName.equals(newFieldCreditCardNameLabel)){
                customerSQL.updateCustomerCreditCardNumber(newFieldUsernameID,newFieldCreditCardNameLabel);
                FieldCreditCardNameLabel.setText(newFieldCreditCardNameLabel);
                 isUpdate = true;
            }

            if(((oldFieldCardExpireDate != null && newFieldCardExpireDateLabel == null) || (oldFieldCardExpireDate == null && newFieldCardExpireDateLabel != null))
                    ? true
                    : (oldFieldCardExpireDate == null && newFieldCardExpireDateLabel == null)
                    ? false
                    : !oldFieldCardExpireDate.equals(newFieldCardExpireDateLabel)){

                String pattern = "[1-9][0-9][0-9][0-9]-(0[1-9]|[1][0-2])-((([1-2][0-9])|([3][0-1]))|0[1-9])";
                Pattern patternObject = Pattern.compile(pattern);
                // Now create matcher object.

                Matcher matcher = patternObject.matcher(FieldCardExpireDate.getText());
                if (matcher.find( )) {
                    customerSQL.updateCustomerCardExpireDate(newFieldUsernameID,newFieldCardExpireDateLabel);
                    FieldCardExpireDate.setText(newFieldCardExpireDateLabel);
                    isUpdate = true;

                }else {
                    JOptionPane.showMessageDialog(null, "For Card Expire Date is not valid !\nExample: 2021-12-28 "); //showConfirmDialog(null, "Are you sure you want to delete this item?");
                }


            }

            if(isUpdate){
                JOptionPane.showMessageDialog(null, "Saved ");
                stackNormal.toFront();
            }


        }
    }




}
package Controllers;

import Queries.SystemUserQueries;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SystemUserItemController implements Initializable {
    @FXML
    private HBox itemC;
    @FXML
    private Label FieldUserNameIDLabel; // ??????!!!!!!! UsernameID is here
    @FXML
    private Label FieldNameLabel;
    @FXML
    private Label FieldSurnameLabel;
    @FXML
    private Label FieldStartDateLabel;
    @FXML
    private Label FieldEndDateLabel;
    @FXML
    private Label FieldPasswordLabel;

    @FXML
    private TextField FieldUserNameID;
    @FXML
    private TextField FieldName;
    @FXML
    private TextField FieldSurname;
    @FXML
    private TextField FieldStartDate;
    @FXML
    private TextField FieldEndDate;
    @FXML
    private TextField FieldPassword;

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
    private TextField FieldInsertUsernameID;
    @FXML
    private TextField FieldInsertName;
    @FXML
    private TextField FieldInsertSurname;
    @FXML
    private TextField FieldInsertStartDate;
    @FXML
    private TextField FieldInsertEndDate;
    @FXML
    private TextField FieldInsertPassword;

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

    public void assignTextLabel(String userNameID, String name, String surname, String startDate , String EndDate , String Password){
        FieldUserNameID.setText(userNameID);
        FieldName.setText(name);
        FieldSurname.setText(surname);
        FieldStartDate.setText(startDate);
        FieldEndDate.setText(EndDate);
        FieldPassword.setText(Password);

        FieldUserNameIDLabel.setText(userNameID);
        FieldNameLabel.setText(name);
        FieldSurnameLabel.setText(surname);
        FieldStartDateLabel.setText(startDate);
        FieldEndDateLabel.setText(EndDate);
        FieldPasswordLabel.setText(Password);
    }

    public void handleClicks(ActionEvent actionEvent) throws IOException {
        SystemUserQueries systemUserSQL = new SystemUserQueries(mainController.getConn());
        systemUserSQL.setMainController(mainController);
        if(actionEvent.getSource() == btnDelete){
            //mainController.getNodes().remove(0);
            systemUserSQL.deleteSystemUser(this.FieldUserNameID.getText());
            if(systemUserSQL.getActionForCancel() == 0){
                mainController.getPnSystemUserItems().getChildren().remove(this.itemC);
                mainController.getNorSystemUser().setText(String.valueOf(Integer.valueOf(mainController.getNorSystemUser().getText())-1));

            }
        }
        if(actionEvent.getSource() == btnAddInsert) {
            stackAddInsert.toFront();
        }

        if(actionEvent.getSource() == btnSaveInsert){
            String patternCustomer = "c_[a-zA-Z0-9]+";
            String patternTrainer = "t_[a-zA-Z0-9]+";
            String patternAdmin = "a_[a-zA-Z0-9]+";
            Pattern patternObjectCustomer = Pattern.compile(patternCustomer);
            Pattern patternObjectTrainer = Pattern.compile(patternTrainer);
            Pattern patternObjectAdmin = Pattern.compile(patternAdmin);

            // Now create matcher object.
            Matcher matcher11 = patternObjectCustomer.matcher(FieldInsertUsernameID.getText());
            Matcher matcher22 = patternObjectTrainer.matcher(FieldInsertUsernameID.getText());
            Matcher matcher33 = patternObjectAdmin.matcher(FieldInsertUsernameID.getText());

            if(matcher11.find() || matcher22.find() || matcher33.find() ){
                String pattern = "([0-1][0-9]|[2][0-3]):[0-5][0-9]:[0-5][0-9]";
                Pattern patternObject = Pattern.compile(pattern);

                // Now create matcher object.
                Matcher matcher1 = patternObject.matcher(FieldInsertStartDate.getText());

                if (matcher1.find() ) {
                    int insertID = systemUserSQL.insertSystemUser(FieldInsertUsernameID.getText(), FieldInsertName.getText(), FieldInsertSurname.getText(),
                            FieldInsertStartDate.getText(),
                            FieldInsertEndDate.getText(), FieldInsertPassword.getText());

                    if (insertID != 0) {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../ItemsFXML/SystemUserItem.fxml"));
                        Node node = fxmlLoader.load();
                        SystemUserItemController systemUserItemController = fxmlLoader.getController();

                        systemUserItemController.assignTextLabel(FieldInsertUsernameID.getText(), FieldInsertName.getText(), FieldInsertSurname.getText(), FieldInsertStartDate.getText(),
                                FieldInsertEndDate.getText(), FieldInsertPassword.getText());

                        FieldInsertUsernameID.setText("");
                        FieldInsertName.setText("");
                        FieldInsertSurname.setText("");
                        FieldInsertStartDate.setText("");
                        FieldInsertEndDate.setText("");
                        FieldInsertPassword.setText("");

                        systemUserItemController.setMainController(this.mainController);

                        mainController.getPnSystemUserItems().getChildren().add(node);
                        stackNormalInsert.toFront();
                        mainController.getNorSystemUser().setText(String.valueOf(Integer.valueOf(mainController.getNorSystemUser().getText())+1));
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null, "The Start Time or End Time is not valid !\nExample: 'HH:MM:SS' ");
                }
            }
            else{
                JOptionPane.showMessageDialog(null, "The username start with 'a_', 'c_' or 't_' \nand could just include alphabet and number");
            }
        }


        if(actionEvent.getSource() == btnCancelInsert){
            FieldInsertUsernameID.setText("");
            FieldInsertName.setText("");
            FieldInsertSurname.setText("");
            FieldInsertStartDate.setText("");
            FieldInsertEndDate.setText("");
            FieldInsertPassword.setText("");
            stackNormalInsert.toFront();
        }

        if(actionEvent.getSource() == btnCancel){
            FieldUserNameID.setText(FieldUserNameIDLabel.getText());

            FieldName.setText(FieldNameLabel.getText());
            FieldSurname.setText(FieldSurnameLabel.getText());
            FieldStartDate.setText(FieldStartDateLabel.getText());
            FieldEndDate.setText(FieldEndDateLabel.getText());
            FieldPassword.setText(FieldPasswordLabel.getText());
            stackNormal.toFront();
        }
        if(actionEvent.getSource() == btnEdit){
            stackEdit.toFront();
        }

        if(actionEvent.getSource() == btnSave){
            boolean isUpdate = false;
            String oldFieldUsernameID = FieldUserNameIDLabel.getText();
            String oldFieldName = FieldNameLabel.getText();
            String oldFieldSurname = FieldSurnameLabel.getText();
            String oldFieldStartDate = FieldStartDateLabel.getText();
            String oldFieldEndDate = FieldEndDateLabel.getText();
            String oldFieldPassword = FieldPasswordLabel.getText();

            String newFieldUsernameID = FieldUserNameID.getText();
            String newFieldName = FieldName.getText();
            String newFieldSurname = FieldSurname.getText();
            String newFieldStartDate = FieldStartDate.getText();
            String newFieldEndDate = FieldEndDate.getText();
            String newFieldPassword = FieldPassword.getText();

            if(( (oldFieldUsernameID != null && newFieldUsernameID == null) || (oldFieldUsernameID == null && newFieldUsernameID != null))
                    ? true
                    : (oldFieldUsernameID == null && newFieldUsernameID == null)
                    ? false
                    : !oldFieldUsernameID.equals(newFieldUsernameID))
            {
                String patternCustomer = "c_[a-zA-Z0-9]+";
                String patternTrainer = "t_[a-zA-Z0-9]+";
                String patternAdmin = "a_[a-zA-Z0-9]+";
                Pattern patternObjectCustomer = Pattern.compile(patternCustomer);
                Pattern patternObjectTrainer = Pattern.compile(patternTrainer);
                Pattern patternObjectAdmin = Pattern.compile(patternAdmin);

                // Now create matcher object.
                Matcher matcher11 = patternObjectCustomer.matcher(newFieldUsernameID);
                Matcher matcher22 = patternObjectTrainer.matcher(newFieldUsernameID);
                Matcher matcher33 = patternObjectAdmin.matcher(newFieldUsernameID);

                if(matcher11.find() || matcher22.find() || matcher33.find() ){
                    isUpdate = true;
                    systemUserSQL.updateUserNameID(oldFieldUsernameID,newFieldUsernameID);
                    FieldUserNameIDLabel.setText(newFieldUsernameID);
                }else{
                    JOptionPane.showMessageDialog(null, "The username start with 'a_', 'c_' or 't_' \nand could just include alphabet and number");

                }

            }

            if( ( (oldFieldName != null && newFieldName == null) || (oldFieldName == null && newFieldName != null))
                    ? true
                    : (oldFieldName == null && newFieldName == null)
                    ? false
                    : !oldFieldName.equals(newFieldName)){
                isUpdate = true;
                systemUserSQL.updateCustomerName(newFieldUsernameID,newFieldName);
                FieldNameLabel.setText(newFieldName);

            }

            if( ( (oldFieldSurname != null && newFieldSurname == null) || (oldFieldSurname == null && newFieldSurname != null))
                    ? true
                    : (oldFieldSurname == null && newFieldSurname == null)
                    ? false
                    : !oldFieldSurname.equals(newFieldSurname)){
                isUpdate = true;
                systemUserSQL.updateCustomerSurname(newFieldUsernameID,newFieldSurname);
                FieldSurnameLabel.setText(newFieldSurname);

            }

            if(( (oldFieldStartDate != null && newFieldStartDate == null) || (oldFieldStartDate == null && newFieldStartDate != null))
                    ? true
                    : (oldFieldStartDate == null && newFieldStartDate == null)
                    ? false
                    : !oldFieldStartDate.equals(newFieldStartDate))
            {
                String pattern = "([0-1][0-9]|[2][0-3]):[0-5][0-9]:[0-5][0-9]";
                Pattern patternObject = Pattern.compile(pattern);

                // Now create matcher object.
                Matcher matcher1 = patternObject.matcher(FieldStartDate.getText());

                if (matcher1.find()) {
                    isUpdate = true;
                    systemUserSQL.updateStartDate(newFieldUsernameID,newFieldStartDate);
                    FieldStartDateLabel.setText(newFieldStartDate);
                }
                else{
                    JOptionPane.showMessageDialog(null, "The Start Time is not valid !\nExample: 'HH:MM:SS' ");
                }

            }

            if(( (oldFieldEndDate != null && newFieldEndDate == null) || (oldFieldEndDate == null && newFieldEndDate != null))
                    ? true
                    : (oldFieldEndDate == null && newFieldEndDate == null)
                    ? false
                    : !oldFieldEndDate.equals(newFieldEndDate))
            {
                String pattern = "([0-1][0-9]|[2][0-3]):[0-5][0-9]:[0-5][0-9]";
                Pattern patternObject = Pattern.compile(pattern);

                // Now create matcher object.
                Matcher matcher2 = patternObject.matcher(FieldEndDate.getText());

                if (matcher2.find( )) {
                    isUpdate = true;
                    systemUserSQL.updateEndDate(newFieldUsernameID,newFieldEndDate);
                    FieldEndDateLabel.setText(newFieldEndDate);
                }else{
                    JOptionPane.showMessageDialog(null, "The End Time is not valid !\nExample: 'HH:MM:SS' ");

                }

            }

            if(( (oldFieldPassword != null && newFieldPassword == null) || (oldFieldPassword == null && newFieldPassword != null))
                    ? true
                    : (oldFieldPassword == null && newFieldPassword == null)
                    ? false
                    : !oldFieldPassword.equals(newFieldPassword))
            {
                isUpdate = true;
                systemUserSQL.updatePassword(newFieldUsernameID,newFieldPassword);
                FieldPasswordLabel.setText(newFieldPassword);
            }
            if(isUpdate){
                JOptionPane.showMessageDialog(null, "Saved "); //showConfirmDialog(null, "Are you sure you want to delete this item?");
                stackNormal.toFront();
            }

        }
    }
}

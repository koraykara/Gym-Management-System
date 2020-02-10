package Controllers;

import Queries.TrainerQueries;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class TrainerItemController implements Initializable {

    @FXML
    private HBox itemT;

    @FXML
    private TextField FieldUsernameID;
    @FXML
    private TextField FieldSalary;

    @FXML
    private Label FieldUsernameIDLabel;

    @FXML
    private Label FieldSalaryLabel;

    @FXML
    private Button btnDelete;


    @FXML
    private Button btnEdit;

    @FXML
    private Button btnSave;

    @FXML
    private Pane stackEdit;

    @FXML
    private Pane stackNormal;

    @FXML
    private Pane itemC1;
    @FXML
    private Pane itemC2;



    // For insert operation
    @FXML
    private HBox InsertItem;

    @FXML
    private  TextField FieldInsertItemUsernameID;
    @FXML
    private TextField FieldInsertSalary;

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

    @FXML
    private Button btnCancel;
    public Controller getMainController() {
        return mainController;
    }

    public void setMainController(Controller mainController) {
        this.mainController = mainController;
    }    @FXML
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


    public void assignTextLabel(String id, String salary) throws SQLException {
        FieldUsernameID.setText(id);
        FieldSalary.setText(salary);

        FieldUsernameIDLabel.setText(id);
        FieldSalaryLabel.setText(salary);
    }


    public void handleClicks(ActionEvent actionEvent) throws IOException, SQLException {
        TrainerQueries trainerSQL = new TrainerQueries(mainController.getConn());
        trainerSQL.setMainController(mainController);

        if(actionEvent.getSource() == btnDelete){
            trainerSQL.deleteTrainer(this.FieldUsernameIDLabel.getText());
            if(trainerSQL.getActionForCancel() == 0){
                mainController.getPnTrainerItems().getChildren().remove(this.itemT);
                mainController.getNorTrainer().setText(String.valueOf(Integer.valueOf(mainController.getNorTrainer().getText())-1));

            }
        }
        if(actionEvent.getSource() == btnAddInsert) {
            stackAddInsert.toFront();
        }
        if(actionEvent.getSource() == btnSaveInsert){

                trainerSQL.insertTrainer(FieldInsertItemUsernameID.getText(), FieldInsertSalary.getText());

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../ItemsFXML/TrainerItem.fxml"));
                Node node = fxmlLoader.load();
                TrainerItemController trainerItemController = fxmlLoader.getController();

                trainerItemController.setMainController(this.mainController);
                trainerItemController.assignTextLabel(FieldInsertItemUsernameID.getText(), FieldInsertSalary.getText());


                mainController.getPnTrainerItems().getChildren().add(node);
                stackNormalInsert.toFront();
                mainController.getNorTrainer().setText(String.valueOf(Integer.valueOf(mainController.getNorTrainer().getText()) + 1));
                this.FieldInsertItemUsernameID.setText("");
                this.FieldInsertSalary.setText("");
        }

        if(actionEvent.getSource() == btnCancelInsert){
            this.FieldInsertItemUsernameID.setText("");
            this.FieldInsertSalary.setText("");
            stackNormalInsert.toFront();
        }
        if(actionEvent.getSource() == btnCancel ){
            this.FieldUsernameID.setText(FieldUsernameIDLabel.getText());
            this.FieldSalary.setText(FieldSalaryLabel.getText());
            stackNormal.toFront();
        }
        if(actionEvent.getSource() == btnEdit){
            stackEdit.toFront();
        }

        if(actionEvent.getSource() == btnSave){
            String oldFieldUsernameID = FieldUsernameIDLabel.getText();
            String oldFieldSalary = FieldSalaryLabel.getText();

            String newFieldUsernameID = FieldUsernameID.getText();
            String newFieldSalary = FieldSalary.getText();

            if(( (oldFieldUsernameID != null && newFieldUsernameID == null) || (oldFieldUsernameID == null && newFieldUsernameID != null))
                    ? true
                    : (oldFieldUsernameID == null && newFieldUsernameID == null)
                    ? false
                    : !oldFieldUsernameID.equals(newFieldUsernameID)){
                trainerSQL.updateUserNameID(oldFieldUsernameID, newFieldUsernameID);
                FieldUsernameIDLabel.setText(newFieldUsernameID);
            }



            if(( (oldFieldSalary != null && newFieldSalary == null) || (oldFieldSalary == null && newFieldSalary != null))
                    ? true
                    : (oldFieldSalary == null && newFieldSalary == null)
                    ? false
                    : !oldFieldSalary.equals(newFieldSalary)){
                trainerSQL.updateSalary(newFieldUsernameID,newFieldSalary);
                FieldSalaryLabel.setText(newFieldSalary);
            }

            JOptionPane.showMessageDialog(null, "Saved "); //showConfirmDialog(null, "Are you sure you want to delete this item?");
            stackNormal.toFront();
        }
    }
}

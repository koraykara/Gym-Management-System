package Controllers;

import Queries.EnrollTimeQueries;
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

public class EnrollTimeItemController implements Initializable {
    @FXML
    private HBox itemC;
    @FXML
    private Label FieldLessonIDLabel; // ??????!!!!!!! UsernameID is here
    @FXML
    private Label FieldDayLabel;
    @FXML
    private Label FieldStartTimeLabel;
    @FXML
    private Label FieldEndTimeLabel;
    @FXML
    private Label FieldQuotaLabel;

    @FXML
    private ChoiceBox<String> boxItemLessonID;
    @FXML
    private TextField FieldDay;
    @FXML
    private TextField FieldStartTime;
    @FXML
    private TextField FieldEndTime;
    @FXML
    private TextField FieldQuota;

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


    @FXML
    private HBox InsertItem;

    @FXML
    private ChoiceBox<String> boxInsertItemLessonID;
    @FXML
    private TextField FieldInsertDay;
    @FXML
    private TextField FieldInsertStartTime;
    @FXML
    private TextField FieldInsertEndTime;
    @FXML
    private TextField FieldInsertQuota;

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
        String sql = "SELECT * FROM Lesson";
        ResultSet rs = stmt.executeQuery(sql);
        while(rs.next()){
            this.boxItemLessonID.getItems().add(rs.getString("ID"));
        }

        this.boxItemLessonID.setValue(this.FieldLessonIDLabel.getText());
    }


    public void createInsertBox() throws SQLException {
        Statement stmt = mainController.getConn().createStatement();
        String sql = "SELECT * FROM Lesson";
        ResultSet rs = stmt.executeQuery(sql);
        this.boxInsertItemLessonID.getItems().add("--select--");
        while(rs.next()){
            this.boxInsertItemLessonID.getItems().add(rs.getString("ID"));
        }
        this.boxInsertItemLessonID.setValue("--select--");

    }

    public void assignTextLabel(String LessonID, String Day, String StartTime,String EndTime, String Quota) throws SQLException {

        FieldDay.setText(Day);
        FieldStartTime.setText(StartTime);
        FieldEndTime.setText(EndTime);
        if(Quota != null)
            FieldQuota.setText(Quota);

        if(LessonID != null)
            FieldLessonIDLabel.setText(LessonID);

        FieldDayLabel.setText(Day);
        FieldStartTimeLabel.setText(StartTime);
        FieldEndTimeLabel.setText(EndTime);
        if(Quota != null)
            FieldQuotaLabel.setText(Quota);

        this.createBox();
    }

    public void handleClicks(ActionEvent actionEvent) throws IOException, SQLException {
        EnrollTimeQueries enrollTimeSQL = new EnrollTimeQueries(mainController.getConn());
        enrollTimeSQL.setMainController(mainController);
        if(actionEvent.getSource() == btnDelete){
            enrollTimeSQL.deleteEnrollTime(this.FieldLessonIDLabel.getText(), this.FieldDay.getText());
            if(enrollTimeSQL.getActionForCancel() == 0){
                mainController.getPnEnrollTimeItems().getChildren().remove(this.itemC);
                mainController.getNorEnrollTime().setText(String.valueOf(Integer.valueOf(mainController.getNorEnrollTime().getText())-1));

            }
        }


        if(actionEvent.getSource() == btnAddInsert) {
            stackAddInsert.toFront();
        }
        if(actionEvent.getSource() == btnSaveInsert){

            if(!boxInsertItemLessonID.getValue().equals("--select--") ) {
                String pattern = "([0-1][0-9]|[2][0-3]):[0-5][0-9]:[0-5][0-9]";
                Pattern patternObject = Pattern.compile(pattern);

                // Now create matcher object.
                Matcher matcher1 = patternObject.matcher(FieldInsertStartTime.getText());
                Matcher matcher2 = patternObject.matcher(FieldInsertEndTime.getText());
                if (matcher1.find() && matcher2.find()) {
                    enrollTimeSQL.insertEnrollTime(boxInsertItemLessonID.getValue(), FieldInsertDay.getText(), FieldInsertStartTime.getText(), FieldInsertEndTime.getText(), FieldInsertQuota.getText());
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../ItemsFXML/EnrollTimeItem.fxml"));
                    Node node = fxmlLoader.load();
                    EnrollTimeItemController enrollTimeItemController = fxmlLoader.getController();

                    enrollTimeItemController.setMainController(this.mainController);
                    enrollTimeItemController.assignTextLabel(boxInsertItemLessonID.getValue(), FieldInsertDay.getText(), FieldInsertStartTime.getText(), FieldInsertEndTime.getText(), FieldInsertQuota.getText());

                    mainController.getPnEnrollTimeItems().getChildren().add(node);
                    FieldInsertDay.setText("");
                    FieldInsertStartTime.setText("");
                    FieldInsertEndTime.setText("");
                    FieldInsertQuota.setText("");
                    stackNormalInsert.toFront();
                    mainController.getNorEnrollTime().setText(String.valueOf(Integer.valueOf(mainController.getNorEnrollTime().getText()) + 1));
                    this.boxInsertItemLessonID.setValue("--select--");
                } else {
                    JOptionPane.showMessageDialog(null, "The Start Time or End Time is not valid !\nExample: 'HH:MM:SS' ");
                }
            }
            else
                JOptionPane.showMessageDialog(null, "You should choose something for all choice boxes!");


        }


        if(actionEvent.getSource() == btnCancelInsert){
            this.boxInsertItemLessonID.setValue("--select--");
            stackNormalInsert.toFront();
        }


        if(actionEvent.getSource() == btnCancel){

            boxItemLessonID.setValue(FieldLessonIDLabel.getText());
            FieldDay.setText(FieldDayLabel.getText());
            FieldStartTime.setText(FieldStartTimeLabel.getText());
            FieldEndTime.setText(FieldEndTimeLabel.getText());
            FieldQuota.setText(FieldQuotaLabel.getText());
            stackNormal.toFront();
        }


        if(actionEvent.getSource() == btnEdit){
            stackEdit.toFront();
        }

        if(actionEvent.getSource() == btnSave){
            boolean isUpdate = false;
            String oldFieldLessonID = FieldLessonIDLabel.getText();
            String oldFieldDay = FieldDayLabel.getText();
            String oldFieldStartTime = FieldStartTimeLabel.getText();
            String oldFieldEndTime = FieldEndTimeLabel.getText();
            String oldFieldQuota = FieldQuotaLabel.getText();

            String newFieldLessonId = boxItemLessonID.getValue();
            String newFieldDay = FieldDay.getText();
            String newFieldStartTime = FieldStartTime.getText();
            String newFieldEndTime = FieldEndTime.getText();
            String newFieldQuota = FieldQuota.getText();


            if(( (oldFieldLessonID != null && newFieldLessonId == null) || (oldFieldLessonID == null && newFieldLessonId != null))
                    ? true
                    : (oldFieldLessonID == null && newFieldLessonId == null)
                    ? false
                    : !oldFieldLessonID.equals(newFieldLessonId)){
                enrollTimeSQL.updateLessonID(oldFieldLessonID,oldFieldDay, newFieldLessonId);
                FieldLessonIDLabel.setText(newFieldLessonId);
                  isUpdate = true;
                stackNormal.toFront();
            }
            if(( (oldFieldDay != null && newFieldDay == null) || (oldFieldDay == null && newFieldDay != null))
                    ? true
                    : (oldFieldDay == null && newFieldDay == null)
                    ? false
                    : !oldFieldDay.equals(newFieldDay)){
                enrollTimeSQL.updateDay(newFieldLessonId,oldFieldDay,newFieldDay);
                FieldDayLabel.setText(newFieldDay);
                 stackNormal.toFront();
                isUpdate = true;
            }


            if(( (oldFieldStartTime != null && newFieldStartTime == null) || (oldFieldStartTime == null && newFieldStartTime != null))
                    ? true
                    : (oldFieldStartTime == null && newFieldStartTime == null)
                    ? false
                    : !oldFieldStartTime.equals(newFieldStartTime)){


                String pattern = "([0-1][0-9]|[2][0-3]):[0-5][0-9]:[0-5][0-9]";
                Pattern patternObject = Pattern.compile(pattern);

                // Now create matcher object.
                Matcher matcher1 = patternObject.matcher(FieldStartTimeLabel.getText());

                if (matcher1.find( ) ){
                    enrollTimeSQL.updateStartTime(newFieldLessonId,newFieldDay, newFieldStartTime);
                    FieldStartTimeLabel.setText(newFieldStartTime);
                     stackNormal.toFront();
                    isUpdate = true;
                }

                else {
                    JOptionPane.showMessageDialog(null, "The Start Time is not valid !\nExample: 'HH:MM:SS' ");
                }

            }
            if(( (oldFieldEndTime != null && newFieldEndTime == null) || (oldFieldEndTime == null && newFieldEndTime != null))
                    ? true
                    : (oldFieldEndTime == null && newFieldEndTime == null)
                    ? false
                    : !oldFieldEndTime.equals(newFieldEndTime)){

                String pattern = "([0-1][0-9]|[2][0-3]):[0-5][0-9]:[0-5][0-9]";
                Pattern patternObject = Pattern.compile(pattern);

                // Now create matcher object.
                Matcher matcher2 = patternObject.matcher(FieldEndTimeLabel.getText());
                if (matcher2.find( )){
                    enrollTimeSQL.updateEndTime(newFieldLessonId,newFieldDay, newFieldEndTime);
                    FieldEndTimeLabel.setText(newFieldEndTime);
                     stackNormal.toFront();
                    isUpdate = true;
                }

                else {
                    JOptionPane.showMessageDialog(null, "The End Time is not valid !\nExample: 'HH:MM:SS' ");
                }


            }
            if(( (oldFieldQuota != null && newFieldQuota == null) || (oldFieldQuota == null && newFieldQuota != null))
                    ? true
                    : (oldFieldQuota == null && newFieldQuota == null)
                    ? false
                    : !oldFieldQuota.equals(newFieldQuota)){
                enrollTimeSQL.updateQuota(newFieldLessonId,newFieldDay,newFieldQuota);
                FieldQuotaLabel.setText(newFieldQuota);
                 //showConfirmDialog(null, "Are you sure you want to delete this item?");
                stackNormal.toFront();
                isUpdate = true;
            }

            if(isUpdate){
                JOptionPane.showMessageDialog(null, "Saved ");
            }

        }
    }

}

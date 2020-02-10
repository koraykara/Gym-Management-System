package Controllers;

import Queries.LessonQueries;
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

public class LessonItemController implements Initializable { //Name, TrainerID, ProfessionName, Price(float)
    @FXML
    private HBox itemC;

    @FXML
    private Label FieldIDLabel;
    @FXML
    private Label FieldNameLabel; // ??????!!!!!!! UsernameID is here
    @FXML
    private Label FieldTrainerIDLabel;
    @FXML
    private Label FieldProfessionNameLabel;
    @FXML
    private Label FieldPriceLabel;

    @FXML
    private TextField FieldID;
    @FXML
    private TextField FieldName;
    @FXML
    private ChoiceBox<String> boxItemTrainerID;
    @FXML
    private ChoiceBox<String> boxItemProfessionName;
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
    private ChoiceBox<String> boxInsertItemTrainerID;
    @FXML
    private ChoiceBox<String>  boxInsertItemProfessionName;
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

    private void createBox() throws SQLException {
        Statement stmt = mainController.getConn().createStatement();
        String sql = "SELECT * FROM Trainer";
        ResultSet rs = stmt.executeQuery(sql);
        while(rs.next()){
            this.boxItemTrainerID.getItems().add(rs.getString("UsernameID"));
        }

        this.boxItemTrainerID.setValue(this.FieldTrainerIDLabel.getText());

        stmt = mainController.getConn().createStatement();
        sql = "SELECT * FROM Profession";
        rs = stmt.executeQuery(sql);
        while(rs.next()){
            this.boxItemProfessionName.getItems().add(rs.getString("Name"));
        }
        this.boxItemProfessionName.setValue(this.FieldProfessionNameLabel.getText());

    }


    public void createInsertBox() throws SQLException {
        Statement stmt = mainController.getConn().createStatement();
        String sql = "SELECT * FROM Trainer";
        ResultSet rs = stmt.executeQuery(sql);
        this.boxInsertItemTrainerID.getItems().add("--select--");
        while(rs.next()){
            this.boxInsertItemTrainerID.getItems().add(rs.getString("UsernameID"));
        }
        this.boxInsertItemTrainerID.setValue("--select--");

        stmt = mainController.getConn().createStatement();
        sql = "SELECT * FROM Profession";
        rs = stmt.executeQuery(sql);
        this.boxInsertItemProfessionName.getItems().add("--select--");
        while(rs.next()){
            this.boxInsertItemProfessionName.getItems().add(rs.getString("Name"));
        }
        this.boxInsertItemProfessionName.setValue("--select--");
    }

    //Name, TrainerID, ProfessionName, Price(float)
    public void assignTextLabel(String ID ,String Name, String TrainerID , String ProfessionName, String Price) throws SQLException {
        if(ID != null)
            FieldID.setText(ID );
        FieldName.setText(Name);
        if(Price != null)
            FieldPrice.setText(Price );

        if(ID != null)
            FieldIDLabel.setText(ID );
        FieldNameLabel.setText(Name);
        FieldTrainerIDLabel.setText(TrainerID);
        FieldProfessionNameLabel.setText(ProfessionName);
        if(Price != null)
            FieldPriceLabel.setText(Price );

        this.createBox();
    }


    public void handleClicks(ActionEvent actionEvent) throws IOException, SQLException {
        LessonQueries lessonSQL = new LessonQueries(mainController.getConn());
        lessonSQL.setMainController(mainController);
        if(actionEvent.getSource() == btnDelete){
            lessonSQL.deleteLesson(this.FieldName.getText());
            if(lessonSQL.getActionForCancel() == 0){
                mainController.getPnLessonItems().getChildren().remove(this.itemC);
                mainController.getNorLesson().setText(String.valueOf(Integer.valueOf(mainController.getNorLesson().getText())-1));

            }
        }

        if(actionEvent.getSource() == btnAddInsert) {
            stackAddInsert.toFront();
        }

        if(actionEvent.getSource() == btnSaveInsert){

            if(!boxInsertItemTrainerID.getValue().equals("--select--")
                    && !boxInsertItemProfessionName.getValue().equals("--select--")) {
                int insertID = lessonSQL.insertLesson(FieldInsertName.getText(), boxInsertItemTrainerID.getValue(), boxInsertItemProfessionName.getValue(), FieldInsertPrice.getText());

                if (insertID != 0) {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../ItemsFXML/LessonItem.fxml"));
                    Node node = fxmlLoader.load();
                    LessonItemController lessonItemController = fxmlLoader.getController();



                    lessonItemController.setMainController(this.mainController);
                    lessonItemController.assignTextLabel(String.valueOf(insertID), FieldInsertName.getText(), boxInsertItemTrainerID.getValue(), boxInsertItemProfessionName.getValue(), FieldInsertPrice.getText());

                    mainController.getPnLessonItems().getChildren().add(node);
                    stackNormalInsert.toFront();
                    mainController.getNorLesson().setText(String.valueOf(Integer.valueOf(mainController.getNorLesson().getText()) + 1));
                    FieldInsertName.setText("");
                    FieldInsertPrice.setText("");
                    this.boxInsertItemTrainerID.setValue("--select--");
                    this.boxInsertItemProfessionName.setValue("--select--");
                }
            }

            else if(boxInsertItemTrainerID.getValue().equals("--select--")
                    && !boxInsertItemProfessionName.getValue().equals("--select--")) {
                int insertID = lessonSQL.insertLesson(FieldInsertName.getText(), null, boxInsertItemProfessionName.getValue(), FieldInsertPrice.getText());

                if (insertID != 0) {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../ItemsFXML/LessonItem.fxml"));
                    Node node = fxmlLoader.load();
                    LessonItemController lessonItemController = fxmlLoader.getController();

                    FieldInsertName.setText("");
                    FieldInsertPrice.setText("");

                    lessonItemController.setMainController(this.mainController);
                    lessonItemController.assignTextLabel(String.valueOf(insertID), FieldInsertName.getText(), "", boxInsertItemProfessionName.getValue(), FieldInsertPrice.getText());

                    mainController.getPnLessonItems().getChildren().add(node);
                    stackNormalInsert.toFront();
                    mainController.getNorLesson().setText(String.valueOf(Integer.valueOf(mainController.getNorLesson().getText()) + 1));
                    this.boxInsertItemTrainerID.setValue("--select--");
                    this.boxInsertItemProfessionName.setValue("--select--");
                }
            }


            else if(!boxInsertItemTrainerID.getValue().equals("--select--")
                    && boxInsertItemProfessionName.getValue().equals("--select--")) {
                int insertID = lessonSQL.insertLesson(FieldInsertName.getText(), boxInsertItemTrainerID.getValue(), null, FieldInsertPrice.getText());

                if (insertID != 0) {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../ItemsFXML/LessonItem.fxml"));
                    Node node = fxmlLoader.load();
                    LessonItemController lessonItemController = fxmlLoader.getController();

                    FieldInsertName.setText("");
                    FieldInsertPrice.setText("");

                    lessonItemController.setMainController(this.mainController);
                    lessonItemController.assignTextLabel(String.valueOf(insertID), FieldInsertName.getText(), boxInsertItemTrainerID.getValue(), "", FieldInsertPrice.getText());

                    mainController.getPnLessonItems().getChildren().add(node);
                    stackNormalInsert.toFront();
                    mainController.getNorLesson().setText(String.valueOf(Integer.valueOf(mainController.getNorLesson().getText()) + 1));
                    this.boxInsertItemTrainerID.setValue("--select--");
                    this.boxInsertItemProfessionName.setValue("--select--");
                }
            }


            else if(boxInsertItemTrainerID.getValue().equals("--select--")
                    && boxInsertItemProfessionName.getValue().equals("--select--")) {
                System.out.println(">"+FieldInsertName.getText()+"<");
                int insertID = lessonSQL.insertLesson(FieldInsertName.getText(), null, null, FieldInsertPrice.getText());

                if (insertID != 0) {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../ItemsFXML/LessonItem.fxml"));
                    Node node = fxmlLoader.load();
                    LessonItemController lessonItemController = fxmlLoader.getController();


                    lessonItemController.setMainController(this.mainController);
                    lessonItemController.assignTextLabel(String.valueOf(insertID), FieldInsertName.getText(), null, null, FieldInsertPrice.getText());

                    mainController.getPnLessonItems().getChildren().add(node);
                    stackNormalInsert.toFront();
                    mainController.getNorLesson().setText(String.valueOf(Integer.valueOf(mainController.getNorLesson().getText()) + 1));

                    FieldInsertName.setText("");
                    FieldInsertPrice.setText("");
                    this.boxInsertItemTrainerID.setValue("--select--");
                    this.boxInsertItemProfessionName.setValue("--select--");
                }
            }


        }


        if(actionEvent.getSource() == btnCancelInsert){
            this.boxInsertItemTrainerID.setValue("--select--");
            this.boxInsertItemProfessionName.setValue("--select--");
            stackNormalInsert.toFront();
        }

        if(actionEvent.getSource() == btnCancel){
            FieldID.setText(FieldIDLabel.getText());
            FieldName.setText(FieldNameLabel.getText());
            boxItemTrainerID.setValue((FieldTrainerIDLabel.getText()));
            boxItemProfessionName.setValue(FieldProfessionNameLabel.getText());
            FieldPrice.setText(FieldPriceLabel.getText());
            stackNormal.toFront();
        }

        if(actionEvent.getSource() == btnEdit){
            stackEdit.toFront();
        }

        if(actionEvent.getSource() == btnSave){
            String oldFieldID = FieldIDLabel.getText();
            String oldFieldName = FieldNameLabel.getText();
            String oldFieldTrainerID= FieldTrainerIDLabel.getText();
            String oldFieldProfessionName = FieldProfessionNameLabel.getText();
            String oldFieldPrice= FieldPriceLabel.getText();

            String newFieldID = FieldID.getText();
            String newFieldName = FieldName.getText();
            String newFieldTrainerID = boxItemTrainerID.getValue();
            String newFieldProfessionName = boxItemProfessionName.getValue();
            String newFieldPrice = FieldPrice.getText();


            if(( (oldFieldID != null && newFieldID == null) || (oldFieldID == null && newFieldID != null))
                    ? true
                    : (oldFieldID == null && newFieldID == null)
                    ? false
                    : !oldFieldID.equals(newFieldID)){
                lessonSQL.updateLessonID(oldFieldID,newFieldID);
                FieldIDLabel.setText(newFieldID);
            }
            if(( (oldFieldName != null && newFieldName == null) || (oldFieldName == null && newFieldName != null))
                    ? true
                    : (oldFieldName == null && newFieldName == null)
                    ? false
                    : !oldFieldName.equals(newFieldName)){
                lessonSQL.updateLessonName(newFieldID, newFieldName);
                FieldNameLabel.setText(newFieldName);
            }

            if(( (oldFieldTrainerID != null && newFieldTrainerID == null) || (oldFieldTrainerID == null && newFieldTrainerID != null))
                    ? true
                    : (oldFieldTrainerID == null && newFieldTrainerID == null)
                    ? false
                    : !oldFieldTrainerID.equals(newFieldTrainerID)){
                lessonSQL.updateTrainerID(newFieldID,newFieldTrainerID);
                FieldTrainerIDLabel.setText(newFieldTrainerID);
            }

            if(( (oldFieldProfessionName != null && newFieldProfessionName == null) || (oldFieldProfessionName == null && newFieldProfessionName != null))
                    ? true
                    : (oldFieldProfessionName == null && newFieldProfessionName == null)
                    ? false
                    : !oldFieldProfessionName.equals(newFieldProfessionName)){

                lessonSQL.updateProfessionName(newFieldID,newFieldProfessionName);
                FieldProfessionNameLabel.setText(newFieldProfessionName);
            }


            if(( (oldFieldPrice != null && newFieldPrice == null) || (oldFieldPrice == null && newFieldPrice != null))
                    ? true
                    : (oldFieldPrice == null && newFieldPrice == null)
                    ? false
                    : !oldFieldPrice.equals(newFieldPrice)){
                lessonSQL.updateLessonPrice(newFieldID,newFieldPrice);
                FieldPriceLabel.setText(newFieldPrice);
            }
            JOptionPane.showMessageDialog(null, "Saved "); //showConfirmDialog(null, "Are you sure you want to delete this item?");
            stackNormal.toFront();
        }
    }
}

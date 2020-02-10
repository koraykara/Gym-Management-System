package Controllers;

import Queries.UsedInQueries;
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

public class UsedInItemController implements Initializable {
    @FXML
    private HBox itemC;
    @FXML
    private Label FieldSportToolsNameLabel; // ??????!!!!!!! UsernameID is here
    @FXML
    private Label FieldLessonIDLabel;
    @FXML
    private Label FieldQuantityLabel;

    @FXML
    private ChoiceBox<String> boxItemSportToolsName;
    @FXML
    private ChoiceBox<String> boxItemLessonID;
    @FXML
    private TextField FieldQuantity;

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
    private ChoiceBox<String> boxInsertItemSportToolsName;
    @FXML
    private ChoiceBox<String> boxInsertItemLessonID;
    @FXML
    private TextField FieldInsertQuantity;

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
        String sql = "SELECT * FROM Sport_Tools";
        ResultSet rs = stmt.executeQuery(sql);
        while(rs.next()){
            this.boxItemSportToolsName.getItems().add(rs.getString("Name"));
        }
        this.boxItemSportToolsName.setValue(this.FieldSportToolsNameLabel.getText());

        stmt = mainController.getConn().createStatement();
        sql = "SELECT * FROM Lesson";
        rs = stmt.executeQuery(sql);
        while(rs.next()){
            this.boxItemLessonID.getItems().add(rs.getString("ID"));
        }
        this.boxItemLessonID.setValue(this.FieldLessonIDLabel.getText());
    }


    public void createInsertBox() throws SQLException {
        Statement stmt = mainController.getConn().createStatement();
        String sql = "SELECT * FROM Sport_Tools";
        ResultSet rs = stmt.executeQuery(sql);
        this.boxInsertItemSportToolsName.getItems().add("--select--");
        while(rs.next()){
            this.boxInsertItemSportToolsName.getItems().add(rs.getString("Name"));
        }
        this.boxInsertItemSportToolsName.setValue("--select--");


        stmt = mainController.getConn().createStatement();
        sql = "SELECT * FROM Lesson";
        rs = stmt.executeQuery(sql);
        this.boxInsertItemLessonID.getItems().add("--select--");
        while(rs.next()){
            this.boxInsertItemLessonID.getItems().add(rs.getString("ID"));
        }
        this.boxInsertItemLessonID.setValue("--select--");
    }


    public void assignTextLabel(String SportToolsName,String LessonID, String Quantity) throws SQLException {

            FieldQuantity.setText(Quantity );

            FieldSportToolsNameLabel.setText(SportToolsName);

            FieldLessonIDLabel.setText(LessonID );

            FieldQuantityLabel.setText(Quantity );

            this.createBox();

    }

    public void handleClicks(ActionEvent actionEvent) throws IOException, SQLException {
        UsedInQueries usedInQueries = new UsedInQueries(mainController.getConn());

        if(actionEvent.getSource() == btnDelete){
            usedInQueries.deleteUsedIn(this.FieldSportToolsNameLabel.getText(), this.FieldLessonIDLabel.getText());
            if(usedInQueries.getActionForCancel() == 0){
                mainController.getPnUsedInItems().getChildren().remove(this.itemC);
                mainController.getNorUsedIn().setText(String.valueOf(Integer.valueOf(mainController.getNorUsedIn().getText())-1));

            }
        }

        if(actionEvent.getSource() == btnAddInsert) {
            stackAddInsert.toFront();
        }

        if(actionEvent.getSource() == btnSaveInsert){

            if(!boxInsertItemSportToolsName.getValue().equals("--select--")
                    && !boxInsertItemLessonID.getValue().equals("--select--")) {
                int insertID = usedInQueries.insertUsedIn(boxInsertItemSportToolsName.getValue(), boxInsertItemLessonID.getValue(), FieldInsertQuantity.getText());

                if (insertID != 0) {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../ItemsFXML/UsedInItem.fxml"));
                    Node node = fxmlLoader.load();
                    UsedInItemController usedInItemController = fxmlLoader.getController();

                    usedInItemController.setMainController(this.mainController);
                    usedInItemController.assignTextLabel(boxInsertItemSportToolsName.getValue(), boxInsertItemLessonID.getValue(), FieldInsertQuantity.getText());

                    FieldInsertQuantity.setText("");


                    mainController.getPnUsedInItems().getChildren().add(node);
                    stackNormalInsert.toFront();
                    mainController.getNorUsedIn().setText(String.valueOf(Integer.valueOf(mainController.getNorUsedIn().getText()) + 1));
                    this.boxInsertItemSportToolsName.setValue("--select--");
                    this.boxInsertItemLessonID.setValue("--select--");
                }
            }
            else
                JOptionPane.showMessageDialog(null, "You should choose something for all choice boxes!");

        }


        if(actionEvent.getSource() == btnCancelInsert){
            this.boxInsertItemSportToolsName.setValue("--select--");
            this.boxInsertItemLessonID.setValue("--select--");
            stackNormalInsert.toFront();
        }

        if(actionEvent.getSource() == btnCancel){
            boxItemSportToolsName.setValue(FieldSportToolsNameLabel.getText());
            boxItemLessonID.setValue(FieldLessonIDLabel.getText() );

            FieldQuantity.setText(FieldQuantityLabel.getText() );
            stackNormal.toFront();
        }


        if(actionEvent.getSource() == btnEdit){
            stackEdit.toFront();
        }

        if(actionEvent.getSource() == btnSave){
            String oldFieldSportToolsName = FieldSportToolsNameLabel.getText();
            String oldFieldLessonID = FieldLessonIDLabel.getText();
            String oldFieldQuantity = FieldQuantityLabel.getText();

            String newFieldSportToolsName = boxItemSportToolsName.getValue();
            String newFieldLessonID = boxItemLessonID.getValue();
            String newFieldQuantity = FieldQuantity.getText();

            if(( (oldFieldSportToolsName != null && newFieldSportToolsName == null) || (oldFieldSportToolsName == null && newFieldSportToolsName != null))
                    ? true
                    : (oldFieldSportToolsName == null && newFieldSportToolsName == null)
                    ? false
                    : !oldFieldSportToolsName.equals(newFieldSportToolsName))
            {
                usedInQueries.updateSportToolsName(oldFieldSportToolsName,newFieldLessonID, newFieldSportToolsName);
                FieldSportToolsNameLabel.setText(newFieldSportToolsName);
            }

            if(( (oldFieldLessonID != null && newFieldLessonID == null) || (oldFieldLessonID == null && newFieldLessonID != null))
                    ? true
                    : (oldFieldLessonID == null && newFieldLessonID == null)
                    ? false
                    : !oldFieldLessonID.equals(newFieldLessonID))
            {
                usedInQueries.updateLessonID(oldFieldLessonID,newFieldSportToolsName,newFieldLessonID);
                FieldLessonIDLabel.setText(newFieldLessonID);
            }

            if(( (oldFieldQuantity != null && newFieldQuantity == null) || (oldFieldQuantity == null && newFieldQuantity != null))
                    ? true
                    : (oldFieldQuantity == null && newFieldQuantity == null)
                    ? false
                    : !oldFieldQuantity.equals(newFieldQuantity))
            {
                usedInQueries.updateQuantity(newFieldSportToolsName,newFieldLessonID, newFieldQuantity);
                FieldQuantityLabel.setText(newFieldQuantity);
            }
            JOptionPane.showMessageDialog(null, "Saved "); //showConfirmDialog(null, "Are you sure you want to delete this item?");
            stackNormal.toFront();
            System.out.println("Updated");
        }
    }
}

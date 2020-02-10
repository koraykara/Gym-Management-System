package Controllers;

import Queries.NotFitQueries;
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

public class NotFitItemController implements Initializable {
    @FXML
    private HBox itemC;
    @FXML
    private Label FieldPyhsicalAilmentNameLabel; // ??????!!!!!!! UsernameID is here
    @FXML
    private Label FieldProgramIDLabel;

    @FXML
    private ChoiceBox<String> boxItemPyhsicalAilmentName;
    @FXML
    private ChoiceBox<String> boxItemProgramID;

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
    private ChoiceBox<String>  boxInsertItemPyhsicalAilmentName;
    @FXML
    private ChoiceBox<String>  boxInsertItemProgramID;

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
        String sql = "SELECT * FROM physical_ailment";
        ResultSet rs = stmt.executeQuery(sql);
        while(rs.next()){
            this.boxItemPyhsicalAilmentName.getItems().add(rs.getString("Name"));
        }

        this.boxItemPyhsicalAilmentName.setValue(this.FieldPyhsicalAilmentNameLabel.getText());

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
        String sql = "SELECT * FROM physical_ailment";
        ResultSet rs = stmt.executeQuery(sql);
        this.boxInsertItemPyhsicalAilmentName.getItems().add("--select--");
        while(rs.next()){
            this.boxInsertItemPyhsicalAilmentName.getItems().add(rs.getString("Name"));
        }
        this.boxInsertItemPyhsicalAilmentName.setValue("--select--");

        stmt = mainController.getConn().createStatement();
        sql = "SELECT * FROM Program";
        rs = stmt.executeQuery(sql);
        this.boxInsertItemProgramID.getItems().add("--select--");
        while(rs.next()){
            this.boxInsertItemProgramID.getItems().add(rs.getString("ID"));
        }
        this.boxInsertItemProgramID.setValue("--select--");
    }


    public void assignTextLabel(String PyhsicalAilmentName , String programID) throws SQLException {

        FieldPyhsicalAilmentNameLabel.setText(PyhsicalAilmentName);
        FieldProgramIDLabel.setText(programID);
        this.createBox();
    }

    public void handleClicks(ActionEvent actionEvent) throws IOException, SQLException {
        NotFitQueries notFitSQL = new NotFitQueries(mainController.getConn());

        if(actionEvent.getSource() == btnDelete){
            //mainController.getNodes().remove(0);
            notFitSQL.deleteNotFit(this.FieldPyhsicalAilmentNameLabel.getText(), this.FieldProgramIDLabel.getText());
            if(notFitSQL.getActionForCancel() == 0){
                mainController.getPnNotFitItems().getChildren().remove(this.itemC);
                mainController.getNorNotFit().setText(String.valueOf(Integer.valueOf(mainController.getNorNotFit().getText())-1));
            }
        }

        if(actionEvent.getSource() == btnAddInsert) {
            stackAddInsert.toFront();
        }

        if(actionEvent.getSource() == btnSaveInsert){

            int insertID = notFitSQL.insertNotFit(boxInsertItemPyhsicalAilmentName.getValue(), boxInsertItemProgramID.getValue() );

            if(!boxInsertItemPyhsicalAilmentName.getValue().equals("--select--")
                    && !boxInsertItemProgramID.getValue().equals("--select--")) {
                if (insertID != 0) {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../ItemsFXML/NotFitItem.fxml"));
                    Node node = fxmlLoader.load();
                    NotFitItemController notFitItemController = fxmlLoader.getController();


                    notFitItemController.setMainController(this.mainController);
                    notFitItemController.assignTextLabel(boxInsertItemPyhsicalAilmentName.getValue(), boxInsertItemProgramID.getValue());

                    mainController.getPnNotFitItems().getChildren().add(node);
                    stackNormalInsert.toFront();
                    mainController.getNorNotFit().setText(String.valueOf(Integer.valueOf(mainController.getNorNotFit().getText()) + 1));
                    this.boxInsertItemPyhsicalAilmentName.setValue("--select--");
                    this.boxInsertItemProgramID.setValue("--select--");
                }
            }
            else
                JOptionPane.showMessageDialog(null, "You should choose something for all choice boxes!");

        }


        if(actionEvent.getSource() == btnCancelInsert){
            this.boxInsertItemPyhsicalAilmentName.setValue("--select--");
            this.boxInsertItemProgramID.setValue("--select--");
            stackNormalInsert.toFront();
        }

        if(actionEvent.getSource() == btnCancel){
            boxItemPyhsicalAilmentName.setValue(FieldPyhsicalAilmentNameLabel.getText());
            boxItemProgramID.setValue(FieldProgramIDLabel.getText());
            stackNormal.toFront();
        }
        if(actionEvent.getSource() == btnEdit){
            stackEdit.toFront();
        }

        if(actionEvent.getSource() == btnSave){
            String oldFieldPyhsicalAilmentName = FieldPyhsicalAilmentNameLabel.getText();
            String oldFieldProgramID = FieldProgramIDLabel.getText();

            String newFieldPyhsicalAilmentName = boxItemPyhsicalAilmentName.getValue();
            String newFieldProgramID = boxItemProgramID.getValue();

            if(( (oldFieldPyhsicalAilmentName != null && newFieldPyhsicalAilmentName == null) || (oldFieldPyhsicalAilmentName == null && newFieldPyhsicalAilmentName != null))
                    ? true
                    : (oldFieldPyhsicalAilmentName == null && newFieldPyhsicalAilmentName == null)
                    ? false
                    : !oldFieldPyhsicalAilmentName.equals(newFieldPyhsicalAilmentName))
             {
                notFitSQL.updatePyhsicalAilmentName(oldFieldPyhsicalAilmentName,newFieldProgramID, newFieldPyhsicalAilmentName);
                FieldPyhsicalAilmentNameLabel.setText(newFieldPyhsicalAilmentName);
            }
            if(( (oldFieldProgramID != null && newFieldProgramID == null) || (oldFieldProgramID == null && newFieldProgramID != null))
                    ? true
                    : (oldFieldProgramID == null && newFieldProgramID == null)
                    ? false
                    : !oldFieldProgramID.equals(newFieldProgramID))
            {
                notFitSQL.updateProgramID(oldFieldProgramID,newFieldPyhsicalAilmentName,newFieldProgramID);
                FieldProgramIDLabel.setText(newFieldProgramID);
            }
            JOptionPane.showMessageDialog(null, "Saved "); //showConfirmDialog(null, "Are you sure you want to delete this item?");
            stackNormal.toFront();
            System.out.println("Updated");
        }
    }
}

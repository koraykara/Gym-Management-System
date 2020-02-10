package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;


public class LoginController implements Initializable {

    @FXML
    private TextField fieldUsername;
    @FXML
    private TextField fieldPassword;
    @FXML
    private Button loginButton;



    public Parent getLoginRoot() {
        return loginRoot;
    }

    public void setLoginRoot(Parent loginRoot) {
        this.loginRoot = loginRoot;
    }

    private Parent loginRoot;
    private Stage primaryStage;

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public LoginController() {
    }

    private Connection conn;


    public TextField getFieldPassword() {
        return fieldPassword;
    }

    public TextField getFieldUsername() {
        return fieldUsername;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    public void handleClicks(ActionEvent actionEvent) throws SQLException, IOException, NoSuchAlgorithmException {
        // For Main Scene
        FXMLLoader mainFxml = new FXMLLoader(getClass().getResource("../Screens/MainScreen.fxml"));
        Parent mainRoot = mainFxml.load();
        Controller mainController = (Controller) mainFxml.getController();
        conn = mainController.getConn();
        //check username and password

        if (actionEvent.getSource() == this.loginButton) {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(this.getFieldPassword().getText().getBytes());

            byte byteData[] = md.digest();

            //Convert "byteData" to hex String:
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }

            String sql = "SELECT * FROM `System_User`  Where Password = '"+sb.toString()
                    + "' AND "+ " UsernameID = 'a_" +this.getFieldUsername().getText() +"'";
            Statement stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql);


            if(resultSet.next()   ){
                Scene mainScene = new Scene(mainRoot);
                primaryStage.setScene(mainScene);
            }
            else{
                JOptionPane.showMessageDialog(null, "Password or Username is not correct "); //showConfirmDialog(null, "Are you sure you want to delete this item?");

            }
        }
    }
}

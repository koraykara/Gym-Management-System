package MainClass;

import Controllers.Controller;
import Controllers.LoginController;
import com.mysql.cj.protocol.Resultset;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main extends Application {
    private Connection conn;
    private Resultset resultSet;

    @Override
    public void start(Stage primaryStage) throws Exception {
/*        Font.loadFont(Main.class.getResource("../Fonts/Poprock.ttf").toExternalForm(), 10);

        // For Login Scene
        FXMLLoader loginFxml = new FXMLLoader(getClass().getResource("../Screens/LoginScreen.fxml"));
        Parent loginRoot = loginFxml.load( );
        LoginController loginController = (LoginController) loginFxml.getController();

        loginController.setPrimaryStage(primaryStage);
        loginController.setLoginRoot(loginRoot);
        loginController.setPrimaryStage(primaryStage);

        primaryStage.resizableProperty().setValue(Boolean.FALSE);

        Scene loginScene = new Scene(loginRoot);
        loginScene.getStylesheets().add(getClass().getResource("../style.css").toExternalForm());
        primaryStage.setScene(loginScene);
        primaryStage.show();*/

        //silinicek




        FXMLLoader mainFxml = new FXMLLoader(getClass().getResource("../Screens/MainScreen.fxml"));
        Parent mainRoot = mainFxml.load();
        Controller mainController = (Controller) mainFxml.getController();
        conn = mainController.getConn();
        //check username and password
        Scene mainScene = new Scene(mainRoot);
        primaryStage.setScene(mainScene);
        primaryStage.show();

        System.out.println(System.getProperty("java.version"));
 
    }

    public static void main(String[] args) {

        launch(args);
    }

    public Connection getConn() {
        return conn;
    }
}

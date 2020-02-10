package Queries;

import Controllers.Controller;
import Controllers.MakeWithItemController;
import Controllers.UsedInItemController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

public class SportToolQueries {

    private Connection conn;
    private Controller mainController;

    public Controller getMainController() {
        return mainController;
    }

    public void setMainController(Controller mainController) {
        this.mainController = mainController;
    }

    public int getActionForCancel() {
        return actionForCancel;
    }
    public void setActionForCancel(int actionForCancel) {
        this.actionForCancel = actionForCancel;
    }

    private int actionForCancel;

    public SportToolQueries(Connection conn) {
        try{
            this.conn = conn;
        }
        catch (Exception e1){
            e1.printStackTrace();
        }

    }


    public int insertSportTools(String name, String amount, String url
    ){

        String sql = "INSERT INTO Sport_Tools( name,amount,urlimage)"
                +"VALUES(?,"+
                "?,?)";
        PreparedStatement preparedStmt;

        actionForCancel = JOptionPane.showConfirmDialog(null, "Are you sure you want to add this item?");
        if(actionForCancel == 0){
            try{
                preparedStmt = conn.prepareStatement(sql);
                preparedStmt.setString(1,name);
                if(amount.isEmpty())
                    preparedStmt.setNull(2, Types.INTEGER);
                else
                    preparedStmt.setInt(2, Integer.parseInt(amount));

                preparedStmt.setString(3,url);
                preparedStmt.execute();



                // -------------------------------

                for( ;mainController.getPnMakeWithItems().getChildren().size() > 1;){
                    mainController.getPnMakeWithItems().getChildren().remove(1);
                }
                mainController.getNorMakeWith().setText("0");
                sql = "SELECT * FROM Make_With";
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                //STEP 5: Extract data from result set
                int i=0;

                ArrayList<Node> nodesMakeWith = new ArrayList<>();
                FXMLLoader fxmlLoaderToAddInsert = new FXMLLoader(getClass().getResource("../InsertItems/InsertMakeWith.fxml"));

                if(i==0){
                    nodesMakeWith.add(fxmlLoaderToAddInsert.load());
                    MakeWithItemController makeWithItemController = fxmlLoaderToAddInsert.getController();
                    makeWithItemController.setMainController(this.mainController);
                    makeWithItemController.createInsertBox();
                    mainController.getPnMakeWithItems().getChildren().add(nodesMakeWith.get(i));
                }
                while(rs.next()){
                    //Retrieve by column name
                    final int j = i+1;
                    String SportToolsName = rs.getString("SportToolsName");
                    String ExerciseName = rs.getString("ExerciseName");

                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../ItemsFXML/MakeWithItem.fxml"));


                    nodesMakeWith.add(fxmlLoader.load());
                    MakeWithItemController makeWithItemController = (MakeWithItemController) fxmlLoader.getController();

                    makeWithItemController.setMainController(this.mainController);
                    makeWithItemController.assignTextLabel(SportToolsName,ExerciseName);

                    mainController.getPnMakeWithItems().getChildren().add(nodesMakeWith.get(j));
                    i++;
                }
                mainController.getNorMakeWith().setText(String.valueOf(i));



                for( ;mainController.getPnUsedInItems().getChildren().size() > 1;){
                    mainController.getPnUsedInItems().getChildren().remove(1);
                }
                mainController.getNorUsedIn().setText("0");
                sql = "SELECT * FROM Used_In";
                stmt = conn.createStatement();
                rs = stmt.executeQuery(sql);
                //STEP 5: Extract data from result set
                i=0;

                ArrayList<Node> nodesUsedIn = new ArrayList<>();
                FXMLLoader fxmlLoader;
                fxmlLoaderToAddInsert = new FXMLLoader(getClass().getResource("../InsertItems/InsertUsedIn.fxml"));

                if(i==0){
                    nodesUsedIn.add(fxmlLoaderToAddInsert.load());
                    UsedInItemController usedInItemController = fxmlLoaderToAddInsert.getController();
                    usedInItemController.setMainController(this.mainController);
                    usedInItemController.createInsertBox();
                    mainController.getPnUsedInItems().getChildren().add(nodesUsedIn.get(i));
                }
                while(rs.next()){
                    //Retrieve by column name
                    final int j = i+1;
                    String SportToolsName = rs.getString("SportToolsName");
                    String LessonID = rs.getString("LessonID");
                    String Quantity = rs.getString("Quantity");

                    fxmlLoader = new FXMLLoader(getClass().getResource("../ItemsFXML/UsedInItem.fxml"));

                    nodesUsedIn.add(fxmlLoader.load());
                    UsedInItemController usedInItemController = (UsedInItemController) fxmlLoader.getController();

                    usedInItemController.setMainController(this.mainController);
                    usedInItemController.assignTextLabel(SportToolsName,LessonID,Quantity);

                    mainController.getPnUsedInItems().getChildren().add(nodesUsedIn.get(j));
                    i++;
                }
                mainController.getNorUsedIn().setText(String.valueOf(i));


                return 1;
            }
            catch (Exception e1){
                e1.printStackTrace();
            }
        }
        return 0;
    }


    public void deleteSportTool(String Name){

        String sql = "delete from Sport_Tools where Name = ?"; // ------>> duzelttim
        PreparedStatement preparedStmt; // ------>> EKLEDIM

        actionForCancel = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this item?");
        if(actionForCancel == 0){
            try{
                preparedStmt = conn.prepareStatement(sql); // ------>> EKLEDIM
                preparedStmt.setString(1, Name);
                preparedStmt.execute();

                // -------------------------------

                for( ;mainController.getPnUsedInItems().getChildren().size() > 1;){
                    mainController.getPnUsedInItems().getChildren().remove(1);
                }
                mainController.getNorUsedIn().setText("0");
                sql = "SELECT * FROM Used_In";
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                //STEP 5: Extract data from result set
                int i=0;

                ArrayList<Node> nodesUsedIn = new ArrayList<>();
                FXMLLoader fxmlLoader;
                FXMLLoader fxmlLoaderToAddInsert;
                fxmlLoaderToAddInsert = new FXMLLoader(getClass().getResource("../InsertItems/InsertUsedIn.fxml"));

                if(i==0){
                    nodesUsedIn.add(fxmlLoaderToAddInsert.load());
                    UsedInItemController usedInItemController = fxmlLoaderToAddInsert.getController();
                    usedInItemController.setMainController(this.mainController);
                    usedInItemController.createInsertBox();
                    mainController.getPnUsedInItems().getChildren().add(nodesUsedIn.get(i));
                }
                while(rs.next()){
                    //Retrieve by column name
                    final int j = i+1;
                    String SportToolsName = rs.getString("SportToolsName");
                    String LessonID = rs.getString("LessonID");
                    String Quantity = rs.getString("Quantity");

                    fxmlLoader = new FXMLLoader(getClass().getResource("../ItemsFXML/UsedInItem.fxml"));

                    nodesUsedIn.add(fxmlLoader.load());
                    UsedInItemController usedInItemController = (UsedInItemController) fxmlLoader.getController();

                    usedInItemController.setMainController(this.mainController);
                    usedInItemController.assignTextLabel(SportToolsName,LessonID,Quantity);

                    mainController.getPnUsedInItems().getChildren().add(nodesUsedIn.get(j));
                    i++;
                }
                mainController.getNorUsedIn().setText(String.valueOf(i));


                // -------------------------------

                for( ;mainController.getPnMakeWithItems().getChildren().size() > 1;){
                    mainController.getPnMakeWithItems().getChildren().remove(1);
                }
                mainController.getNorMakeWith().setText("0");
                sql = "SELECT * FROM Make_With";
                rs = stmt.executeQuery(sql);
                //STEP 5: Extract data from result set
                i=0;

                ArrayList<Node> nodesMakeWith = new ArrayList<>();
                fxmlLoaderToAddInsert = new FXMLLoader(getClass().getResource("../InsertItems/InsertMakeWith.fxml"));

                if(i==0){
                    nodesMakeWith.add(fxmlLoaderToAddInsert.load());
                    MakeWithItemController makeWithItemController = fxmlLoaderToAddInsert.getController();
                    makeWithItemController.setMainController(this.mainController);
                    makeWithItemController.createInsertBox();
                    mainController.getPnMakeWithItems().getChildren().add(nodesMakeWith.get(i));
                }
                while(rs.next()){
                    //Retrieve by column name
                    final int j = i+1;
                    String SportToolsName = rs.getString("SportToolsName");
                    String ExerciseName = rs.getString("ExerciseName");

                    fxmlLoader = new FXMLLoader(getClass().getResource("../ItemsFXML/MakeWithItem.fxml"));


                    nodesMakeWith.add(fxmlLoader.load());
                    MakeWithItemController makeWithItemController = (MakeWithItemController) fxmlLoader.getController();

                    makeWithItemController.setMainController(this.mainController);
                    makeWithItemController.assignTextLabel(SportToolsName,ExerciseName);

                    mainController.getPnMakeWithItems().getChildren().add(nodesMakeWith.get(j));
                    i++;
                }
                mainController.getNorMakeWith().setText(String.valueOf(i));

            }
            catch (Exception e1){
                e1.printStackTrace();
            }
        }

    }


    public void updateSportToolName(String Name , String newName){
        String sql = "update Sport_Tools set Name = ? where Name = ?";
        PreparedStatement preparedStmt;
        try{
            preparedStmt = conn.prepareStatement(sql);
            preparedStmt.setString(1, newName);
            preparedStmt.setString(2, Name);
            preparedStmt.executeUpdate();
            // -------------------------------

            for( ;mainController.getPnUsedInItems().getChildren().size() > 1;){
                mainController.getPnUsedInItems().getChildren().remove(1);
            }
            mainController.getNorUsedIn().setText("0");
            sql = "SELECT * FROM Used_In";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            //STEP 5: Extract data from result set
            int i=0;

            ArrayList<Node> nodesUsedIn = new ArrayList<>();
            FXMLLoader fxmlLoader;
            FXMLLoader fxmlLoaderToAddInsert;
            fxmlLoaderToAddInsert = new FXMLLoader(getClass().getResource("../InsertItems/InsertUsedIn.fxml"));

            if(i==0){
                nodesUsedIn.add(fxmlLoaderToAddInsert.load());
                UsedInItemController usedInItemController = fxmlLoaderToAddInsert.getController();
                usedInItemController.setMainController(this.mainController);
                usedInItemController.createInsertBox();
                mainController.getPnUsedInItems().getChildren().add(nodesUsedIn.get(i));
            }
            while(rs.next()){
                //Retrieve by column name
                final int j = i+1;
                String SportToolsName = rs.getString("SportToolsName");
                String LessonID = rs.getString("LessonID");
                String Quantity = rs.getString("Quantity");

                fxmlLoader = new FXMLLoader(getClass().getResource("../ItemsFXML/UsedInItem.fxml"));

                nodesUsedIn.add(fxmlLoader.load());
                UsedInItemController usedInItemController = (UsedInItemController) fxmlLoader.getController();

                usedInItemController.setMainController(this.mainController);
                usedInItemController.assignTextLabel(SportToolsName,LessonID,Quantity);

                mainController.getPnUsedInItems().getChildren().add(nodesUsedIn.get(j));
                i++;
            }
            mainController.getNorUsedIn().setText(String.valueOf(i));



            // -------------------------------

            for( ;mainController.getPnMakeWithItems().getChildren().size() > 1;){
                mainController.getPnMakeWithItems().getChildren().remove(1);
            }
            mainController.getNorMakeWith().setText("0");
            sql = "SELECT * FROM Make_With";
            rs = stmt.executeQuery(sql);
            //STEP 5: Extract data from result set
            i=0;

            ArrayList<Node> nodesMakeWith = new ArrayList<>();
            fxmlLoaderToAddInsert = new FXMLLoader(getClass().getResource("../InsertItems/InsertMakeWith.fxml"));

            if(i==0){
                nodesMakeWith.add(fxmlLoaderToAddInsert.load());
                MakeWithItemController makeWithItemController = fxmlLoaderToAddInsert.getController();
                makeWithItemController.setMainController(this.mainController);
                makeWithItemController.createInsertBox();
                mainController.getPnMakeWithItems().getChildren().add(nodesMakeWith.get(i));
            }
            while(rs.next()){
                //Retrieve by column name
                final int j = i+1;
                String SportToolsName = rs.getString("SportToolsName");
                String ExerciseName = rs.getString("ExerciseName");

                fxmlLoader = new FXMLLoader(getClass().getResource("../ItemsFXML/MakeWithItem.fxml"));


                nodesMakeWith.add(fxmlLoader.load());
                MakeWithItemController makeWithItemController = (MakeWithItemController) fxmlLoader.getController();

                makeWithItemController.setMainController(this.mainController);
                makeWithItemController.assignTextLabel(SportToolsName,ExerciseName);

                mainController.getPnMakeWithItems().getChildren().add(nodesMakeWith.get(j));
                i++;
            }
            mainController.getNorMakeWith().setText(String.valueOf(i));
        }
        catch (Exception e1){
            e1.printStackTrace();
        }
    }

    public void updateSportToolAmount(String Name, String Amount ){
        String sql = "update Sport_Tools set Amount = ? where Name = ?";
        PreparedStatement preparedStmt;
        try{
            preparedStmt = conn.prepareStatement(sql);
            if(Amount.isEmpty())
                preparedStmt.setNull(1, Types.INTEGER);
            else
                preparedStmt.setInt(1, Integer.parseInt(Amount));

            preparedStmt.setString(2, Name);
            preparedStmt.executeUpdate();

        }
        catch (Exception e1){
            e1.printStackTrace();
        }
    }
    public void updateSportToolURLImage(String Name, String urlImage ){
        String sql = "update Sport_Tools set urlImage = ? where Name = ?";
        PreparedStatement preparedStmt;
        try{
            preparedStmt = conn.prepareStatement(sql);
            preparedStmt.setString(1, urlImage);
            preparedStmt.setString(2, Name);
            preparedStmt.executeUpdate();

        }
        catch (Exception e1){
            e1.printStackTrace();
        }
    }



}

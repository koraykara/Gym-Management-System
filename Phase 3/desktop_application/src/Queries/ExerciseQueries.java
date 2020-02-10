package Queries;

import Controllers.ConsistOfItemController;
import Controllers.Controller;
import Controllers.MakeWithItemController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

public class ExerciseQueries {
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

    public ExerciseQueries(Connection conn) {
        try{
            this.conn = conn;
        }
        catch (Exception e1){
            e1.printStackTrace();
        }

    }
    public int insertExercise(String Name , String Set , String RepetitionPerSet
    ){

        String sql = "INSERT INTO Exercise( `Name`, `Set`, RepetitionPerSet )"
                +" VALUES(?,?,?)";
        PreparedStatement preparedStmt;

        actionForCancel = JOptionPane.showConfirmDialog(null, "Are you sure you want to add this item?");
        if(actionForCancel == 0){
            try{
                preparedStmt = conn.prepareStatement(sql);
                preparedStmt.setString(1, Name);
                if(Set.isEmpty())
                    preparedStmt.setNull(2, Types.INTEGER);
                else
                    preparedStmt.setInt(2, Integer.parseInt(Set));
                if(RepetitionPerSet.isEmpty())
                    preparedStmt.setNull(3, Types.INTEGER);
                else
                    preparedStmt.setInt(3, Integer.parseInt(RepetitionPerSet));
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
                return 1;
            }
            catch (Exception e1){
                e1.printStackTrace();
            }
        }
        return 0;
    }
    public void deleteExercise(String FieldName){

        String sql = "delete from Exercise where `Name` = ?"; // ------>> duzelttim
        PreparedStatement preparedStmt; // ------>> EKLEDIM

        actionForCancel = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this item?");
        if(actionForCancel == 0){
            try{
                preparedStmt = conn.prepareStatement(sql); // ------>> EKLEDIM
                preparedStmt.setString(1, FieldName);
                preparedStmt.execute();

                // -------------------------------
                for( ;mainController.getPnConsistOfItems().getChildren().size() > 1;){
                    mainController.getPnConsistOfItems().getChildren().remove(1);
                }
                mainController.getNorConsistOf().setText("0");

                sql = "SELECT * FROM Consist_Of";
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                //STEP 5: Extract data from result set
                int i=0;

                ArrayList<Node> nodesConsistOf = new ArrayList<>();
                FXMLLoader fxmlLoader;
                FXMLLoader fxmlLoaderToAddInsert;
                fxmlLoaderToAddInsert = new FXMLLoader(getClass().getResource("../InsertItems/InsertConsistOf.fxml"));

                if(i==0){
                    nodesConsistOf.add(fxmlLoaderToAddInsert.load());
                    ConsistOfItemController consistOfItemController = fxmlLoaderToAddInsert.getController();
                    consistOfItemController.setMainController(this.mainController);
                    mainController.getPnConsistOfItems().getChildren().add(nodesConsistOf.get(i));
                }

                while(rs.next()){
                    //Retrieve by column name
                    final int j = i+1;
                    String ProgramID = rs.getString("ProgramID");
                    String ExerciseName = rs.getString("ExerciseName");

                    fxmlLoader = new FXMLLoader(getClass().getResource("../ItemsFXML/ConsistOfItem.fxml"));

                    nodesConsistOf.add(fxmlLoader.load());
                    ConsistOfItemController consistOfItemController = (ConsistOfItemController) fxmlLoader.getController();

                    consistOfItemController.setMainController(this.mainController);
                    consistOfItemController.assignTextLabel(ProgramID,ExerciseName);

                    mainController.getPnConsistOfItems().getChildren().add(nodesConsistOf.get(j));
                    i++;
                }
                mainController.getNorConsistOf().setText(String.valueOf(i));



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

    public void updateName(String oldFieldName,String newFieldName){
        String sql = "update Exercise set `Name` = ? where `Name` = ?";
        PreparedStatement preparedStmt;
        try{
            preparedStmt = conn.prepareStatement(sql);
            preparedStmt.setString(1, newFieldName);
            preparedStmt.setString(2, oldFieldName);
            preparedStmt.executeUpdate();

            // -------------------------------
            for( ;mainController.getPnConsistOfItems().getChildren().size() > 1;){
                mainController.getPnConsistOfItems().getChildren().remove(1);
            }
            mainController.getNorConsistOf().setText("0");

            sql = "SELECT * FROM Consist_Of";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            //STEP 5: Extract data from result set
            int i=0;

            ArrayList<Node> nodesConsistOf = new ArrayList<>();
            FXMLLoader fxmlLoader;
            FXMLLoader fxmlLoaderToAddInsert;
            fxmlLoaderToAddInsert = new FXMLLoader(getClass().getResource("../InsertItems/InsertConsistOf.fxml"));

            if(i==0){
                nodesConsistOf.add(fxmlLoaderToAddInsert.load());
                ConsistOfItemController consistOfItemController = fxmlLoaderToAddInsert.getController();
                consistOfItemController.setMainController(this.mainController);
                mainController.getPnConsistOfItems().getChildren().add(nodesConsistOf.get(i));
            }

            while(rs.next()){
                //Retrieve by column name
                final int j = i+1;
                String ProgramID = rs.getString("ProgramID");
                String ExerciseName = rs.getString("ExerciseName");

                fxmlLoader = new FXMLLoader(getClass().getResource("../ItemsFXML/ConsistOfItem.fxml"));

                nodesConsistOf.add(fxmlLoader.load());
                ConsistOfItemController consistOfItemController = (ConsistOfItemController) fxmlLoader.getController();

                consistOfItemController.setMainController(this.mainController);
                consistOfItemController.assignTextLabel(ProgramID,ExerciseName);

                mainController.getPnConsistOfItems().getChildren().add(nodesConsistOf.get(j));
                i++;
            }
            mainController.getNorConsistOf().setText(String.valueOf(i));


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

    public void updateSet(String newFieldName,String newFieldSet){
        String sql = "update Exercise set `Set` = ? where `Name` = ?";
        PreparedStatement preparedStmt;
        try{
            preparedStmt = conn.prepareStatement(sql);
            if(newFieldSet.isEmpty())
                preparedStmt.setNull(1, Types.INTEGER);
            else
                preparedStmt.setInt(1, Integer.parseInt(newFieldSet));
            preparedStmt.setString(2, newFieldName);
            preparedStmt.executeUpdate();

        }
        catch (Exception e1){
            e1.printStackTrace();
        }
    }
    public void updateRepetitionPerSet(String newFieldName,String newFieldRepetitionPerSet){
        String sql = "update Exercise set RepetitionPerSet = ? where `Name` = ?";
        PreparedStatement preparedStmt;
        try{
            preparedStmt = conn.prepareStatement(sql);
            if(newFieldRepetitionPerSet.isEmpty())
                preparedStmt.setNull(1, Types.INTEGER);
            else
                preparedStmt.setInt(1, Integer.parseInt(newFieldRepetitionPerSet));
            preparedStmt.setString(2, newFieldName);
            preparedStmt.executeUpdate();

        }
        catch (Exception e1){
            e1.printStackTrace();
        }
    }
}

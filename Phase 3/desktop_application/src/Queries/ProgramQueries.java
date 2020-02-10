package Queries;

import Controllers.AskedForItemController;
import Controllers.ConsistOfItemController;
import Controllers.Controller;
import Controllers.NotFitItemController;
import com.sun.javafx.image.IntPixelGetter;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

public class ProgramQueries {
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

    public ProgramQueries(Connection conn) {
        try{
            this.conn = conn;
        }
        catch (Exception e1){
            e1.printStackTrace();
        }

    }
    public int insertProgram(String bmi
    ){
        actionForCancel = JOptionPane.showConfirmDialog(null, "Are you sure you want to add this item?");
        if(actionForCancel == 0){
            try{

                if(bmi.isEmpty()){
                    PreparedStatement statement = conn.prepareStatement(String.format(
                            "INSERT INTO Program(`bmi`)" + "VALUES("+null+")"), Statement.RETURN_GENERATED_KEYS);
                    int affectedRows = statement.executeUpdate();
                    ResultSet generatedKeys = statement.getGeneratedKeys();
                    generatedKeys.next();
                    return generatedKeys.getInt(1);
                }


                PreparedStatement statement = conn.prepareStatement(String.format(
                            "INSERT INTO Program(`bmi`)" + "VALUES(%.1f)", Float.parseFloat(bmi)), Statement.RETURN_GENERATED_KEYS);
                int affectedRows = statement.executeUpdate();
                ResultSet generatedKeys = statement.getGeneratedKeys();
                generatedKeys.next();



                // -------------------------------

                for( ;mainController.getPnAskedForItems().getChildren().size() > 1;){
                    mainController.getPnAskedForItems().getChildren().remove(1);
                }
                mainController.getNorAskedFor().setText("0");
                Statement stmt = conn.createStatement();
                String sql = "SELECT * FROM Asked_For";
                ResultSet rs = stmt.executeQuery(sql);
                //STEP 5: Extract data from result set
                int i=0;
                FXMLLoader fxmlLoader;
                FXMLLoader fxmlLoaderToAddInsert;
                ArrayList<Node> nodesAsked_For = new ArrayList<>();
                fxmlLoaderToAddInsert = new FXMLLoader(getClass().getResource("../InsertItems/InsertAskedFor.fxml"));

                if(i==0){
                    nodesAsked_For.add(fxmlLoaderToAddInsert.load());
                    AskedForItemController askedForItemController = fxmlLoaderToAddInsert.getController();
                    askedForItemController.setMainController(this.mainController);
                    askedForItemController.createInsertBox();
                    mainController.getPnAskedForItems().getChildren().add(nodesAsked_For.get(i));
                }
                while(rs.next()){
                    //Retrieve by column name
                    final int j = i+1;
                    String customerID = rs.getString("CustomerID");
                    String ProgramID = rs.getString("ProgramID");

                    fxmlLoader = new FXMLLoader(getClass().getResource("../ItemsFXML/AskedForItem.fxml"));


                    nodesAsked_For.add(fxmlLoader.load());
                    AskedForItemController askedForItemController = (AskedForItemController) fxmlLoader.getController();


                    askedForItemController.setMainController(this.mainController);
                    askedForItemController.assignTextLabel(customerID,ProgramID);

                    mainController.getPnAskedForItems().getChildren().add(nodesAsked_For.get(j));
                    i++;
                }
                mainController.getNorAskedFor().setText(String.valueOf(i));


                // -------------------------------
                for( ;mainController.getPnConsistOfItems().getChildren().size() > 1;){
                    mainController.getPnConsistOfItems().getChildren().remove(1);
                }
                mainController.getNorConsistOf().setText("0");

                sql = "SELECT * FROM Consist_Of";
                stmt = conn.createStatement();
                rs = stmt.executeQuery(sql);
                //STEP 5: Extract data from result set
                i=0;

                ArrayList<Node> nodesConsistOf = new ArrayList<>();
                fxmlLoaderToAddInsert = new FXMLLoader(getClass().getResource("../InsertItems/InsertConsistOf.fxml"));

                if(i==0){
                    nodesConsistOf.add(fxmlLoaderToAddInsert.load());
                    ConsistOfItemController consistOfItemController = fxmlLoaderToAddInsert.getController();
                    consistOfItemController.setMainController(this.mainController);
                    consistOfItemController.createInsertBox();
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
                for( ;mainController.getPnNotFitItems().getChildren().size() > 1;){
                    mainController.getPnNotFitItems().getChildren().remove(1);
                }
                mainController.getNorNotFit().setText("0");
                sql = "SELECT * FROM Not_Fit";
                rs = stmt.executeQuery(sql);
                //STEP 5: Extract data from result set
                i=0;

                ArrayList<Node> nodesNotFit = new ArrayList<>();
                fxmlLoaderToAddInsert = new FXMLLoader(getClass().getResource("../InsertItems/InsertNotFit.fxml"));

                if(i==0){
                    nodesNotFit.add(fxmlLoaderToAddInsert.load());
                    NotFitItemController notFitItemController = fxmlLoaderToAddInsert.getController();
                    notFitItemController.setMainController(this.mainController);
                    notFitItemController.createInsertBox();
                    mainController.getPnNotFitItems().getChildren().add(nodesNotFit.get(i));
                }
                while(rs.next()){
                    //Retrieve by column name
                    final int j = i+1;
                    String PyhsicalAilmentName = rs.getString("PyhsicalAilmentName");
                    String ProgramID = rs.getString("ProgramID");

                    fxmlLoader = new FXMLLoader(getClass().getResource("../ItemsFXML/NotFitItem.fxml"));


                    nodesNotFit.add(fxmlLoader.load());
                    NotFitItemController notFitItemController = (NotFitItemController) fxmlLoader.getController();

                    notFitItemController.setMainController(this.mainController);
                    notFitItemController.assignTextLabel(PyhsicalAilmentName,ProgramID);

                    mainController.getPnNotFitItems().getChildren().add(nodesNotFit.get(j));
                    i++;
                }
                mainController.getNorNotFit().setText(String.valueOf(i));




                return generatedKeys.getInt(1);
            }
            catch (Exception e1){
                e1.printStackTrace();
            }

        }
        return 0;
    }
    public void deleteProgram(String ID){

        String sql = "delete from Program where ID = ?"; // ------>> duzelttim
        PreparedStatement preparedStmt; // ------>> EKLEDIM

        actionForCancel = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this item?");
        if(actionForCancel == 0){
            try{
                preparedStmt = conn.prepareStatement(sql); // ------>> EKLEDIM
                preparedStmt.setInt(1, Integer.parseInt(ID));
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
                    consistOfItemController.createInsertBox();
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
                for( ;mainController.getPnNotFitItems().getChildren().size() > 1;){
                    mainController.getPnNotFitItems().getChildren().remove(1);
                }
                mainController.getNorNotFit().setText("0");
                sql = "SELECT * FROM Not_Fit";
                rs = stmt.executeQuery(sql);
                //STEP 5: Extract data from result set
                i=0;

                ArrayList<Node> nodesNotFit = new ArrayList<>();
                fxmlLoaderToAddInsert = new FXMLLoader(getClass().getResource("../InsertItems/InsertNotFit.fxml"));

                if(i==0){
                    nodesNotFit.add(fxmlLoaderToAddInsert.load());
                    NotFitItemController notFitItemController = fxmlLoaderToAddInsert.getController();
                    notFitItemController.setMainController(this.mainController);
                    notFitItemController.createInsertBox();
                    mainController.getPnNotFitItems().getChildren().add(nodesNotFit.get(i));
                }
                while(rs.next()){
                    //Retrieve by column name
                    final int j = i+1;
                    String PyhsicalAilmentName = rs.getString("PyhsicalAilmentName");
                    String ProgramID = rs.getString("ProgramID");

                    fxmlLoader = new FXMLLoader(getClass().getResource("../ItemsFXML/NotFitItem.fxml"));


                    nodesNotFit.add(fxmlLoader.load());
                    NotFitItemController notFitItemController = (NotFitItemController) fxmlLoader.getController();

                    notFitItemController.setMainController(this.mainController);
                    notFitItemController.assignTextLabel(PyhsicalAilmentName,ProgramID);

                    mainController.getPnNotFitItems().getChildren().add(nodesNotFit.get(j));
                    i++;
                }
                mainController.getNorNotFit().setText(String.valueOf(i));


                // -------------------------------



                for( ;mainController.getPnAskedForItems().getChildren().size() > 1;){
                    mainController.getPnAskedForItems().getChildren().remove(1);
                }
                mainController.getNorAskedFor().setText("0");
                sql = "SELECT * FROM Asked_For";
                rs = stmt.executeQuery(sql);
                //STEP 5: Extract data from result set
                i=0;

                ArrayList<Node> nodesAsked_For = new ArrayList<>();
                fxmlLoaderToAddInsert = new FXMLLoader(getClass().getResource("../InsertItems/InsertAskedFor.fxml"));

                if(i==0){
                    nodesAsked_For.add(fxmlLoaderToAddInsert.load());
                    AskedForItemController askedForItemController = fxmlLoaderToAddInsert.getController();
                    askedForItemController.setMainController(this.mainController);
                    askedForItemController.createInsertBox();
                    mainController.getPnAskedForItems().getChildren().add(nodesAsked_For.get(i));
                }
                while(rs.next()){
                    //Retrieve by column name
                    final int j = i+1;
                    String customerID = rs.getString("CustomerID");
                    String ProgramID = rs.getString("ProgramID");

                    fxmlLoader = new FXMLLoader(getClass().getResource("../ItemsFXML/AskedForItem.fxml"));


                    nodesAsked_For.add(fxmlLoader.load());
                    AskedForItemController askedForItemController = (AskedForItemController) fxmlLoader.getController();


                    askedForItemController.setMainController(this.mainController);
                    askedForItemController.assignTextLabel(customerID,ProgramID);

                    mainController.getPnAskedForItems().getChildren().add(nodesAsked_For.get(j));
                    i++;
                }
                mainController.getNorAskedFor().setText(String.valueOf(i));

            }
            catch (Exception e1){
                e1.printStackTrace();
            }
        }

    }

    public void updateID(String ID, String newID){
        String sql = "update Program set ID = ? where ID = ?";
        PreparedStatement preparedStmt;
        try{
            preparedStmt = conn.prepareStatement(sql);

            preparedStmt.setInt(1,Integer.valueOf(newID));
            preparedStmt.setInt(2, Integer.valueOf(ID));

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
                consistOfItemController.createInsertBox();
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
            for( ;mainController.getPnNotFitItems().getChildren().size() > 1;){
                mainController.getPnNotFitItems().getChildren().remove(1);
            }
            mainController.getNorNotFit().setText("0");
            sql = "SELECT * FROM Not_Fit";
            rs = stmt.executeQuery(sql);
            //STEP 5: Extract data from result set
            i=0;

            ArrayList<Node> nodesNotFit = new ArrayList<>();
            fxmlLoaderToAddInsert = new FXMLLoader(getClass().getResource("../InsertItems/InsertNotFit.fxml"));

            if(i==0){
                nodesNotFit.add(fxmlLoaderToAddInsert.load());
                NotFitItemController notFitItemController = fxmlLoaderToAddInsert.getController();
                notFitItemController.setMainController(this.mainController);
                notFitItemController.createInsertBox();
                mainController.getPnNotFitItems().getChildren().add(nodesNotFit.get(i));
            }

            while(rs.next()){
                //Retrieve by column name
                final int j = i+1;
                String PyhsicalAilmentName = rs.getString("PyhsicalAilmentName");
                String ProgramID = rs.getString("ProgramID");

                fxmlLoader = new FXMLLoader(getClass().getResource("../ItemsFXML/NotFitItem.fxml"));

                nodesNotFit.add(fxmlLoader.load());
                NotFitItemController notFitItemController = (NotFitItemController) fxmlLoader.getController();


                notFitItemController.setMainController(this.mainController);
                notFitItemController.assignTextLabel(PyhsicalAilmentName,ProgramID);

                mainController.getPnNotFitItems().getChildren().add(nodesNotFit.get(j));
                i++;
            }
            mainController.getNorNotFit().setText(String.valueOf(i));



            // -------------------------------



            for( ;mainController.getPnAskedForItems().getChildren().size() > 1;){
                mainController.getPnAskedForItems().getChildren().remove(1);
            }
            mainController.getNorAskedFor().setText("0");
            sql = "SELECT * FROM Asked_For";
            rs = stmt.executeQuery(sql);
            //STEP 5: Extract data from result set
            i=0;

            ArrayList<Node> nodesAsked_For = new ArrayList<>();
            fxmlLoaderToAddInsert = new FXMLLoader(getClass().getResource("../InsertItems/InsertAskedFor.fxml"));

            if(i==0){
                nodesAsked_For.add(fxmlLoaderToAddInsert.load());
                AskedForItemController askedForItemController = fxmlLoaderToAddInsert.getController();
                askedForItemController.setMainController(this.mainController);
                askedForItemController.createInsertBox();
                mainController.getPnAskedForItems().getChildren().add(nodesAsked_For.get(i));
            }

            while(rs.next()){
                //Retrieve by column name
                final int j = i+1;
                String customerID = rs.getString("CustomerID");
                String ProgramID = rs.getString("ProgramID");

                fxmlLoader = new FXMLLoader(getClass().getResource("../ItemsFXML/AskedForItem.fxml"));

                nodesAsked_For.add(fxmlLoader.load());
                AskedForItemController askedForItemController = (AskedForItemController) fxmlLoader.getController();


                askedForItemController.setMainController(this.mainController);
                askedForItemController.assignTextLabel(customerID,ProgramID);

                mainController.getPnAskedForItems().getChildren().add(nodesAsked_For.get(j));
                i++;
            }
            mainController.getNorAskedFor().setText(String.valueOf(i));
        }
        catch (Exception e1){
            e1.printStackTrace();
        }
    }

    public void updateBMI(String newFieldID,String newFieldBMI){
        String sql = "update Program set bmi = ? where ID = ?";
        PreparedStatement preparedStmt;
        try{
            preparedStmt = conn.prepareStatement(sql);

            if(newFieldBMI.isEmpty())
                preparedStmt.setNull(1, Types.FLOAT);
            else
                preparedStmt.setFloat(1, Float.parseFloat(newFieldBMI));

            preparedStmt.setInt(2,Integer.parseInt(newFieldID));
            preparedStmt.executeUpdate();

        }
        catch (Exception e1){
            e1.printStackTrace();
        }
    }


}
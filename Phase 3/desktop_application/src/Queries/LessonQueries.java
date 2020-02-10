package Queries;

import Controllers.Controller;
import Controllers.EnrollTimeItemController;
import Controllers.PurchaseItemController;
import Controllers.UsedInItemController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

public class LessonQueries {
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

    public LessonQueries(Connection conn) {
        try{
            this.conn = conn;
        }
        catch (Exception e1){
            e1.printStackTrace();
        }

    }
    public int insertLesson(String name, String TrainerID , String ProfessionName, String Price ){

        actionForCancel = JOptionPane.showConfirmDialog(null, "Are you sure you want to add this item?");
        if(actionForCancel == 0){
            try{
                if(name.isEmpty()){
                    name = null;
                }

                if(Price.isEmpty()){
                    PreparedStatement statement = conn.prepareStatement(String.format(
                            "INSERT INTO Lesson( `Name`, TrainerID, ProfessionName) VALUES( %s ,%s, %s)"
                            , name , TrainerID, ProfessionName
                            ), Statement.RETURN_GENERATED_KEYS);
                    int affectedRows = statement.executeUpdate();
                    ResultSet generatedKeys = statement.getGeneratedKeys();
                    generatedKeys.next();
                    return generatedKeys.getInt(1);
                }


                PreparedStatement statement = conn.prepareStatement(
                            "INSERT INTO Lesson( `Name`, TrainerID, ProfessionName, Price )" + " VALUES(?, ?,?,?)" ,
                            Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, name);
                statement.setString(2, TrainerID);
                statement.setString(3, ProfessionName);
                statement.setInt(4, Integer.parseInt(Price));
                int affectedRows = statement.executeUpdate();
                ResultSet generatedKeys = statement.getGeneratedKeys();
                generatedKeys.next();



                // -------------------------------

                for( ;mainController.getPnEnrollTimeItems().getChildren().size() > 1;){
                    mainController.getPnEnrollTimeItems().getChildren().remove(1);
                }
                Statement stmt = conn.createStatement();
                String sql = "SELECT * FROM Enroll_Time";
                ResultSet rs = stmt.executeQuery(sql);
                //STEP 5: Extract data from result set
                int i=0;

                ArrayList<Node> nodesEnrollTime = new ArrayList<>();
                FXMLLoader fxmlLoaderToAddInsert = new FXMLLoader(getClass().getResource("../InsertItems/InsertEnrollTime.fxml"));

                if(i==0){
                    nodesEnrollTime.add(fxmlLoaderToAddInsert.load());
                    EnrollTimeItemController enrollTimeItemController = fxmlLoaderToAddInsert.getController();
                    enrollTimeItemController.setMainController(this.mainController);
                    enrollTimeItemController.createInsertBox();
                    mainController.getPnEnrollTimeItems().getChildren().add(nodesEnrollTime.get(i));
                }
                while(rs.next()){
                    //Retrieve by column name
                    final int j = i+1;
                    String lessonID = rs.getString("LessonID");
                    String Day = rs.getString("Day");
                    String StartTime  = rs.getString("StartTime");
                    String EndTime = rs.getString("EndTime");
                    String Quota = rs.getString("Quota");

                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../ItemsFXML/EnrollTimeItem.fxml"));


                    nodesEnrollTime.add(fxmlLoader.load());
                    EnrollTimeItemController enrollTimeItemController = (EnrollTimeItemController) fxmlLoader.getController();

                    enrollTimeItemController.setMainController(this.mainController);
                    enrollTimeItemController.assignTextLabel(lessonID,Day,StartTime,EndTime,Quota);

                    mainController.getPnEnrollTimeItems().getChildren().add(nodesEnrollTime.get(j));
                    i++;
                }
                mainController.getNorEnrollTime().setText(String.valueOf(i));


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



                return generatedKeys.getInt(1);
            }
            catch (Exception e1){
                e1.printStackTrace();
            }

        }
        return 0;
    }

    public void deleteLesson(String lessonName){

        String sql = "delete from Lesson where Name = ?"; // ------>> duzelttim
        PreparedStatement preparedStmt; // ------>> EKLEDIM

        actionForCancel = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this item?");
        if(actionForCancel == 0){
            try{
                preparedStmt = conn.prepareStatement(sql); // ------>> EKLEDIM
                preparedStmt.setString(1, lessonName);
                preparedStmt.execute();


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

                for( ;mainController.getPnEnrollTimeItems().getChildren().size() > 1;){
                    mainController.getPnEnrollTimeItems().getChildren().remove(1);
                }
                sql = "SELECT * FROM Enroll_Time";
                rs = stmt.executeQuery(sql);
                //STEP 5: Extract data from result set
                i=0;

                ArrayList<Node> nodesEnrollTime = new ArrayList<>();
                fxmlLoaderToAddInsert = new FXMLLoader(getClass().getResource("../InsertItems/InsertEnrollTime.fxml"));

                if(i==0){
                    nodesEnrollTime.add(fxmlLoaderToAddInsert.load());
                    EnrollTimeItemController enrollTimeItemController = fxmlLoaderToAddInsert.getController();
                    enrollTimeItemController.setMainController(this.mainController);
                    enrollTimeItemController.createInsertBox();
                    mainController.getPnEnrollTimeItems().getChildren().add(nodesEnrollTime.get(i));
                }
                while(rs.next()){
                    //Retrieve by column name
                    final int j = i+1;
                    String lessonID = rs.getString("LessonID");
                    String Day = rs.getString("Day");
                    String StartTime  = rs.getString("StartTime");
                    String EndTime = rs.getString("EndTime");
                    String Quota = rs.getString("Quota");

                    fxmlLoader = new FXMLLoader(getClass().getResource("../ItemsFXML/EnrollTimeItem.fxml"));


                    nodesEnrollTime.add(fxmlLoader.load());
                    EnrollTimeItemController enrollTimeItemController = (EnrollTimeItemController) fxmlLoader.getController();

                    enrollTimeItemController.setMainController(this.mainController);
                    enrollTimeItemController.assignTextLabel(lessonID,Day,StartTime,EndTime,Quota);

                    mainController.getPnEnrollTimeItems().getChildren().add(nodesEnrollTime.get(j));
                    i++;
                }
                mainController.getNorEnrollTime().setText(String.valueOf(i));


                // -------------------------------
                for( ;mainController.getPnPurchaseItems().getChildren().size() > 1;){
                    mainController.getPnPurchaseItems().getChildren().remove(1);
                }
                sql = "SELECT * FROM Purchase";
                rs = stmt.executeQuery(sql);
                //STEP 5: Extract data from result set
                i=0;

                ArrayList<Node> nodesPurchase = new ArrayList<>();
                fxmlLoaderToAddInsert = new FXMLLoader(getClass().getResource("../InsertItems/InsertPurchase.fxml"));

                if(i==0){
                    nodesPurchase.add(fxmlLoaderToAddInsert.load());
                    PurchaseItemController purchaseItemController = fxmlLoaderToAddInsert.getController();
                    purchaseItemController.setMainController(this.mainController);
                    mainController.getPnPurchaseItems().getChildren().add(nodesPurchase.get(i));
                }
                while(rs.next()){
                    //Retrieve by column name
                    final int j = i+1;
                    String customerID = rs.getString("CustomerID");
                    String LessonID = rs.getString("LessonID");
                    String enrollTimeDay = rs.getString("EnrollTimeDay");
                    String PurchasedDate = rs.getString("PurchasedDate");

                    fxmlLoader = new FXMLLoader(getClass().getResource("../ItemsFXML/PurchaseItem.fxml"));

                    nodesPurchase.add(fxmlLoader.load());
                    PurchaseItemController purchaseItemController = (PurchaseItemController) fxmlLoader.getController();

                    purchaseItemController.setMainController(this.mainController);
                    purchaseItemController.assignTextLabel(customerID,LessonID,enrollTimeDay,PurchasedDate);

                    mainController.getPnPurchaseItems().getChildren().add(nodesPurchase.get(j));
                    i++;
                }
                mainController.getNorPurchase().setText(String.valueOf(i));
            }
            catch (Exception e1){
                e1.printStackTrace();
            }
        }

    }
    public void updateLessonID(String oldFieldID,String newFieldID){
        String sql = "update Lesson set ID = ? where ID = ?";
        PreparedStatement preparedStmt;
        try{
            preparedStmt = conn.prepareStatement(sql);
            preparedStmt.setInt(1, Integer.valueOf(newFieldID));
            preparedStmt.setInt(2, Integer.valueOf(oldFieldID));
            preparedStmt.executeUpdate();

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

            for( ;mainController.getPnEnrollTimeItems().getChildren().size() > 1;){
                mainController.getPnEnrollTimeItems().getChildren().remove(1);
            }
            sql = "SELECT * FROM Enroll_Time";
            rs = stmt.executeQuery(sql);
            //STEP 5: Extract data from result set
            i=0;

            ArrayList<Node> nodesEnrollTime = new ArrayList<>();
            fxmlLoaderToAddInsert = new FXMLLoader(getClass().getResource("../InsertItems/InsertEnrollTime.fxml"));

            if(i==0){
                nodesEnrollTime.add(fxmlLoaderToAddInsert.load());
                EnrollTimeItemController enrollTimeItemController = fxmlLoaderToAddInsert.getController();
                enrollTimeItemController.setMainController(this.mainController);
                enrollTimeItemController.createInsertBox();
                mainController.getPnEnrollTimeItems().getChildren().add(nodesEnrollTime.get(i));
            }
            while(rs.next()){
                //Retrieve by column name
                final int j = i+1;
                String lessonID = rs.getString("LessonID");
                String Day = rs.getString("Day");
                String StartTime  = rs.getString("StartTime");
                String EndTime = rs.getString("EndTime");
                String Quota = rs.getString("Quota");

                fxmlLoader = new FXMLLoader(getClass().getResource("../ItemsFXML/EnrollTimeItem.fxml"));


                nodesEnrollTime.add(fxmlLoader.load());
                EnrollTimeItemController enrollTimeItemController = (EnrollTimeItemController) fxmlLoader.getController();

                enrollTimeItemController.setMainController(this.mainController);
                enrollTimeItemController.assignTextLabel(lessonID,Day,StartTime,EndTime,Quota);

                mainController.getPnEnrollTimeItems().getChildren().add(nodesEnrollTime.get(j));
                i++;
            }
            mainController.getNorEnrollTime().setText(String.valueOf(i));


            // -------------------------------
            for( ;mainController.getPnPurchaseItems().getChildren().size() > 1;){
                mainController.getPnPurchaseItems().getChildren().remove(1);
            }
            sql = "SELECT * FROM Purchase";
            rs = stmt.executeQuery(sql);
            //STEP 5: Extract data from result set
            i=0;

            ArrayList<Node> nodesPurchase = new ArrayList<>();
            fxmlLoaderToAddInsert = new FXMLLoader(getClass().getResource("../InsertItems/InsertPurchase.fxml"));

            if(i==0){
                nodesPurchase.add(fxmlLoaderToAddInsert.load());
                PurchaseItemController purchaseItemController = fxmlLoaderToAddInsert.getController();
                purchaseItemController.setMainController(this.mainController);
                mainController.getPnPurchaseItems().getChildren().add(nodesPurchase.get(i));
            }
            while(rs.next()){
                //Retrieve by column name
                final int j = i+1;
                String customerID = rs.getString("CustomerID");
                String LessonID = rs.getString("LessonID");
                String enrollTimeDay = rs.getString("EnrollTimeDay");
                String PurchasedDate = rs.getString("PurchasedDate");

                fxmlLoader = new FXMLLoader(getClass().getResource("../ItemsFXML/PurchaseItem.fxml"));

                nodesPurchase.add(fxmlLoader.load());
                PurchaseItemController purchaseItemController = (PurchaseItemController) fxmlLoader.getController();

                purchaseItemController.setMainController(this.mainController);
                purchaseItemController.assignTextLabel(customerID,LessonID,enrollTimeDay,PurchasedDate);

                mainController.getPnPurchaseItems().getChildren().add(nodesPurchase.get(j));
                i++;
            }
            mainController.getNorPurchase().setText(String.valueOf(i));
        }
        catch (Exception e1){
            e1.printStackTrace();
        }
    }
    public void updateLessonName(String newFieldID,String newFieldName){
        String sql = "update Lesson set Name = ? where ID = ?";
        PreparedStatement preparedStmt;
        try{
            preparedStmt = conn.prepareStatement(sql);
            preparedStmt.setString(1, newFieldName);
            preparedStmt.setInt(2, Integer.valueOf(newFieldID));
            preparedStmt.executeUpdate();

        }
        catch (Exception e1){
            e1.printStackTrace();
        }
    }

    public void updateTrainerID(String newFieldID,String newFieldTrainerID ){
        String sql = "update Lesson set TrainerID = ? where ID = ?";
        PreparedStatement preparedStmt;
        try{
            preparedStmt = conn.prepareStatement(sql);
            preparedStmt.setString(1, newFieldTrainerID);
            preparedStmt.setInt(2, Integer.valueOf(newFieldID));
            preparedStmt.executeUpdate();

        }
        catch (Exception e1){
            e1.printStackTrace();
        }
    }

    public void updateProfessionName(String newFieldID,String newFieldProfessionName){
        String sql = "update Lesson set ProfessionName = ? where ID = ?";
        PreparedStatement preparedStmt;
        try{
            preparedStmt = conn.prepareStatement(sql);
            preparedStmt.setString(1, newFieldProfessionName);
            preparedStmt.setInt(2, Integer.valueOf(newFieldID));
            preparedStmt.executeUpdate();

        }
        catch (Exception e1){
            e1.printStackTrace();
        }
    }

    public void updateLessonPrice(String newFieldID,String newFieldPrice){
        String sql = "update Lesson set Price = ? where ID = ?";
        PreparedStatement preparedStmt;
        try{
            preparedStmt = conn.prepareStatement(sql);
            if(newFieldPrice.isEmpty())
                preparedStmt.setNull(1, Types.FLOAT);
            else
                preparedStmt.setFloat(1, Float.parseFloat(newFieldPrice));
            preparedStmt.setInt(2, Integer.valueOf(newFieldID));
            preparedStmt.executeUpdate();

        }
        catch (Exception e1){
            e1.printStackTrace();
        }
    }

}

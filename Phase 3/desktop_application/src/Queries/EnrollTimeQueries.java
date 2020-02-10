package Queries;

import Controllers.Controller;
import Controllers.PurchaseItemController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

public class EnrollTimeQueries {
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

    public EnrollTimeQueries(Connection conn) {
        try{
            this.conn = conn;
        }
        catch (Exception e1){
            e1.printStackTrace();
        }

    }

    public void insertEnrollTime(String LessonID, String Day , String StartTime, String EndTime, String Quota
    ){

        String sql = "INSERT INTO Enroll_Time( LessonID, Day, StartTime, EndTime , Quota)"
                +"VALUES(?,?,?,?,?)";
        PreparedStatement preparedStmt;

        actionForCancel = JOptionPane.showConfirmDialog(null, "Are you sure you want to add this item?");
        if(actionForCancel == 0){
            try{
                preparedStmt = conn.prepareStatement(sql);
                preparedStmt.setString(1,LessonID);
                preparedStmt.setString(2,Day);
                preparedStmt.setString(3,StartTime);
                preparedStmt.setString(4,EndTime);
                if(Quota.isEmpty())
                    preparedStmt.setNull(5, Types.INTEGER);
                else
                    preparedStmt.setInt(5, Integer.parseInt(Quota));
                preparedStmt.execute();
            }
            catch (Exception e1){
                e1.printStackTrace();
            }
        }
    }

    public void deleteEnrollTime(String lessonID, String Day){

        String sql = "delete from Enroll_Time where LessonID = ? and Day = ?"; // ------>> duzelttim
        PreparedStatement preparedStmt; // ------>> EKLEDIM

        actionForCancel = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this item?");
        if(actionForCancel == 0){
            try{
                preparedStmt = conn.prepareStatement(sql); // ------>> EKLEDIM
                preparedStmt.setString(1, lessonID);
                preparedStmt.setString(2, Day);
                preparedStmt.execute();

                // -------------------------------
                for( ;mainController.getPnPurchaseItems().getChildren().size() > 1;){
                    mainController.getPnPurchaseItems().getChildren().remove(1);
                }

                sql = "SELECT * FROM Purchase";
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                //STEP 5: Extract data from result set
                int i=0;

                ArrayList<Node> nodesPurchase = new ArrayList<>();
                FXMLLoader fxmlLoader;
                FXMLLoader fxmlLoaderToAddInsert;
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

    public void updateDay(String newFieldLessonId,String oldFieldDay, String newFieldDay){
        String sql = "update Enroll_Time set Day = ? where LessonID = ? and Day = ?";
        PreparedStatement preparedStmt;
        try{
            preparedStmt = conn.prepareStatement(sql);
            preparedStmt.setString(1, newFieldDay);
            preparedStmt.setInt(2, Integer.valueOf(newFieldLessonId));
            preparedStmt.setString(3, oldFieldDay);
            preparedStmt.executeUpdate();
// -------------------------------
            for( ;mainController.getPnPurchaseItems().getChildren().size() > 1;){
                mainController.getPnPurchaseItems().getChildren().remove(1);
            }

            sql = "SELECT * FROM Purchase";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            //STEP 5: Extract data from result set
            int i=0;

            ArrayList<Node> nodesPurchase = new ArrayList<>();
            FXMLLoader fxmlLoader;
            FXMLLoader fxmlLoaderToAddInsert;
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

    public void updateLessonID(String oldFieldLessonID,String oldFieldDay,String newFieldLessonId){
        String sql = "update Enroll_Time set LessonID = ? where Day = ? and LessonID = ?";
        PreparedStatement preparedStmt;
        try{
            preparedStmt = conn.prepareStatement(sql);
            preparedStmt.setInt(1, Integer.valueOf(newFieldLessonId));
            preparedStmt.setString(2, oldFieldDay);
            preparedStmt.setString(3, oldFieldLessonID);
            preparedStmt.executeUpdate();

        }
        catch (Exception e1){
            e1.printStackTrace();
        }
    }
    public void updateStartTime(String newFieldLessonId,String newFieldDay,String newFieldStartTime){
        String sql = "update Enroll_Time set StartTime = ? where LessonID = ? and Day = ?";
        PreparedStatement preparedStmt;
        try{
            preparedStmt = conn.prepareStatement(sql);
            preparedStmt.setString(1, newFieldStartTime);
            preparedStmt.setInt(2, Integer.valueOf(newFieldLessonId));
            preparedStmt.setString(3, newFieldDay);
            preparedStmt.executeUpdate();

        }
        catch (Exception e1){
            e1.printStackTrace();
        }
    }

    public void updateEndTime(String newFieldLessonId,String newFieldDay, String newFieldEndTime ){
        String sql = "update Enroll_Time set EndTime = ? where LessonID = ? and Day = ?";
        PreparedStatement preparedStmt;
        try{
            preparedStmt = conn.prepareStatement(sql);
            preparedStmt.setString(1, newFieldEndTime);
            preparedStmt.setInt(2, Integer.valueOf(newFieldLessonId));
            preparedStmt.setString(3, newFieldDay);
            preparedStmt.executeUpdate();

        }
        catch (Exception e1){
            e1.printStackTrace();
        }
    }

    public void updateQuota(String newFieldLessonId,String newFieldDay,String newFieldQuota){
        String sql = "update Enroll_Time set Quota = ? where LessonID = ? and Day = ?";
        PreparedStatement preparedStmt;
        try{
            preparedStmt = conn.prepareStatement(sql);
            if(newFieldQuota.isEmpty())
                preparedStmt.setNull(1, Types.INTEGER);
            else
                preparedStmt.setInt(1, Integer.parseInt(newFieldQuota));
            preparedStmt.setInt(2, Integer.valueOf(newFieldLessonId));
            preparedStmt.setString(3, newFieldDay);
            preparedStmt.executeUpdate();

        }
        catch (Exception e1){
            e1.printStackTrace();
        }
    }
}

package Queries;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Types;

public class UsedInQueries {
    private Connection conn;

    public int getActionForCancel() {
        return actionForCancel;
    }
    public void setActionForCancel(int actionForCancel) {
        this.actionForCancel = actionForCancel;
    }

    private int actionForCancel;

    public UsedInQueries(Connection conn) {
        try{
            this.conn = conn;
        }
        catch (Exception e1){
            e1.printStackTrace();
        }

    }

    public void deleteUsedIn(String FieldSportToolsName, String FieldLessonID){

        String sql = "delete from Used_In where SportToolsName = ? and LessonID = ?"; // ------>> duzelttim
        PreparedStatement preparedStmt; // ------>> EKLEDIM

        actionForCancel = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this item?");
        if(actionForCancel == 0){
            try{
                preparedStmt = conn.prepareStatement(sql); // ------>> EKLEDIM
                preparedStmt.setString(1, FieldSportToolsName);
                preparedStmt.setString(2, FieldLessonID);
                preparedStmt.execute();
            }
            catch (Exception e1){
                e1.printStackTrace();
            }
        }

    }

    public int insertUsedIn(String SportToolsName,String LessonID, String Quantity
    ){

        String sql = "INSERT INTO Used_In( SportToolsName,LessonID,Quantity )"
                +"VALUES(?,"+
                "?," +
                "?)";
        PreparedStatement preparedStmt;

        actionForCancel = JOptionPane.showConfirmDialog(null, "Are you sure you want to add this item?");
        if(actionForCancel == 0){
            try{
                preparedStmt = conn.prepareStatement(sql);
                preparedStmt.setString(1, SportToolsName);
                preparedStmt.setInt(2, Integer.parseInt(LessonID));
                if(Quantity.isEmpty())
                    preparedStmt.setNull(3, Types.INTEGER);
                else
                    preparedStmt.setInt(3, Integer.parseInt(Quantity));
                preparedStmt.execute();
                return 1;
            }
            catch (Exception e1){
                e1.printStackTrace();
            }
        }
        return 0;
    }

    public void updateSportToolsName(String oldFieldSportToolsName,String newFieldLessonID,String newFieldSportToolsName){
        String sql = "update Used_In set SportToolsName = ? where SportToolsName = ? and LessonID = ?";
        PreparedStatement preparedStmt;
        try{
            preparedStmt = conn.prepareStatement(sql);
            preparedStmt.setString(1, newFieldSportToolsName);
            preparedStmt.setString(2, oldFieldSportToolsName);
            preparedStmt.setInt(3, Integer.parseInt(newFieldLessonID));
            preparedStmt.executeUpdate();

        }
        catch (Exception e1){
            e1.printStackTrace();
        }
    }

    public void updateLessonID(String oldFieldLessonID,String newFieldSportToolsName,String newFieldLessonID){
        String sql = "update Used_In set LessonID = ? where LessonID = ? and SportToolsName = ?";
        PreparedStatement preparedStmt;
        try{
            preparedStmt = conn.prepareStatement(sql);
            preparedStmt.setInt(1, Integer.parseInt(newFieldLessonID));
            preparedStmt.setInt(2, Integer.parseInt(oldFieldLessonID));
            preparedStmt.setString(3, newFieldSportToolsName);
            preparedStmt.executeUpdate();

        }
        catch (Exception e1){
            e1.printStackTrace();
        }
    }
    public void updateQuantity(String newFieldSportToolsName,String newFieldLessonID,String newFieldQuantity){
        String sql = "update Used_In set Quantity = ? where SportToolsName = ? and LessonID = ?";
        PreparedStatement preparedStmt;
        try{
            preparedStmt = conn.prepareStatement(sql);
            if(newFieldQuantity.isEmpty())
                preparedStmt.setNull(1, Types.INTEGER);
            else
                preparedStmt.setInt(1, Integer.parseInt(newFieldQuantity));
            preparedStmt.setString(2, newFieldSportToolsName);
            preparedStmt.setInt(3, Integer.parseInt(newFieldLessonID));
            preparedStmt.executeUpdate();

        }
        catch (Exception e1){
            e1.printStackTrace();
        }
    }
}

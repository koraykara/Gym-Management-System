package Queries;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Types;

public class MakeWithQueries {
    private Connection conn;

    public int getActionForCancel() {
        return actionForCancel;
    }
    public void setActionForCancel(int actionForCancel) {
        this.actionForCancel = actionForCancel;
    }

    private int actionForCancel;

    public MakeWithQueries(Connection conn) {
        try{
            this.conn = conn;
        }
        catch (Exception e1){
            e1.printStackTrace();
        }

    }
    public int insertMakeWith(String SportToolsName , String ExerciseName
    ){

        String sql = "INSERT INTO Make_With( SportToolsName, ExerciseName )"
                +" VALUES(?,? )";
        PreparedStatement preparedStmt;

        actionForCancel = JOptionPane.showConfirmDialog(null, "Are you sure you want to add this item?");
        if(actionForCancel == 0){
            try{
                preparedStmt = conn.prepareStatement(sql);
                preparedStmt.setString(1,  SportToolsName );
                preparedStmt.setString(2, ExerciseName);
                preparedStmt.execute();
                return 1;
            }
            catch (Exception e1){
                e1.printStackTrace();
            }
        }
        return 0;
    }
    public void deleteMakeWith(String FieldSportToolsName,String FieldExerciseName){

        String sql = "delete from Make_With where SportToolsName = ? and ExerciseName = ?";
        PreparedStatement preparedStmt;

        actionForCancel = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this item?");
        if(actionForCancel == 0){
            try{
                preparedStmt = conn.prepareStatement(sql);
                preparedStmt.setString(1, FieldSportToolsName);
                preparedStmt.setString(2, FieldExerciseName);
                preparedStmt.execute();
            }
            catch (Exception e1){
                e1.printStackTrace();
            }
        }

    }

    public void updateSportToolsName(String oldFieldSportToolsName,String newFieldExerciseName,String newFieldSportToolsName){
        String sql = "update Make_With set SportToolsName = ? where SportToolsName = ? and ExerciseName = ?";
        PreparedStatement preparedStmt;
        try{
            preparedStmt = conn.prepareStatement(sql);
            preparedStmt.setString(1, newFieldSportToolsName);
            preparedStmt.setString(2, oldFieldSportToolsName);
            preparedStmt.setString(3, newFieldExerciseName);
            preparedStmt.executeUpdate();

        }
        catch (Exception e1){
            e1.printStackTrace();
        }
    }

    public void updateExerciseName(String oldFieldExerciseName,String newFieldSportToolsName,String newFieldExerciseName){
        String sql = "update Make_With set ExerciseName = ? where SportToolsName = ? and ExerciseName = ?";
        PreparedStatement preparedStmt;
        try{
            preparedStmt = conn.prepareStatement(sql);
            preparedStmt.setString(1, newFieldExerciseName);
            preparedStmt.setString(2, newFieldSportToolsName);
            preparedStmt.setString(3, oldFieldExerciseName);
            preparedStmt.executeUpdate();

        }
        catch (Exception e1){
            e1.printStackTrace();
        }
    }
}

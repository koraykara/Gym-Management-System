package Queries;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Types;

public class ConsisftOfQueries {
    private Connection conn;

    public int getActionForCancel() {
        return actionForCancel;
    }
    public void setActionForCancel(int actionForCancel) {
        this.actionForCancel = actionForCancel;
    }

    private int actionForCancel;

    public ConsisftOfQueries(Connection conn) {
        try{
            this.conn = conn;
        }
        catch (Exception e1){
            e1.printStackTrace();
        }

    }
    public int insertConsistOf(String programID, String exerciseName
    ){

        String sql = "INSERT INTO Consist_Of( ProgramID, ExerciseName )"
                +" VALUES(?,? )";
        PreparedStatement preparedStmt;

        actionForCancel = JOptionPane.showConfirmDialog(null, "Are you sure you want to add this item?");
        if(actionForCancel == 0){
            try{
                preparedStmt = conn.prepareStatement(sql);
                if(programID.isEmpty())
                    preparedStmt.setNull(1, Types.INTEGER);
                else
                    preparedStmt.setInt(1, Integer.parseInt(programID));

                preparedStmt.setString(2, exerciseName);
                preparedStmt.execute();
                return 1;
            }
            catch (Exception e1){
                e1.printStackTrace();
            }
        }
        return 0;
    }
    public void deleteConsistOf(String FieldProgramID,String FieldExerciseName){

        String sql = "delete from Consist_Of where ProgramID = ? and ExerciseName = ?";
        PreparedStatement preparedStmt; // ------>> EKLEDIM

        actionForCancel = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this item?");
        if(actionForCancel == 0){
            try{
                preparedStmt = conn.prepareStatement(sql); // ------>> EKLEDIM
                preparedStmt.setString(1, FieldProgramID);
                preparedStmt.setString(2, FieldExerciseName);
                preparedStmt.execute();
            }
            catch (Exception e1){
                e1.printStackTrace();
            }
        }

    }

    public void updateProgramID(String oldFieldProgramID,String newFieldExerciseName,String newFieldProgramID){
        String sql = "update Consist_Of set ProgramID = ? where ProgramID = ? and ExerciseName = ?";
        PreparedStatement preparedStmt;
        try{
            preparedStmt = conn.prepareStatement(sql);
            if(newFieldProgramID.isEmpty())
                preparedStmt.setNull(1, Types.INTEGER);
            else
                preparedStmt.setInt(1, Integer.parseInt(newFieldProgramID));
            preparedStmt.setInt(2, Integer.valueOf(oldFieldProgramID));
            preparedStmt.setString(3, newFieldExerciseName);
            preparedStmt.executeUpdate();

        }
        catch (Exception e1){
            e1.printStackTrace();
        }
    }

    public void updateExerciseName(String oldFieldExerciseName,String newFieldProgramID,String newFieldExerciseName){
        String sql = "update Consist_Of set ExerciseName = ? where ProgramID = ? and ExerciseName = ?";
        PreparedStatement preparedStmt;
        try{
            preparedStmt = conn.prepareStatement(sql);
            preparedStmt.setString(1, newFieldExerciseName);
            preparedStmt.setInt(2, Integer.valueOf(newFieldProgramID));
            preparedStmt.setString(3, oldFieldExerciseName);
            preparedStmt.executeUpdate();

        }
        catch (Exception e1){
            e1.printStackTrace();
        }
    }
}

package Queries;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Types;

public class NotFitQueries {
    private Connection conn;

    public int getActionForCancel() {
        return actionForCancel;
    }
    public void setActionForCancel(int actionForCancel) {
        this.actionForCancel = actionForCancel;
    }

    private int actionForCancel;

    public NotFitQueries(Connection conn) {
        try{
            this.conn = conn;
        }
        catch (Exception e1){
            e1.printStackTrace();
        }

    }
    public int insertNotFit(String pyhsicalAilmentName , String programID
    ){

        String sql = "INSERT INTO Not_Fit( PyhsicalAilmentName, ProgramID )"
                +"VALUES(?,?)";
        PreparedStatement preparedStmt;

        actionForCancel = JOptionPane.showConfirmDialog(null, "Are you sure you want to add this item?");
        if(actionForCancel == 0){
            try{
                preparedStmt = conn.prepareStatement(sql);
                preparedStmt.setString(1, pyhsicalAilmentName);
                if(programID.isEmpty())
                    preparedStmt.setNull(2, Types.INTEGER);
                else
                    preparedStmt.setInt(2, Integer.parseInt(programID));
                preparedStmt.execute();
                return 1;
            }
            catch (Exception e1){
                e1.printStackTrace();
            }
        }
        return 0;
    }
    public void deleteNotFit(String FieldPyhsicalAilmentName,String FieldProgramID){

        String sql = "delete from Not_Fit where PyhsicalAilmentName = ? and ProgramID = ?"; // ------>> duzelttim
        PreparedStatement preparedStmt; // ------>> EKLEDIM

        actionForCancel = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this item?");
        if(actionForCancel == 0){
            try{
                preparedStmt = conn.prepareStatement(sql); // ------>> EKLEDIM
                preparedStmt.setString(1, FieldPyhsicalAilmentName);
                preparedStmt.setString(2, FieldProgramID);
                preparedStmt.execute();
            }
            catch (Exception e1){
                e1.printStackTrace();
            }
        }

    }

    public void updatePyhsicalAilmentName(String oldFieldPyhsicalAilmentName,String newFieldProgramID,String newFieldPyhsicalAilmentName){
        String sql = "update Not_Fit set PyhsicalAilmentName = ? where PyhsicalAilmentName = ? and ProgramID = ?";
        PreparedStatement preparedStmt;
        try{
            preparedStmt = conn.prepareStatement(sql);
            preparedStmt.setString(1, newFieldPyhsicalAilmentName);
            preparedStmt.setString(2, oldFieldPyhsicalAilmentName);
            preparedStmt.setInt(3, Integer.valueOf(newFieldProgramID));
            preparedStmt.executeUpdate();

        }
        catch (Exception e1){
            e1.printStackTrace();
        }
    }

    public void updateProgramID(String oldFieldProgramID,String newFieldPyhsicalAilmentName,String newFieldProgramID){
        String sql = "update Not_Fit set ProgramID = ? where PyhsicalAilmentName = ? and ProgramID = ?";
        PreparedStatement preparedStmt;
        try{
            preparedStmt = conn.prepareStatement(sql);
            if(newFieldProgramID.isEmpty())
                preparedStmt.setNull(1, Types.INTEGER);
            else
                preparedStmt.setInt(1, Integer.parseInt(newFieldProgramID));

            preparedStmt.setString(2, newFieldPyhsicalAilmentName);
            preparedStmt.setInt(3, Integer.parseInt(oldFieldProgramID));
            preparedStmt.executeUpdate();

        }
        catch (Exception e1){
            e1.printStackTrace();
        }
    }
}

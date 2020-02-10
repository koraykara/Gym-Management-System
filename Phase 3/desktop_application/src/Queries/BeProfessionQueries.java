package Queries;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Types;

public class BeProfessionQueries {

    private Connection conn;

    public int getActionForCancel() {
        return actionForCancel;
    }
    public void setActionForCancel(int actionForCancel) {
        this.actionForCancel = actionForCancel;
    }

    private int actionForCancel;

    public BeProfessionQueries(Connection conn) {
        try{
            this.conn = conn;
        }
        catch (Exception e1){
            e1.printStackTrace();
        }

    }

    public int insertBeProfession(String name, String trainerID
    ){

        String sql = "INSERT INTO Be_Profession( ProfessionName, TrainerID )"
                +"VALUES(?,"+
                "?)";
        PreparedStatement preparedStmt;

        actionForCancel = JOptionPane.showConfirmDialog(null, "Are you sure you want to add this item?");
        if(actionForCancel == 0){
            try{
                preparedStmt = conn.prepareStatement(sql);
                preparedStmt.setString(1,name);
                preparedStmt.setString(2,trainerID);
                preparedStmt.execute();
            }
            catch (Exception e1){
                e1.printStackTrace();
            }
            return 1;
        }
        return 0;
    }

    public void deleteBeProfessionName(String ProfessionName){

        String sql = "delete from Be_Profession where ProfessionName = ?"; // ------>> duzelttim
        PreparedStatement preparedStmt; // ------>> EKLEDIM

        actionForCancel = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this item?");
        if(actionForCancel == 0){
            try{
                preparedStmt = conn.prepareStatement(sql); // ------>> EKLEDIM
                preparedStmt.setString(1, ProfessionName);
                preparedStmt.execute();
            }
            catch (Exception e1){
                e1.printStackTrace();
            }
        }

    }

    public void updateBeProfessionName(String ProfessionName , String newProfessionName){
        String sql = "update Be_Profession set ProfessionName = ? where ProfessionName = ?";
        PreparedStatement preparedStmt;
        try{
            preparedStmt = conn.prepareStatement(sql);
            preparedStmt.setString(1, newProfessionName);
            preparedStmt.setString(2, ProfessionName);
            preparedStmt.executeUpdate();

        }
        catch (Exception e1){
            e1.printStackTrace();
        }
    }

    public void updateBeProfessionTrainerID(String ProfessionName,String TrainerID ){
        String sql = "update Be_Profession set TrainerID = ? where ProfessionName = ?";
        PreparedStatement preparedStmt;
        try{
            preparedStmt = conn.prepareStatement(sql);
            preparedStmt.setString(1, TrainerID);
            preparedStmt.setString(2, ProfessionName);
            preparedStmt.executeUpdate();

        }
        catch (Exception e1){
            e1.printStackTrace();
        }
    }
}

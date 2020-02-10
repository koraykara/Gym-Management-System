package Queries;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Types;

public class AskedForQueries {
    private Connection conn;

    public int getActionForCancel() {
        return actionForCancel;
    }
    public void setActionForCancel(int actionForCancel) {
        this.actionForCancel = actionForCancel;
    }

    private int actionForCancel;

    public AskedForQueries(Connection conn) {
        try{
            this.conn = conn;
        }
        catch (Exception e1){
            e1.printStackTrace();
        }

    }

    public void deleteAskedFor(String FieldCustomerID,String FieldProgramID){

        String sql = "delete from Asked_For where CustomerID = ? and ProgramID = ?"; // ------>> duzelttim
        PreparedStatement preparedStmt; // ------>> EKLEDIM

        actionForCancel = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this item?");
        if(actionForCancel == 0){
            try{
                preparedStmt = conn.prepareStatement(sql); // ------>> EKLEDIM
                preparedStmt.setString(1, FieldCustomerID);
                preparedStmt.setInt(2, Integer.valueOf(FieldProgramID));
                preparedStmt.execute();
            }
            catch (Exception e1){
                e1.printStackTrace();
            }
        }

    }
    public int insertAskedFor(String CustomerID,String ProgramID){

        String sql = "INSERT INTO Asked_For( CustomerID, ProgramID )"
                +"VALUES(?,?)";
        PreparedStatement preparedStmt;

        actionForCancel = JOptionPane.showConfirmDialog(null, "Are you sure you want to add this item?");
        if(actionForCancel == 0){
            try{
                preparedStmt = conn.prepareStatement(sql);
                preparedStmt.setString(1, CustomerID);
                if(ProgramID.isEmpty())
                    preparedStmt.setNull(2, Types.INTEGER);
                else
                    preparedStmt.setInt(2, Integer.parseInt(ProgramID));
                preparedStmt.execute();
                return 1;
            }
            catch (Exception e1){
                e1.printStackTrace();
            }
        }
        return 0;
    }
    public void updateCustomerID(String oldFieldCustomerID,String newFieldProgramID,String newFieldCustomerID){
        String sql = "update Asked_For set CustomerID = ? where CustomerID = ? and ProgramID = ?";
        PreparedStatement preparedStmt;
        try{
            preparedStmt = conn.prepareStatement(sql);
            preparedStmt.setString(1, newFieldCustomerID);
            preparedStmt.setString(2, oldFieldCustomerID);
            preparedStmt.setInt(3, Integer.parseInt(newFieldProgramID));
            preparedStmt.executeUpdate();

        }
        catch (Exception e1){
            e1.printStackTrace();
        }
    }

    public void updateProgramID(String oldFieldProgramID,String newFieldCustomerID,String newFieldProgramID){
        String sql = "update Asked_For set ProgramID = ? where ProgramID = ? and CustomerID = ?";
        PreparedStatement preparedStmt;
        try{
            preparedStmt = conn.prepareStatement(sql);

            if(newFieldProgramID.isEmpty())
                preparedStmt.setNull(1, Types.INTEGER);
            else
                preparedStmt.setInt(1, Integer.parseInt(newFieldProgramID));
            preparedStmt.setInt(2, Integer.valueOf(oldFieldProgramID));
            preparedStmt.setString(3, newFieldCustomerID);
            preparedStmt.executeUpdate();

        }
        catch (Exception e1){
            e1.printStackTrace();
        }
    }
}

package Queries;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Types;

public class HasQueries {
    private Connection conn;

    public int getActionForCancel() {
        return actionForCancel;
    }
    public void setActionForCancel(int actionForCancel) {
        this.actionForCancel = actionForCancel;
    }

    private int actionForCancel;

    public HasQueries(Connection conn) {
        try{
            this.conn = conn;
        }
        catch (Exception e1){
            e1.printStackTrace();
        }

    }
    public int insertHas(String CustomerID,String PyhsicalAilmentName
    ){

        String sql = "INSERT INTO Has( CustomerID, PyhsicalAilmentName )"
                +"VALUES(?,?)";
        PreparedStatement preparedStmt;

        actionForCancel = JOptionPane.showConfirmDialog(null, "Are you sure you want to add this item?");
        if(actionForCancel == 0){
            try{
                preparedStmt = conn.prepareStatement(sql);
                preparedStmt.setString(1, CustomerID);
                preparedStmt.setString(2, PyhsicalAilmentName);
                preparedStmt.execute();
                return 1;
            }
            catch (Exception e1){
                e1.printStackTrace();
            }
        }
        return 0;
    }

    public void deleteHas(String FieldCustomerID, String FieldPyhsicalAilmentName){

        String sql = "delete from Has where CustomerID = ? and PyhsicalAilmentName = ?"; // ------>> duzelttim
        PreparedStatement preparedStmt;

        actionForCancel = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this item?");
        if(actionForCancel == 0){
            try{
                preparedStmt = conn.prepareStatement(sql);
                preparedStmt.setString(1, FieldCustomerID);
                preparedStmt.setString(2, FieldPyhsicalAilmentName);
                preparedStmt.execute();
            }
            catch (Exception e1){
                e1.printStackTrace();
            }
        }

    }

    public void updateCustomerID(String oldFieldCustomerID,String newFieldPyhsicalAilment,String newFieldCustomerID){
        String sql = "update Has set CustomerID = ? where CustomerID = ? and PyhsicalAilmentName = ?";
        PreparedStatement preparedStmt;
        try{
            preparedStmt = conn.prepareStatement(sql);
            preparedStmt.setString(1, newFieldCustomerID);
            preparedStmt.setString(2, oldFieldCustomerID);
            preparedStmt.setString(3, newFieldPyhsicalAilment);
            preparedStmt.executeUpdate();

        }
        catch (Exception e1){
            e1.printStackTrace();
        }
    }

    public void updatePyhsicalAilment(String oldFieldPyhsicalAilment,String newFieldCustomerID,String newFieldPyhsicalAilment){
        String sql = "update Has set PyhsicalAilmentName = ? where CustomerID = ? and PyhsicalAilmentName = ?";
        PreparedStatement preparedStmt;
        try{
            preparedStmt = conn.prepareStatement(sql);
            preparedStmt.setString(1, newFieldPyhsicalAilment);
            preparedStmt.setString(2, newFieldCustomerID);
            preparedStmt.setString(3, oldFieldPyhsicalAilment);
            preparedStmt.executeUpdate();

        }
        catch (Exception e1){
            e1.printStackTrace();
        }
    }
}

package Queries;

import org.omg.CORBA.INTERNAL;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Types;

public class PurchaseQueries {
    private Connection conn;

    public int getActionForCancel() {
        return actionForCancel;
    }
    public void setActionForCancel(int actionForCancel) {
        this.actionForCancel = actionForCancel;
    }

    private int actionForCancel;

    public PurchaseQueries(Connection conn) {
        try{
            this.conn = conn;
        }
        catch (Exception e1){
            e1.printStackTrace();
        }

    }

    public void deletePurchase(String FieldCustomerID, String FieldLessonID, String FieldEnrollTimeDay){

        String sql = "delete from Purchase where CustomerID = ? and LessonID = ? and EnrollTimeDay = ?"; // ------>> duzelttim
        PreparedStatement preparedStmt; // ------>> EKLEDIM

        actionForCancel = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this item?");
        if(actionForCancel == 0){
            try{
                preparedStmt = conn.prepareStatement(sql); // ------>> EKLEDIM
                preparedStmt.setString(1, FieldCustomerID);
                preparedStmt.setString(2, FieldLessonID);
                preparedStmt.setString(3, FieldEnrollTimeDay);
                preparedStmt.execute();
            }
            catch (Exception e1){
                e1.printStackTrace();
            }
        }

    }
    public int insertPurchase(String customerID, String lessonID, String EnrollTimeDay, String purchaseDate
    ){

        String sql = "INSERT INTO Purchase( CustomerID, LessonID, EnrollTimeDay, PurchasedDate )"
                +"VALUES(?,?,?,?)";
        PreparedStatement preparedStmt;

        actionForCancel = JOptionPane.showConfirmDialog(null, "Are you sure you want to add this item?");
        if(actionForCancel == 0){
            try{
                preparedStmt = conn.prepareStatement(sql);
                preparedStmt.setString(1,customerID);

                if(lessonID.isEmpty())
                    preparedStmt.setNull(2, Types.INTEGER);
                else
                    preparedStmt.setInt(2, Integer.parseInt(lessonID));

                preparedStmt.setString(3,EnrollTimeDay);
                preparedStmt.setString(4,purchaseDate);
                return 1;
            }
            catch (Exception e1){
                e1.printStackTrace();
            }
        }
        return 0;
    }
    public void updateCustomerID(String oldFieldCustomerID,String newFieldLessonId,String newFieldEnrollTimeDay,String newFieldCustomerID){
        String sql = "update Purchase set CustomerID = ? where CustomerID = ? and LessonID = ? and EnrollTimeDay = ?";
        PreparedStatement preparedStmt;
        try{
            preparedStmt = conn.prepareStatement(sql);
            preparedStmt.setString(1, newFieldCustomerID);
            preparedStmt.setString(2, oldFieldCustomerID);
            preparedStmt.setInt(3, Integer.valueOf(newFieldLessonId));
            preparedStmt.setString(4, newFieldEnrollTimeDay);

            preparedStmt.executeUpdate();

        }
        catch (Exception e1){
            e1.printStackTrace();
        }
    }

    public void updateLessonID(String newFieldCustomerID,String oldFieldLessonId,String newFieldEnrollTimeDay,String newFieldLessonId){
        String sql = "update Purchase set LessonID = ? where CustomerID = ? and LessonID = ? and EnrollTimeDay = ?";
        PreparedStatement preparedStmt;
        try{
            preparedStmt = conn.prepareStatement(sql);
            preparedStmt.setInt(1, Integer.valueOf(newFieldLessonId));
            preparedStmt.setString(2, newFieldCustomerID);
            preparedStmt.setInt(3, Integer.valueOf(oldFieldLessonId));
            preparedStmt.setString(4, newFieldEnrollTimeDay);
            preparedStmt.executeUpdate();

        }
        catch (Exception e1){
            e1.printStackTrace();
        }
    }
    public void updateEnrollTimeDay(String newFieldCustomerID,String newFieldLessonId,String oldFieldEnrollTimeDay,String newFieldEnrollTimeDay){
        String sql = "update Purchase set EnrollTimeDay = ? where CustomerID = ? and LessonID = ? and EnrollTimeDay = ?";
        PreparedStatement preparedStmt;
        try{
            preparedStmt = conn.prepareStatement(sql);
            preparedStmt.setString(1, newFieldEnrollTimeDay);
            preparedStmt.setString(2, newFieldCustomerID);
            preparedStmt.setInt(3, Integer.valueOf(newFieldLessonId));
            preparedStmt.setString(4, oldFieldEnrollTimeDay);
            preparedStmt.executeUpdate();

        }
        catch (Exception e1){
            e1.printStackTrace();
        }
    }

    public void updatePurchasedDay(String newFieldCustomerID,String newFieldLessonId, String newFieldEnrollTimeDay,String newFieldPurchasedDate){
        String sql = "update Purchase set PurchasedDate = ?  where CustomerID = ? and LessonID = ? and EnrollTimeDay = ?";
        PreparedStatement preparedStmt;
        try{
            preparedStmt = conn.prepareStatement(sql);
            preparedStmt.setString(1, newFieldPurchasedDate);
            preparedStmt.setString(2, newFieldCustomerID);
            preparedStmt.setInt(3, Integer.valueOf(newFieldLessonId));
            preparedStmt.setString(4, newFieldEnrollTimeDay);
            preparedStmt.executeUpdate();

        }
        catch (Exception e1){
            e1.printStackTrace();
        }
    }

}

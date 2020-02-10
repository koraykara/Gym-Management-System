package Queries;

import Controllers.Controller;
import Controllers.CustomerItemController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

public class MembershipQueries {
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

    public MembershipQueries(Connection conn) {
        try{
            this.conn = conn;
        }
        catch (Exception e1){
            e1.printStackTrace();
        }

    }
    public void insertMembershipType(String name, String price){

        String sql = "INSERT INTO Membership_Type( Name, price )"
                +"VALUES(?,? )";
        PreparedStatement preparedStmt;

        actionForCancel = JOptionPane.showConfirmDialog(null, "Are you sure you want to add this item?");
        if(actionForCancel == 0){
            try{
                preparedStmt = conn.prepareStatement(sql);
                preparedStmt.setString(1,name);
                if(price.isEmpty())
                    preparedStmt.setNull(2, Types.FLOAT);
                else
                    preparedStmt.setFloat(2, Float.parseFloat(price));
                preparedStmt.execute();


                // -------------------------------

                Statement stmt = conn.createStatement();
                ResultSet rs;

                for( ;mainController.getPnItems().getChildren().size() > 1;){
                    mainController.getPnItems().getChildren().remove(1);
                }
                mainController.getNorCustomer().setText("0");
                sql = "SELECT * FROM Customer";
                rs = stmt.executeQuery(sql);
                ArrayList<Node> nodesCustomer = new ArrayList<>();
                //STEP 5: Extract data from result set
                int i=0;
                FXMLLoader fxmlLoader;
                FXMLLoader fxmlLoaderToAddInsert;
                fxmlLoaderToAddInsert = new FXMLLoader(getClass().getResource("../InsertItems/InsertCustomer.fxml"));

                if(i==0){
                    nodesCustomer.add(fxmlLoaderToAddInsert.load());
                    CustomerItemController customerItemControllerInsert = fxmlLoaderToAddInsert.getController();
                    customerItemControllerInsert.setMainController(this.mainController);
                    customerItemControllerInsert.createInsertBox();
                    mainController.getPnItems().getChildren().add(nodesCustomer.get(i));
                }
                while(rs.next()){
                    //Retrieve by column name
                    final int j = i+1;
                    final String id  = rs.getString("UsernameID");
                    final String membershipTypeName = rs.getString("MembershipTypeName");
                    final String customerTrainerID = rs.getString("TrainerID");
                    final String weight = rs.getString("Weight");
                    final String length = rs.getString("Length");
                    final String age = rs.getString("Age");
                    final String fatRatio = rs.getString("FatRatio");
                    final String creditCardNumber = rs.getString("CreditCardNumber");
                    final String creditCardExpireDate = rs.getString("CreditCardExpireDate");

                    fxmlLoader = new FXMLLoader(getClass().getResource("../ItemsFXML/CustomerItem.fxml"));


                    nodesCustomer.add(fxmlLoader.load());
                    CustomerItemController customerItemController = (CustomerItemController) fxmlLoader.getController();

                    customerItemController.setMainController(this.mainController);
                    customerItemController.assignTextLabel(id, membershipTypeName, customerTrainerID,
                            weight, length, age, fatRatio, creditCardNumber, creditCardExpireDate);


                    mainController.getPnItems().getChildren().add(nodesCustomer.get(j));
                    i++;
                }
                mainController.getNorCustomer().setText(String.valueOf(i));

            }
            catch (Exception e1){
                e1.printStackTrace();
            }
        }
    }

    public void deleteMembershipType(String FieldName){

        String sql = "delete from Membership_Type where Name = ?"; // ------>> duzelttim
        PreparedStatement preparedStmt; // ------>> EKLEDIM

        actionForCancel = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this item?");
        if(actionForCancel == 0){
            try{
                preparedStmt = conn.prepareStatement(sql); // ------>> EKLEDIM
                preparedStmt.setString(1, FieldName);
                preparedStmt.execute();

                // -------------------------------

                Statement stmt = conn.createStatement();
                ResultSet rs;

                for( ;mainController.getPnItems().getChildren().size() > 1;){
                    mainController.getPnItems().getChildren().remove(1);
                }
                mainController.getNorCustomer().setText("0");
                sql = "SELECT * FROM Customer";
                rs = stmt.executeQuery(sql);
                ArrayList<Node> nodesCustomer = new ArrayList<>();
                //STEP 5: Extract data from result set
                int i=0;
                FXMLLoader fxmlLoader;
                FXMLLoader fxmlLoaderToAddInsert;
                fxmlLoaderToAddInsert = new FXMLLoader(getClass().getResource("../InsertItems/InsertCustomer.fxml"));

                if(i==0){
                    nodesCustomer.add(fxmlLoaderToAddInsert.load());
                    CustomerItemController customerItemControllerInsert = fxmlLoaderToAddInsert.getController();
                    customerItemControllerInsert.setMainController(this.mainController);
                    customerItemControllerInsert.createInsertBox();
                    mainController.getPnItems().getChildren().add(nodesCustomer.get(i));
                }
                while(rs.next()){
                    //Retrieve by column name
                    final int j = i+1;
                    final String id  = rs.getString("UsernameID");
                    final String membershipTypeName = rs.getString("MembershipTypeName");
                    final String customerTrainerID = rs.getString("TrainerID");
                    final String weight = rs.getString("Weight");
                    final String length = rs.getString("Length");
                    final String age = rs.getString("Age");
                    final String fatRatio = rs.getString("FatRatio");
                    final String creditCardNumber = rs.getString("CreditCardNumber");
                    final String creditCardExpireDate = rs.getString("CreditCardExpireDate");

                    fxmlLoader = new FXMLLoader(getClass().getResource("../ItemsFXML/CustomerItem.fxml"));


                    nodesCustomer.add(fxmlLoader.load());
                    CustomerItemController customerItemController = (CustomerItemController) fxmlLoader.getController();

                    customerItemController.setMainController(this.mainController);
                    customerItemController.assignTextLabel(id, membershipTypeName, customerTrainerID,
                            weight, length, age, fatRatio, creditCardNumber, creditCardExpireDate);


                    mainController.getPnItems().getChildren().add(nodesCustomer.get(j));
                    i++;
                }
                mainController.getNorCustomer().setText(String.valueOf(i));
            }
            catch (Exception e1){
                e1.printStackTrace();
            }
        }

    }

    public void updateName(String oldFieldName,String newFieldName){
        String sql = "update Membership_Type set Name = ? where Name = ?";
        PreparedStatement preparedStmt;
        try{
            preparedStmt = conn.prepareStatement(sql);
            preparedStmt.setString(1, newFieldName);
            preparedStmt.setString(2, oldFieldName);
            preparedStmt.executeUpdate();

            // -------------------------------

            Statement stmt = conn.createStatement();
            ResultSet rs;

            for( ;mainController.getPnItems().getChildren().size() > 1;){
                mainController.getPnItems().getChildren().remove(1);
            }
            mainController.getNorCustomer().setText("0");
            sql = "SELECT * FROM Customer";
            rs = stmt.executeQuery(sql);
            ArrayList<Node> nodesCustomer = new ArrayList<>();
            //STEP 5: Extract data from result set
            int i=0;
            FXMLLoader fxmlLoader;
            FXMLLoader fxmlLoaderToAddInsert;
            fxmlLoaderToAddInsert = new FXMLLoader(getClass().getResource("../InsertItems/InsertCustomer.fxml"));

            if(i==0){
                nodesCustomer.add(fxmlLoaderToAddInsert.load());
                CustomerItemController customerItemControllerInsert = fxmlLoaderToAddInsert.getController();
                customerItemControllerInsert.setMainController(this.mainController);
                customerItemControllerInsert.createInsertBox();
                mainController.getPnItems().getChildren().add(nodesCustomer.get(i));
            }
            while(rs.next()){
                //Retrieve by column name
                final int j = i+1;
                final String id  = rs.getString("UsernameID");
                final String membershipTypeName = rs.getString("MembershipTypeName");
                final String customerTrainerID = rs.getString("TrainerID");
                final String weight = rs.getString("Weight");
                final String length = rs.getString("Length");
                final String age = rs.getString("Age");
                final String fatRatio = rs.getString("FatRatio");
                final String creditCardNumber = rs.getString("CreditCardNumber");
                final String creditCardExpireDate = rs.getString("CreditCardExpireDate");

                fxmlLoader = new FXMLLoader(getClass().getResource("../ItemsFXML/CustomerItem.fxml"));


                nodesCustomer.add(fxmlLoader.load());
                CustomerItemController customerItemController = (CustomerItemController) fxmlLoader.getController();

                customerItemController.setMainController(this.mainController);
                customerItemController.assignTextLabel(id, membershipTypeName, customerTrainerID,
                        weight, length, age, fatRatio, creditCardNumber, creditCardExpireDate);


                mainController.getPnItems().getChildren().add(nodesCustomer.get(j));
                i++;
            }
            mainController.getNorCustomer().setText(String.valueOf(i));

        }
        catch (Exception e1){
            e1.printStackTrace();
        }
    }

    public void updatePrice(String newFieldName,String newFieldPrice){
        String sql = "update Membership_Type set price = ? where Name = ?";
        PreparedStatement preparedStmt;
        try{
            preparedStmt = conn.prepareStatement(sql);
            if(newFieldPrice.isEmpty())
                preparedStmt.setNull(1, Types.FLOAT);
            else
                preparedStmt.setFloat(1, Float.parseFloat(newFieldPrice));
            preparedStmt.setString(2, newFieldName);
            preparedStmt.executeUpdate();

        }
        catch (Exception e1){
            e1.printStackTrace();
        }
    }
}
